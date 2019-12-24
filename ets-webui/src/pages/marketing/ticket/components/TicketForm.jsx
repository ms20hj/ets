import React, { Component } from 'react';
import { Form, Input, message, Modal, Select, DatePicker, Row, Col } from 'antd';
import { connect } from 'dva';
import moment from "moment";


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

const formItemLayoutColspan = {
  labelCol: {
    xs: {span: 24},
    sm: {span: 4},
  },
  wrapperCol: {
    xs: {span: 24},
    sm: {span: 12},
    md: {span: 16},
  },
};

const { RangePicker } = DatePicker;

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
      physicalList,
      deadlineUnitList,
      printMethodList,
      printTemplateList,
      currentNode,
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

          <Row gutter={2}>
            <Col span={12}>
              <Form.Item label="所属种类">
                {getFieldDecorator('ticketCategoryId', {
                  initialValue: tempTicket.ticketCategoryId === '' ? currentNode.id:tempTicket.ticketCategoryId,
                })(
                  <Select placeholder="请选择所属种类" disabled>
                    <Select.Option value={currentNode.id} >
                      {currentNode.title}
                    </Select.Option>
                  </Select>,
                )}
              </Form.Item>
            </Col>

            <Col span={12}>
              <Form.Item label="门票介质">
                {getFieldDecorator('physical', {
                  // 字典值是字符串，所以需要转化成字符串
                  initialValue: tempTicket.physical+'',
                  rules: [{ required: true, message: '请选择门票介质' }],
                  validateTrigger: 'onBlur',
                })(
                  <Select placeholder="请选择门票介质">
                    {physicalList.map((item, index) => (
                      <Select.Option key={item.dictValue}>
                        {item.dictName}
                      </Select.Option>
                    ))}
                  </Select>,
                )}
              </Form.Item>
            </Col>
          </Row>

          <Row gutter={2}>
            <Col span={12}>
              <Form.Item label="窗口售价">
                {getFieldDecorator('salePrice', {
                  initialValue: tempTicket.salePrice,
                  rules: [
                    { required: true, message: '请输入窗口售价' },
                    { pattern: '^[1-9]*[1-9][0-9]*$', message: '请输入整数'},
                    { max: 8, message: '不能超过8位数'}
                  ],
                  validateTrigger: 'onBlur',
                })(<Input prefix="￥" suffix="RMB" placeholder="请输入窗口售价" width={150}/>)}
              </Form.Item>
            </Col>

            <Col span={12}>
              <Form.Item label="票面打印价">
                {getFieldDecorator('printPrice', {
                  initialValue: tempTicket.printPrice,
                  rules: [
                    { required: true, message: '请输入票面打印价' },
                    { pattern: '^[1-9]*[1-9][0-9]*$', message: '请输入整数'},
                    { max: 8, message: '不能超过8位数'}
                  ],
                  validateTrigger: 'onBlur',
                })(<Input prefix="￥" suffix="RMB" placeholder="请输入票面打印价" width={150}/>)}
              </Form.Item>
            </Col>
          </Row>

          <Row gutter={2}>
            <Col span={12}>
              <Form.Item label="网络售价">
                {getFieldDecorator('networkPrice', {
                  initialValue: tempTicket.networkPrice,
                  rules: [
                    { required: true, message: '请输入网络售价' },
                    { pattern: '^[1-9]*[1-9][0-9]*$', message: '请输入整数'},
                    { max: 8, message: '不能超过8位数'}
                  ],
                  validateTrigger: 'onBlur',
                })(<Input prefix="￥" suffix="RMB" placeholder="请输入网络售价" width={150}/>)}
              </Form.Item>
            </Col>

            <Col span={12}>
              <Form.Item label="有效期限">
                {getFieldDecorator('deadline', {
                  initialValue: tempTicket.printPrice,
                  rules: [
                    { required: true, message: '请输入有效期限' },
                    { pattern: '^[1-9]*[1-9][0-9]*$', message: '请输入整数'},
                    { max: 3, message: '不能超过3位数'}
                  ],
                  validateTrigger: 'onBlur',
                })(<Input placeholder="请输入有效期限" width={150}/>)}
              </Form.Item>
            </Col>
          </Row>

          <Row gutter={2}>
            <Col span={12}>
              <Form.Item label="有效期单位">
                {getFieldDecorator('deadlineUnit', {
                  initialValue: tempTicket.deadlineUnit,
                  rules: [
                    { required: true, message: '请选择有效期单位' },
                  ],
                  validateTrigger: 'onBlur',
                })(
                  <Select placeholder="请选择有效期单位">
                    {deadlineUnitList.map((item, index) => (
                      <Select.Option key={item.dictValue}>
                        {item.dictName}
                      </Select.Option>
                    ))}
                  </Select>,
                )}
              </Form.Item>
            </Col>

            <Col span={12}>
              <Form.Item label="打印方式">
                {getFieldDecorator('printMethod', {
                  // 字典值是字符串，所以需要转化成字符串
                  initialValue: tempTicket.printMethod+'',
                  rules: [{ required: true, message: '请选择打印方式' }],
                  validateTrigger: 'onBlur',
                })(
                  <Select placeholder="请选择打印方式">
                    {printMethodList.map((item, index) => (
                      <Select.Option key={item.dictValue}>
                        {item.dictName}
                      </Select.Option>
                    ))}
                  </Select>,
                )}
              </Form.Item>
            </Col>
          </Row>

          <Row gutter={2}>
            <Col span={24}>
              <Form.Item label="参售日期" {...formItemLayoutColspan}>
                {getFieldDecorator('range-picker', {
                  initialValue: [moment(tempTicket.beginDate, 'yyyy-MM-dd'), moment(tempTicket.endDate, 'yyyy-MM-dd')],
                  rules: [{ type: 'array', required: true, message: '请选择参售日期' }],
                })(<RangePicker />)}
              </Form.Item>
            </Col>

          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create()(TicketForm);
