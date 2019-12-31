import React, {Component} from 'react';
import {Col, Form, Input, InputNumber, message, Modal, Popconfirm, Row, Table} from "antd";
import {connect} from 'dva';

const EditableContext = React.createContext();

class EditableCell extends React.Component {
  getInput = () => {
    return <InputNumber min={1} max={999} />;
  };

  renderCell = ({ getFieldDecorator }) => {
    const {
      editing,
      dataIndex,
      title,
      inputType,
      record,
      index,
      children,
      ...restProps
    } = this.props;
    return (
      <td {...restProps}>
        {editing ? (
          <Form.Item style={{ margin: 0 }}>
            {getFieldDecorator(dataIndex, {
              rules: [
                {
                  required: true,
                  message: `Please Input ${title}!`,
                },
              ],
              initialValue: record[dataIndex],
            })(this.getInput())}
          </Form.Item>
        ) : (
          children
        )}
      </td>
    );
  };

  render() {
    return <EditableContext.Consumer>{this.renderCell}</EditableContext.Consumer>;
  }
}

@connect(({ ticket }) => ({
  ticket,
  ticketScapeList: ticket.ticketScapeList,
  handleResult: ticket.handleResult,
}))
class TicketScapeTable extends Component{

  constructor(props) {
    super(props);
    this.state = { editingKey: '' };
    this.columns = [
      {
        title: '序号',
        dataIndex: 'id',
        width: '10%',
        key: 'id',
        editable: false,
        render: (text, record, index) => <span>{index + 1}</span>,
      },
      {
        title: '景点名称',
        dataIndex: 'scapeName',
        key: 'scapeName',
        editable: false,
      },
      {
        title: '总可进次数',
        dataIndex: 'allIn',
        key: 'allIn',
        editable: true,
      },
      {
        title: '日可进次数',
        dataIndex: 'dayIn',
        key: 'dayIn',
        editable: true,
      },
      {
        title: '操作',
        dataIndex: '操作',
        render: (text, record) => {
          const { editingKey } = this.state;
          const editable = this.isEditing(record);
          return editable ? (
            <span>
              <EditableContext.Consumer>
                {form => (
                  <a
                    onClick={() => this.save(form, record.id)}
                    style={{ marginRight: 8 }}
                  >
                    保存
                  </a>
                )}
              </EditableContext.Consumer>
              <Popconfirm title="确定取消?" onConfirm={() => this.cancel(record.id)}>
                <a>取消</a>
              </Popconfirm>
            </span>
          ) : (
            <a disabled={editingKey !== ''} onClick={() => this.edit(record.id)}>
              编辑
            </a>
          );
        },
      },
    ];
  }

  handleSubmit = () => {

  };

  isEditing = record => record.id === this.state.editingKey;

  cancel = () => {
    this.setState({ editingKey: '' });
  };

  save(form, key) {
    form.validateFields((error, row) => {
      if (error) {
        return;
      }
      const {ticketScapeList} = this.props;
      const newData = [...ticketScapeList];
      const index = newData.findIndex(item => key === item.id);
      if (index > -1) {
        const item = newData[index];
        newData.splice(index, 1, {
          ...item,
          ...row,
        });
        const{dispatch} = this.props;
        dispatch({
          type: 'ticket/updateConfig',
          payload: {
            id: item.id,
            ...row,
          },
        }).then( () => {
          this.setState({ editingKey: '' });
          const { handleResult, dispatch } = this.props;
          if (handleResult.status) {
            message.success('保存成功');
            dispatch({
              type: 'ticket/getTicketScapeList',
              payload: item.ticketId,
            });
          } else {
            message.error('保存失败');
          }
        });
      }
    });
  }

  edit(key) {
    this.setState({ editingKey: key });
  }


  render() {
    const {editTsVisible, ticketScapeList, changeEditTsVisible} = this.props;
    const components = {
      body: {
        cell: EditableCell,
      },
    };
    const columns = this.columns.map(col => {
      if (!col.editable) {
        return col;
      }
      return {
        ...col,
        onCell: record => ({
          record,
          dataIndex: col.dataIndex,
          title: col.title,
          editing: this.isEditing(record),
        }),
      };
    });


    return(
      <Modal
        destroyOnClose
        title='游玩景点配置'
        visible={editTsVisible}
        onOk={this.handleSubmit}
        onCancel={() => changeEditTsVisible(false)}
        width={680}
        footer={false}
      >
        <Row>
          <Col span={24}>
            <EditableContext.Provider value={this.props.form}>
              <Table
                components={components}
                bordered
                dataSource={ticketScapeList}
                columns={columns}
                pagination={false}
              />
            </EditableContext.Provider>
          </Col>
        </Row>

      </Modal>
    );
  }
}
export default Form.create()(TicketScapeTable);


