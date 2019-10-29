import React, { Component } from 'react';
import {Button, Table, Pagination, Divider, Row, Col, message, Input, Modal, Form, Tree} from 'antd';
import { PageHeaderWrapper } from '@ant-design/pro-layout';
import { connect } from 'dva';
import TravelTypeForm from './components/TravelTypeForm';
import SaleWindowForm from "../../park/salewindow/components/SaleWindowForm";
import {getContentHeight} from "../../../utils/utils";

@Form.create()
@connect(({ travelAgency, loading }) => ({
  travelAgency,
  loading: loading.models.travelAgency,
  pageData: travelAgency.pageData,
  handleResult: travelAgency.handleResult,
  tempTravelAgency: travelAgency.tempTravelAgency,
  treeTravel: travelAgency.treeTravel,
}))
export default class TravelAgency extends Component {
  constructor(props) {
    super(props);
  }

  state = {
    editVisible: false,
    editTitle: '新增',
    editTypeVisible: false,
    editTypeTitle: '新增',
    typeAddButtonDisabled: true,
    typeEditButtonDisabled: true,
    addButtonDisabled: true,
    selectedRowKeys: [],
    selectedRows: [],
    currentKey: '',
    treeSelectKeys: [],
  };

  componentDidMount() {
    const { pageData } = this.props;
    const param = {
      current: pageData.pagination.current,
      size: pageData.pagination.size,
    };
    this.queryPage(param);
    this.queryTree();
  }

