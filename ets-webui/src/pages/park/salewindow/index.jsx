import React, { Component } from 'react';
import { Button, Table, Pagination, Divider, Row, Col, message, Input, Modal, Form } from 'antd';
import { PageHeaderWrapper } from '@ant-design/pro-layout';
import { connect } from 'dva';
import SaleWindowForm from './components/SaleWindowForm';

@Form.create()
@connect(({ saleWindow, loading }) => ({
  saleWindow,
  loading: loading.models.saleWindow,
  pageData: saleWindow.pageData,
  handleResult: saleWindow.handleResult,
  tempSaleWindow: saleWindow.tempSaleWindow,
}))
export default class SaleWindow extends Component {
  constructor(props) {
    super(props);
  }

  state = {
    editVisible: false,
    editTitle: '新增',
    selectedRowKeys: [],
    selectedRows: [],
    searchValue: '',
  };

  componentDidMount() {
    this.queryPage();
  }

  /**
   * 分页查询
   * @param param
   */
  queryPage = param => {
    if (!param) {
      const { pageData } = this.props;
      const { searchValue } = this.state;
      param = {
        current: 1,
        size: pageData.pagination.size,
        name: searchValue,
      };
    }
    const { dispatch } = this.props;
    dispatch({
      type: 'saleWindow/page',
      payload: {
        ...param,
      },
    }).then(() => {
      // this.calculateTableHeight();
      this.setState({
        selectedRowKeys: [],
        selectedRows: [],
      });
    });
  };

  /**
   * 刷新当前页面的数据
   */
  refreshCurrentPage = () => {
    const { pageData } = this.props;
    const { searchValue } = this.state;
    const param = {
      current: pageData.pagination.current,
      size: pageData.pagination.size,
      name: searchValue,
    };
    this.queryPage(param);
  };
  /**
   * 搜索
   * @param value
   */
  handleSearch = value => {
    const { pageData } = this.props;
    const param = {
      current: 1,
      size: pageData.pagination.size,
      name: value,
    };
    this.queryPage(param);
    this.setState({
      searchValue: value,
    });
  };

  handleTableChange = (current, size) => {
    const param = {
      current: current,
      size: size,
      name: this.state.searchValue,
    };
    this.queryPage(param);
  };

  showTotal(total, range) {
    return `共 ${total} 条`;
  }

  changeEditVisible = (title, flag) => {
    if (flag) {
      const { dispatch } = this.props;
      dispatch({
        type: 'saleWindow/getSaleSiteList',
      });
    }
    this.setState({
      editVisible: flag,
      editTitle: title,
    });
    if (!flag) {
      this.clearModelsData();
    }
  };
  /**
   * 清理model 那边的临时数据
   */
  clearModelsData = () => {
    const { dispatch } = this.props;
    dispatch({
      type: 'saleWindow/clearData',
    });
  };
  /**
   * 删除
   * @param id
   */
  handleRemove = id => {
    Modal.confirm({
      title: '提示',
      content: '确定删除此数据？',
      okText: '确定',
      cancelText: '取消',
      onOk: () => {
        let ids = new Array();
        ids.push(id);
        this.remove(ids);
      },
    });
  };

  /**
   * 批量删除
   */
  handleBatchRemove = () => {
    Modal.confirm({
      title: '提示',
      content: '确定删除选中数据？',
      okText: '确定',
      cancelText: '取消',
      onOk: () => {
        const { selectedRows } = this.state;
        const ids = selectedRows.map(row => {
          return row.id;
        });
        this.remove(ids);
      },
    });
  };

  remove = ids => {
    const { dispatch } = this.props;
    dispatch({
      type: 'saleWindow/remove',
      payload: ids,
    }).then(() => {
      const { handleResult } = this.props;
      if (handleResult.status) {
        this.clearModelsData();
        this.queryPage();
        message.success('删除成功');
      } else {
        message.error('删除失败');
      }
    });
  };
  /**
   * 编辑
   * @param id
   */
  handlePreEdit = id => {
    const { dispatch } = this.props;
    dispatch({
      type: 'saleWindow/getById',
      payload: id,
    }).then(() => {
      const { handleResult, tempSaleWindow } = this.props;
      if (handleResult.status && tempSaleWindow !== null) {
        this.changeEditVisible('编辑', true);
      } else {
        this.queryPage();
        message.error('数据不存在');
      }
    });
  };

  onSelectChange = (selectedRowKeys, selectedRows) => {
    this.setState({
      selectedRowKeys,
      selectedRows,
    });
  };

  render() {
    const { pageData, loading } = this.props;
    const { list, pagination } = pageData;
    const { editVisible, editTitle, selectedRowKeys } = this.state;
    const columns = [
      {
        title: '序号',
        dataIndex: 'id',
        width: '6%',
        key: 'id',
        render: (text, record, index) => <span>{index + 1}</span>,
      },
      {
        title: '窗口名称',
        dataIndex: 'windowName',
        key: 'windowName',
      },
      {
        title: '所属站点',
        dataIndex: 'saleSite.siteName',
        key: 'saleSite.siteName',
      },
      {
        title: 'mac地址',
        dataIndex: 'mac',
        key: 'mac',
      },
      {
        title: '操作',
        render: (text, record) => (
          <span>
            <a size="small" onClick={this.handlePreEdit.bind(this, `${record.id}`)}>
              编辑
            </a>
            <Divider type="vertical" />
            <a size="small" onClick={this.handleRemove.bind(this, `${record.id}`)}>
              删除
            </a>
          </span>
        ),
      },
    ];
    const rowSelection = {
      selectedRowKeys,
      onChange: this.onSelectChange,
    };
    const editMethods = {
      queryPage: this.queryPage,
      refreshCurrentPage: this.refreshCurrentPage,
      clearModelsData: this.clearModelsData,
      changeEditVisible: this.changeEditVisible,
    };
    return (
      <PageHeaderWrapper>
        <Row>
          <Col>
            <Button type="primary" onClick={() => this.changeEditVisible('新增', true)}>
              新增
            </Button>
            <Button
              onClick={() => this.handleBatchRemove()}
              disabled={selectedRowKeys.length === 0}
              style={{ marginLeft: 10 }}
            >
              删除
            </Button>
            <Input.Search
              placeholder="请输入窗口名称"
              onSearch={this.handleSearch}
              style={{ width: 200, float: 'right' }}
            />
          </Col>
        </Row>
        <Row style={{ marginTop: 15 }}>
          <Col>
            <Table
              loading={loading}
              columns={columns}
              dataSource={list}
              rowKey={item => item.id}
              pagination={false}
              rowSelection={rowSelection}
            >
            </Table>
            <Pagination
              current={pagination.current}
              showSizeChanger
              onChange={this.handleTableChange}
              onShowSizeChange={this.handleTableChange}
              total={pagination === undefined ? 0 : pagination.total}
              showTotal={this.showTotal}
              style={{ marginTop: '20px', float: 'right' }}
            />
          </Col>
        </Row>
        <SaleWindowForm editVisible={editVisible} title={editTitle} {...editMethods} />
      </PageHeaderWrapper>
    );
  }
}
