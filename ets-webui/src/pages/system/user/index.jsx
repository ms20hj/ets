import React, { Component } from 'react';
import { Button, Table, Pagination, Divider, Row, Col, message } from 'antd';
import { PageHeaderWrapper } from '@ant-design/pro-layout';
import { connect } from 'dva';
import EditForm from './components/EditForm';

@connect(({ user, loading }) => ({
  user,
  pageData: user.pageData,
  handleResult: user.handleResult,
  tempUser: user.tempUser,
}))
export default class User extends Component {
  constructor(props) {
    super(props);
  }

  state = {
    editVisible: false,
    editTitle: '新增',
  };

  componentDidMount() {
    const { pageData } = this.props;
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
  queryPage = param => {
    const { dispatch } = this.props;
    dispatch({
      type: 'user/page',
      payload: {
        ...param,
      },
    });
  };

  handleTableChange = (current, size) => {
    const param = {
      current: current,
      size: size,
    };
    this.queryPage(param);
  };

  showTotal(total, range) {
    return `共 ${total} 条`;
  }

  changeEidtVisible = (title, flag) => {
    this.setState({
      editVisible: flag,
      editTitle: title,
    });
  };
  /**
   * 清理model 那边的临时数据
   */
  clearModelsData = () => {
    const { dispatch } = this.props;
    dispatch({
      type: 'user/clearData',
    });
  };

  handleSave = user => {
    const { tempUser, dispatch } = this.props;
    const action = tempUser === null ? 'user/save' : 'user/update';
    dispatch({
      type: action,
      payload: {
        ...user,
      },
    }).then(() => {
      const { handleResult, pageData } = this.props;
      if (handleResult.status) {
        this.changeEidtVisible('', false);
        const param = {
          current: 1,
          size: pageData.pagination.size,
        };
        this.queryPage(param);
        message.success('保存成功').then(() => {
          this.clearModelsData();
        });
      } else {
        message.error('保存失败');
      }
    });
  };

  render() {
    const { pageData, loading } = this.props;
    const { list, pagination } = pageData;
    const { editVisible, editTitle } = this.state;
    const columns = [
      {
        title: '序号',
        dataIndex: '',
        width: '6%',
        key: '',
        render: (text, record, index) => <span>{index + 1}</span>,
      },
      {
        title: '账号',
        dataIndex: 'userName',
        key: 'userName',
      },
      {
        title: '真实姓名',
        dataIndex: 'realName',
        key: 'realName',
      },
      {
        title: '状态',
        key: 'status',
        dataIndex: 'status',
        render: status => <span>{status === 0 ? '启用' : '禁用'}</span>,
      },
      {
        title: '操作',
        render: (text, record) => (
          <span>
            <a size="small">编辑</a>
            <Divider type="vertical" />
            <a size="small">删除</a>
          </span>
        ),
      },
    ];
    const editMethods = {
      handleSave: this.handleSave,
      changeEidtVisible: this.changeEidtVisible,
    };
    return (
      <PageHeaderWrapper>
        <Row>
          <Col>
            <Button type="primary" onClick={() => this.changeEidtVisible('新增', true)}>
              新增
            </Button>
          </Col>
        </Row>
        <Row style={{ marginTop: 15 }}>
          <Col>
            <Table loading={loading} columns={columns} dataSource={list}></Table>
            <Pagination
              defaultCurrent={1}
              showSizeChanger
              onChange={this.handleTableChange}
              onShowSizeChange={this.handleTableChange}
              total={pagination === undefined ? 0 : pagination.total}
              showTotal={this.showTotal}
              style={{ marginTop: '20px', float: 'right' }}
            />
          </Col>
        </Row>
        <EditForm editVisible={editVisible} title={editTitle} {...editMethods} />
      </PageHeaderWrapper>
    );
  }
}