  /**
   * 分页查询
   * @param param
   */
  queryPage = param => {
    const {currentKey} = this.state;
    param.parentId = currentKey;
    const { dispatch } = this.props;
    dispatch({
      type: 'travelAgency/page',
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

  queryTree = () => {
    const { dispatch } = this.props;
    dispatch({
      type: 'travelAgency/getTreeTravel'
    }).then( () => {
      const {treeTravel,pageData} = this.props;
      const rootNode = treeTravel[0];
      if (rootNode.children && rootNode.children.length > 0) {
        debugger;
        const child = rootNode.children[0];
        this.setState({
          treeSelectKeys: [child.id],
          currentKey: child.id,
        })
      } else {
        this.setState({
          treeSelectKeys: [],
          currentKey: '',
        })
      }
      const param = {
        current: 1,
        size: pageData.pagination.size,
      };
      this.queryPage(param);
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

  changeEditVisible = (title, flag) => {
    this.setState({
      editVisible: flag,
      editTitle: title,
    });
  };

  changeEditTypeVisible = (title, flag) => {
    this.setState({
      editTypeVisible: flag,
      editTypeTitle: title,
    });
  };
  /**
   * 清理model 那边的临时数据
   */
  clearModelsData = () => {
    const { dispatch } = this.props;
    dispatch({
      type: 'travelAgency/clearData',
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
   * 删除类别
   */
  handleRemoveType = () => {
    const removeType = () => {
      const { dispatch } = this.props;
      const {currentKey} = this.state;
      const ids = [currentKey];
      dispatch({
        type: 'travelAgency/remove',
        payload: ids,
      }).then(() => {
        const { handleResult } = this.props;
        if (handleResult.status) {
          this.queryTree();
          message.success('删除成功');
        } else {
          message.error('删除失败');
        }
      });
    };
    Modal.confirm({
      title: '提示',
      content: '确定删除此类别？',
      okText: '确定',
      cancelText: '取消',
      onOk: removeType,
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
      type: 'travelAgency/remove',
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
      type: 'travelAgency/getById',
      payload: id,
    }).then(() => {
      const { handleResult, tempTravelAgency, pageData } = this.props;
      if (handleResult.status && tempTravelAgency !== null) {
        this.changeEditVisible('编辑', true);
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
  /**
   * 编辑类别
   */
  handlePreTypeEdit = () => {
    const { dispatch } = this.props;
    const {currentKey} = this.state;
    dispatch({
      type: 'travelAgency/getTypeById',
      payload: currentKey,
    }).then(() => {
      const { handleResult, tempTravelType } = this.props;
      if (handleResult.status && tempTravelType !== null) {
        this.changeEditTypeVisible('编辑', true);
      } else {
        this.queryTree();
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

  /**
   * 渲染树节点
   * @param data
   */
  renderTreeNodes = (data) => data.map(item => {
    if (item.children) {
      return (
        <Tree.TreeNode title={item.name} key={item.id} dataRef={item}>
          {this.renderTreeNodes(item.children)}
        </Tree.TreeNode>
      );
    }
    return <Tree.TreeNode key={item.id} title={item.name} dataRef={item} />;
  });

  onSelectTreeNode = (selectedKeys, info) => {
    const props = info.node.props;
    this.setState({
      currentKey:props.eventKey
    });
    if (props.eventKey === 'ROOT') {
      this.setState({
        typeAddButtonDisabled: false,
        typeEditButtonDisabled: true,
        addButtonDisabled: true,
      });
    } else {
      this.setState({
        typeAddButtonDisabled: true,
        typeEditButtonDisabled: false,
        addButtonDisabled: false,
      });
    }
  };

  render() {
    const { pageData, loading, treeTravel } = this.props;
    const { list, pagination } = pageData;
    const { editVisible, editTitle, editTypeVisible, editTypeTitle, selectedRowKeys, typeAddButtonDisabled,
      typeEditButtonDisabled, addButtonDisabled, currentKey, treeSelectKeys } = this.state;
    const columns = [
      {
        title: '序号',
        dataIndex: 'id',
        width: '6%',
        key: 'id',
        render: (text, record, index) => <span>{index + 1}</span>,
      },
      {
        title: '旅行社名称',
        dataIndex: 'name',
        key: 'name',
      },
      {
        title: '联系人',
        dataIndex: 'contact',
        key: 'contact',
      },
      {
        title: '联系电话',
        dataIndex: 'phone',
        key: 'phone',
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
      changeEditVisible: this.changeEditVisible,
    };
    const editTypeMethods = {
      queryTree: this.queryTree,
      changeEditTypeVisible: this.changeEditTypeVisible,
      parentId: currentKey,
    };
    return (
      <PageHeaderWrapper>
        <Row gutter={8}>
          {/*左边树*/}
          <Col span={4}>
            <Row>
              <Col>
                <Button type="primary" disabled={typeAddButtonDisabled}
                        style={{ marginLeft: 30 }}
                        onClick={() => this.changeEditTypeVisible('新增', true)}>
                  新增
                </Button>
                <Button type="primary" disabled={typeEditButtonDisabled}
                        style={{ marginLeft: 10} }
                        onClick={() => this.handlePreTypeEdit()}>
                  编辑
                </Button>
                <Button disabled={list.length === 0 && typeEditButtonDisabled}
                        style={{ marginLeft: 10 }}
                        onClick={() => this.handleRemoveType()}>
                  删除
                </Button>
              </Col>
            </Row>
            <Row>
              <Col>
                <Tree
                  selectedKeys={treeSelectKeys}
                  defaultExpandAll
                  onSelect={this.onSelectTreeNode}
                  style={{height: getContentHeight()-50}}
                >
                  {this.renderTreeNodes(treeTravel)}
                </Tree>
              </Col>
            </Row>
          </Col>
          {/*右侧列表页面*/}
          <Col span={20}>
            <Row>
              <Col>
                <Button type="primary" disabled={addButtonDisabled} onClick={() => this.changeEditVisible('新增', true)}>
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
                  placeholder="请输入旅行社名称"
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
          </Col>
        </Row>
        <TravelTypeForm editTypeVisible={editTypeVisible} title={editTypeTitle} {...editTypeMethods} />
      </PageHeaderWrapper>
    );
  }
}
