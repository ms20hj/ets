import React, { Component } from 'react';
import { Form, Input, message, Modal, Select } from 'antd';
import { connect } from 'dva';
import Row from "antd/lib/grid/row";
import Col from "antd/lib/grid/col";

const formItemLayout = {
  labelCol: {
    xs: {span: 24},
    sm: {span: 8},
  },
  wrapperCol: {
    xs: {span: 24},
    sm: {span: 12},
    md: {span: 16},
  },
};

@connect(({ ticket }) => ({
  ticket,
  handleResult: ticket.handleResult,
  tempTicket: ticket.tempTicket,
  pageData: ticket.pageData,
  scenicSpotList: ticket.scenicSpotList,
  physicalList: ticket.physicalList,
  deadlineUnitList: ticket.deadlineUnitList,
  printMethodList: ticket.printMethodList,
  printTemplateList: ticket.printTemplateList,
}))
class TicketForm extends Component {

  constructor(props) {
    super(props);
  }

  componentDidMount() {
    const {dispatch} = this.props;
    dispatch({
      type: 'ticket/initTicketSelectParams',
    });
  }

  validateName = async (rule, value, callback) => {
    try {
      if (value) {
        const { dispatch, tempTicket } = this.props;
        const param = {
          name: value,
        };
        if (tempTicket.id) {
          param.id = tempTicket.id;
        }
        return dispatch({
          type: 'ticket/checkNameExist',
          payload: {
            ...param,
          },
        }).then(() => {
          const { handleResult } = this.props;
          if (handleResult.data) {
            return Promise.reject(new Error('名称已存在'));
          } else {
            return Promise.resolve();
          }
        });
      }
      return Promise.resolve();
    } catch (e) {
      return Promise.reject(e);
    }
  };

  checkSortNum = (rule, value, callback) => {
    const regPos = /^-?[0-9]\d*$/; // 非负浮点数
    if (value && value !== '') {
      if(!regPos.test(value)){
        callback('请输入数字!');
      }
      if (value.length > 4) {
        callback('不能超出4位数!');
      }
    }
    callback();
  };

  handleSubmit = () => {
    const { form, parentId } = this.props;
    form.validateFieldsAndScroll((err, fieldsValue) => {
      if (err) return;
      const { tempTicket, dispatch, queryPage, changeEditVisible, refreshCurrentPage } = this.props;
      const action = tempTicket.id ? 'ticket/update' : 'ticket/save';
      tempTicket.id ? (fieldsValue.id = tempTicket.id) : '';
      fieldsValue.parentId = parentId;
      fieldsValue.level = 3; //旅行社等级为3
      dispatch({
        type: action,
        payload: {
          ...fieldsValue,
        },
      }).then(() => {
        const { handleResult, dispatch } = this.props;
        if (handleResult.status) {
          tempTicket.id ? refreshCurrentPage() : queryPage();
          dispatch({
            type: 'ticket/clearData'
          });
          changeEditVisible('', false);
          message.success('保存成功');
        } else {
          message.error('保存失败');
        }
      });
    });
  };

  render() {
    const {
      editVisible,
      title,
      form,
      changeEditVisible,
      tempTicket,
      scenicSpotList,
    } = this.props;
    const { getFieldDecorator } = form;
    return (
      <Modal
        destroyOnClose
        title={title}
        visible={editVisible}
        onOk={this.handleSubmit}
        width={600}
        onCancel={() => changeEditVisible('', false)}
      >

        <Form {...formItemLayout}>
          <Row gutter={2}>
            <Col span={12}>
              <Form.Item label="票名称">
                {getFieldDecorator('ticketName', {
                  initialValue: tempTicket.ticketName,
                  rules: [
                    { required: true, message: '请输入票名称' },
                    { max: 16, message: '票名称不能超过16个字符' },
                    { validator: this.validateName },
                  ],
                  validateTrigger: 'onBlur',
                })(<Input placeholder="请输入票名称" width={150}/>)}
              </Form.Item>
            </Col>

            <Col span={12}>
              <Form.Item label="所属景区">
                {getFieldDecorator('scenicSpotId', {
                  initialValue: tempTicket.scenicSpotId,
                  rules: [{ required: true, message: '请选择所属景区' }],
                  validateTrigger: 'onBlur',
                })(
                  <Select placeholder="请选择所属景区">
                    {scenicSpotList.map((item, index) => (
                      <Select.Option key={item.id} value={item.id}>
                        {item.spotName}
                      </Select.Option>
                    ))}
                  </Select>,
                )}
              </Form.Item>
            </Col>
          </Row>

        </Form>
      </Modal>
    );
  }
}

export default Form.create()(TicketForm);
