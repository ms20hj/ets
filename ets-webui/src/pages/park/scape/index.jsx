import React, { Component } from 'react';
import { Button, Table, Pagination, Divider, Row, Col, message, Input, Modal, Form } from 'antd';
import { PageHeaderWrapper } from '@ant-design/pro-layout';
import { connect } from 'dva';
import ScapeForm from './components/ScapeForm';

@Form.create()
@connect(({ scape, loading }) => ({
  scape,
  loading: loading.models.scape,
  pageData: scape.pageData,
  handleResult: scape.handleResult,
  tempScape: scape.tempScape,
}))
export default class Scape extends Component {
  constructor(props) {
    super(props);
  }

  state = {
    editVisible: false,
    editTitle: '新增',
    selectedRowKeys: [],
    selectedRows: [],
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
      type: 'scape/page',
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
    if (flag) {
      const { dispatch } = this.props;
      dispatch({
        type: 'scape/getScenicSpotList',
      });
    }
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
      type: 'scape/clearData',
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
      type: 'scape/remove',
      payload: ids,
    }).then(() => {
      const { handleResult, pageData } = this.props;
      if (handleResult.status) {
        this.clearModelsData();
        const param = {
          current: 1,
          size: pageData.pagination.size,
        };
        this.queryPage(param);
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
      type: 'scape/getById',
      payload: id,
    }).then(() => {
      const { handleResult, tempScape, pageData } = this.props;
      if (handleResult.status && tempScape !== null) {
        this.changeEidtVisible('编辑', true);
      } else {
        const param = {
          current: 1,
          size: pageData.pagination.size,
        };
        this.queryPage(param);
        message.error('数据不存在');
      }
    });
  };

  onSelectChange = (selectedRowKeys, selectedRows) => {
    console.log(selectedRowKeys, selectedRows);
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
        title: '景点名称',
        dataIndex: 'scapeName',
        key: 'scapeName',
      },
      {
        title: '所属景区',
        dataIndex: 'scenicSpot.scapeName',
        key: 'scenicSpot.scapeName',
      },
      {
        title: '描述',
        dataIndex: 'description',
        key: 'description',
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
      clearModelsData: this.clearModelsData,
      changeEidtVisible: this.changeEidtVisible,
    };
    return (
      <PageHeaderWrapper>
        <Row>
          <Col>
            <Button type="primary" onClick={() => this.changeEidtVisible('新增', true)}>
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
              placeholder="请输入景点名称"
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
        <ScapeForm editVisible={editVisible} title={editTitle} {...editMethods} />
      </PageHeaderWrapper>
    );
  }
}
