import React, {Component} from 'react';
import {Col, Form, Input, InputNumber, message, Modal, Row, Select, Switch, Transfer, DatePicker} from 'antd';
import {connect} from 'dva';
import moment from 'moment';

const { RangePicker } = DatePicker;
const formItemLayout = {
  labelCol: {
    span: 4,
  },
  wrapperCol: {
    span: 20,
  },
};

@connect(({ discount }) => ({
  discount,
  handleResult: discount.handleResult,
  tempDiscount: discount.tempDiscount,
  discountWayList: discount.discountWayList,
  touristList: discount.touristList,
  ticketList: discount.ticketList,
  travelAgencyList: discount.travelAgencyList,
  touristIds: discount.touristIds,
  ticketIds: discount.ticketIds,
  travelAgencyIds: discount.travelAgencyIds,
}))
class DiscountForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      contentWidth: document.documentElement.clientWidth * 0.6,
      transferWidth: document.documentElement.clientWidth * 0.1,
    }
  }

  componentDidMount() {
    const {dispatch} = this.props;
    dispatch({
      type: 'discount/getSelectList'
    });
  }

  validateName = async (rule, value, callback) => {
    try {
      if (value) {
        const { dispatch, tempDiscount } = this.props;
        const param = {
          name: value,
        };
        if (tempDiscount.id) {
          param.id = tempDiscount.id;
        }
        return dispatch({
          type: 'discount/checkNameExist',
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


  handleSubmit = () => {
    const { form } = this.props;
    form.validateFieldsAndScroll((err, fieldsValue) => {
      if (err) return;
      const { tempDiscount, dispatch, changeEditVisible, touristIds, ticketIds, travelAgencyIds } = this.props;
      const action = tempDiscount.id ? 'discount/save' : 'discount/update';
      tempDiscount.id ? (fieldsValue.id = tempDiscount.id) : '';
      fieldsValue.touristIds = touristIds;
      fieldsValue.ticketIds = ticketIds;
      fieldsValue.travelAgencyIds = travelAgencyIds;

      const rangDate = form.getFieldValue('rangePicker');
      fieldsValue.beginDate = rangDate[0].format('YYYY-MM-DD') + ' 00:00:00';
      fieldsValue.endDate = rangDate[1].format('YYYY-MM-DD') + ' 23:59:59';
      delete fieldsValue.rangePicker;
      dispatch({
        type: action,
        payload: {
          ...fieldsValue,
        },
      }).then(() => {
        const { handleResult, dispatch, queryPage } = this.props;
        if (handleResult.status) {
          queryPage();
          message.success('保存成功');
          dispatch({
            type: 'discount/clearData'
          });
          changeEditVisible('', false);
        } else {
          message.error('保存失败');
        }
      });
    });
  };

  changeLimitCount = checked => {

  };

  handleTouristSelectChange = (targetKeys, direction, moveKeys) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'discount/changeTouristSelected',
      payload: targetKeys,
    });
  };

  handleTicketSelectChange = (targetKeys, direction, moveKeys) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'discount/changeTicketSelected',
      payload: targetKeys,
    });
  };

  handleTravelAgencySelectChange = (targetKeys, direction, moveKeys) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'discount/changeTravelAgencySelected',
      payload: targetKeys,
    });
  };


  render() {
    const {
      editVisible,
      title,
      form,
      changeEditVisible,
      tempDiscount,
      discountWayList,
      touristList,
      ticketList,
      travelAgencyList,
      touristIds,
      ticketIds,
      travelAgencyIds,
    } = this.props;
    const { getFieldDecorator } = form;
    return (
      <Modal
        destroyOnClose
        title={title}
        visible={editVisible}
        onOk={this.handleSubmit}
        onCancel={() => changeEditVisible('', false)}
        width={this.state.contentWidth}
      >
        <Form {...formItemLayout}>
          <Row>
            <Col span={12}>
              <Row>
                <Col span={24}>
                  <Form.Item label="优惠名称">
                    {getFieldDecorator('discName', {
                      initialValue: tempDiscount.discName,
                      rules: [
                        { required: true, message: '请输入优惠名称' },
                        { validator: this.validateName },
                      ],
                      validateTrigger: 'onBlur',
                    })(<Input placeholder="请输入优惠名称" maxLength={16} width={150} />)}
                  </Form.Item>
                </Col>
              </Row>
              <Row>
                <Col span={24}>
                  <Form.Item label="人数限制">
                    {getFieldDecorator('limitCount', {
                      valuePropName: 'checked',
                      initialValue: tempDiscount.limitCount,
                    })(<Switch checkedChildren="限制" unCheckedChildren="不限制" onChange={this.changeLimitCount} />)}
                  </Form.Item>
                </Col>
              </Row>
              <Row>
                <Col span={24}>
                    <Form.Item label="人数">
                      {getFieldDecorator('limitBegin', {
                        initialValue: tempDiscount.limitBegin,
                      })(<InputNumber min={1} max={999} placeholder="最低人数" />)}
                      &nbsp;至&nbsp;
                      {getFieldDecorator('limitEnd', {
                        initialValue: tempDiscount.limitEnd,
                      })(<InputNumber min={1} max={999} placeholder="最高人数" />)}
                    </Form.Item>
                </Col>
              </Row>
              <Row>
                <Col>
                  <Form.Item label="优惠方式">
                    {getFieldDecorator('discountWay', {
                      initialValue: tempDiscount.discountWay+'',
                      rules: [{ required: true, message: '请选择优惠方式' }],
                      validateTrigger: 'onBlur',
                    })(
                      <Select placeholder="请选择优惠方式">
                        {discountWayList.map((item, index) => (
                          <Select.Option key={item.dictValue}>{item.dictName}</Select.Option>
                        ))}
                      </Select>
                    )}
                  </Form.Item>
                </Col>
              </Row>
              <Row>
                <Col>
                  <Form.Item label="优惠比例">
                    {getFieldDecorator('discountScale', {
                      initialValue: tempDiscount.discountScale,
                      rules: [{ required: true, message: '请选择优惠比例' }],
                      validateTrigger: 'onBlur',
                    })(<InputNumber min={0.01} max={999} placeholder="请选择优惠比例" />)}
                  </Form.Item>
                </Col>
              </Row>
              <Row>
                <Col span={24}>
                  <Form.Item label="启用状态">
                    {getFieldDecorator('status', {
                      valuePropName: 'checked',
                      initialValue: tempDiscount.status,
                    })(<Switch checkedChildren="启用" unCheckedChildren="禁用" />)}
                  </Form.Item>
                </Col>
              </Row>
              <Row>
                <Col span={24}>
                  <Form.Item label="优惠日期">
                    {getFieldDecorator('rangePicker', {
                      initialValue: [
                        moment(
                          tempDiscount.beginDate === '' ? new Date() : tempDiscount.beginDate,
                          'yyyy-MM-dd',
                        ),
                        moment(
                          tempDiscount.endDate === '' ? new Date() : tempDiscount.endDate,
                          'yyyy-MM-dd',
                        ),
                      ],
                      rules: [{ type: 'array', required: true, message: '请选择优惠日期' }],
                    })(
                      <RangePicker
                        onChange={v => {
                          console.log('RangePicker', v);
                        }}
                      />,
                    )}
                  </Form.Item>
                </Col>
              </Row>
            </Col>
            <Col span={12}>
              <Form.Item label="参与游客">
                <Transfer
                  dataSource={touristList}
                  targetKeys={touristIds}
                  showSearch
                  onChange={this.handleTouristSelectChange}
                  render={item => item.touristName}
                  rowKey={item => item.id}
                  titles={['未选中游客', '已选中游客']}
                  listStyle={{
                    width: this.state.transferWidth,
                    height: 400,
                  }}
                />
              </Form.Item>
            </Col>
          </Row>

          <Row>
            <Col span={12}>
              <Form.Item label="参与门票">
                <Transfer
                  dataSource={ticketList}
                  targetKeys={ticketIds}
                  showSearch
                  onChange={this.handleTicketSelectChange}
                  render={item => item.ticketName}
                  rowKey={item => item.id}
                  titles={['未选中门票', '已选中门票']}
                  listStyle={{
                    width: this.state.transferWidth,
                    height: 400,
                  }}
                />
              </Form.Item>
            </Col>
            <Col span={12}>
              <Form.Item label="参与旅行社">
                <Transfer
                  dataSource={travelAgencyList}
                  targetKeys={travelAgencyIds}
                  showSearch
                  onChange={this.handleTravelAgencySelectChange}
                  render={item => item.name}
                  rowKey={item => item.id}
                  titles={['未选中旅行社', '已选中旅行社']}
                  listStyle={{
                    width: this.state.transferWidth,
                    height: 400,
                  }}
                />
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
}

export default Form.create()(DiscountForm);
