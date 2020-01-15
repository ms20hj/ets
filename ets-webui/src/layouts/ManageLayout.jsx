import React, { Component } from 'react';
import { connect } from 'dva';
import { Link, routerRedux } from 'dva/router';
import RightContent from '@/components/GlobalHeader/RightContent';
import logo from '../assets/logo.svg';
import ProLayout from '@ant-design/pro-layout';

@connect(({ global, settings }) => ({
  collapsed: global.collapsed,
  settings,
  routes: global.routes,
}))
export default class ManageLayout extends Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'global/fetchRoutes',
    }).then(() => {
      const { routes, dispatch } = this.props;
      if (routes.length === 0) {
        dispatch(
          routerRedux.replace({
            pathname: '/exception/403',
          }),
        );
      }
    });
  }

  toggle = () => {
    this.setState({
      collapsed: !this.state.collapsed,
    });
  };

  handleMenuCollapse = payload => {
    const { dispatch } = this.props;
    dispatch({
      type: 'global/changeLayoutCollapsed',
      payload,
    });
  };

  footerRender = (_, defaultDom) => {
    return (
      <>
        {defaultDom}
        <div
          style={{
            padding: '0px 24px 24px',
            textAlign: 'center',
          }}
        >
          xxxxxxxxxxx
        </div>
      </>
    );
  };

  render() {
    const { routes } = this.props;
    return (
      <ProLayout
        logo={logo}
        onCollapse={this.handleMenuCollapse}
        menuItemRender={(menuItemProps, defaultDom) => {
          if (menuItemProps.isUrl) {
            return defaultDom;
          }
          return <Link to={menuItemProps.path}>{defaultDom}</Link>;
        }}
        itemRender={(route, params, routes, paths) => {
          const first = routes.indexOf(route) === 0;
          return first ? (
            <Link to={paths.join('/')}>{route.breadcrumbName}</Link>
          ) : (
            <span>{route.breadcrumbName}</span>
          );
        }}
        footerRender={this.footerRender}
        menuDataRender={() => routes}
        rightContentRender={rightProps => <RightContent {...rightProps} />}
        {...this.props}
        {...this.props.settings}
      >
        {this.props.children}
      </ProLayout>
    );
  }
}
