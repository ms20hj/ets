import React from 'react';
import { connect } from 'dva';
import { Spin } from 'antd';

const FetchRoutes = WrappedComponent => {
  @connect(state => ({
    global: state.global,
  }))
  class FetchRoutes extends React.Component {
    componentDidMount() {
      this.props.dispatch({
        type: 'global/fetchRoutes',
      });
    }
    render() {
      const routes = this.props.global.routes;
      return routes.length === 0 ? (
        <Spin spinning={true} />
      ) : (
        <WrappedComponent {...this.props} route={routes} />
      );
    }
  }
  return FetchRoutes;
};
export default FetchRoutes;
