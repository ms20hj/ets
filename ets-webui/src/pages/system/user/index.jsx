import React, { Component } from 'react';
import { Table, Pagination, Divider  } from 'antd';
import { PageHeaderWrapper } from '@ant-design/pro-layout';
import { connect } from 'dva';

@connect(({ user, loading }) => ({
  user,
  pageData: user.pageData,
}))
export default class User extends Component {

  componentDidMount() {
    const {pageData} = this.props;
    const param = {
      current: pageData.pagination.current,
      size: pageData.pagination.size,
    };
    this.queryPage(param);
  }

  /**
   * 分页查询
   * @param param
   */
  queryPage=(param)=>{
    const {dispatch } = this.props;
    dispatch({
      type: 'user/page',
      payload: {
        ...param,
      },
    })
  };

  render() {
    const { pageData, loading } = this.props;
    const {list, pagination} = pageData;
    const columns = [
      {
        title: '序号',
        dataIndex: '',
        width: '6%',
        key: '',
        render: (text, record, index) => (
          <span>{index + 1}</span>
        ),
      }, {
        title: '账号',
        dataIndex: 'userName',
        key: 'userName',
      }, {
        title: '真实姓名',
        dataIndex: 'realName',
        key: 'realName',
      }, {
        title: '状态',
        key: 'status',
        dataIndex: 'status',
        render: status => (
          <span>
            return (
              <span>
                {status === 0 ? "启用": "禁用"}
              </span>
          );
          </span>

        ),
      }, {
        title: '操作',
        render: (text, record) => (
          <span>
            <a size="small">编辑</a>
            <Divider type="vertical" />
            <a size="small">删除</a>
          </span>
        ),
      }];
    return (
      <PageHeaderWrapper>
        <Table
          loading={loading}
          columns={columns}
          dataSource={list}
        >
        </Table>
      </PageHeaderWrapper>
    );
  }
}
