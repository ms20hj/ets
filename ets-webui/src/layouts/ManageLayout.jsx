import React, { Component } from 'react';
import { connect } from 'dva';
import { Link } from 'dva/router';
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
    this.state = {
      collapsed: false,
      openKeys: [],
      rootSubmenuKeys: [],
    };
  }

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'global/fetchRoutes',
    }).then(() => {
      const { routes } = this.props;
      if (routes.length === 0) {
        message.warning('暂无权限');
      }
      let subMenuKeys = routes.map(item => {
        return item.id;
      });
      this.setState({ rootSubmenuKeys: subMenuKeys });
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
