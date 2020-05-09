import React, {Component} from 'react';
import {Form, Input, InputNumber, Modal, Select} from 'antd';
import {connect} from "dva";
import styles from "./MiddlePart.less";

const formItemLayout = {
  labelCol: {
    span: 5,
  },
  wrapperCol: {
    span: 15,
  },
};
@connect( ({order, sale}) => ({
  saleOrderTypeList: order.saleOrderTypeList,
  touristList: sale.touristList,
  ticketList: sale.ticketList,
  discountList: sale.discountList,
  tempSaleOrderType: order.tempSaleOrderType,
}))
@Form.create()
export default class SaleForm extends Component{


  /**
   * 选中游客类型
   * @param val
   * @param option
   */
  selectTourist = (val, option) => {
    const {tempSaleOrderType} = this.props;
    tempSaleOrderType.touristId = val;
    tempSaleOrderType.touristName = option.props.children;
    console.log(tempSaleOrderType);
  };
  /**
   * 选中门票处理
   * @param val
   * @param option
   */
  selectTicket = (val) => {
    const {tempSaleOrderType, ticketList, } = this.props;
    const ticket = ticketList.find((item) => {
      if (item.id === val)  {
        return item;
      }
    });
    tempSaleOrderType.ticketId = ticket.id;
    tempSaleOrderType.ticketName = ticket.ticketName;
    tempSaleOrderType.price = ticket.salePrice;
    tempSaleOrderType.amount = tempSaleOrderType.price * tempSaleOrderType.personCount;
    tempSaleOrderType.ticket = ticket;
    console.log(tempSaleOrderType);
  };

  selectDiscount = val => {
    const { discountList, tempSaleOrderType} = this.props;
    const discount = discountList.find((item) => {
      if (item.id === val)  {
        return item;
      }
    });
    tempSaleOrderType.price = tempSaleOrderType.ticket.salePrice;
    tempSaleOrderType.discountId = discount.id;
    tempSaleOrderType.discountName = discount.discName;
    // 0：打折（如：8折、九折）
    if (discount.discountWay === 0) {
      tempSaleOrderType.price = tempSaleOrderType.price * discount.discountScale;
    }
    //1：减免（如：减10/人，减15/人）
    if (discount.discountWay === 1) {
      tempSaleOrderType.price = tempSaleOrderType.price - discount.discountScale;
    }
    //2：按照优惠金额销售（如：直接￥50）
    if (discount.discountWay === 2) {
      tempSaleOrderType.price = discount.discountScale;
    }
    tempSaleOrderType.amount = tempSaleOrderType.price * tempSaleOrderType.personCount;
    tempSaleOrderType.discount = discount;
  };

  changePerson = count => {
    const {tempSaleOrderType} = this.props;
    tempSaleOrderType.personCount = count;
    // 判断一票一人
    if (tempSaleOrderType.ticket !== null && item.tempSaleOrderType.printMethod === 0) {
      tempSaleOrderType.ticketCount = count;
    } else {
      tempSaleOrderType.ticketCount = 1;
    }
  };

  okHandle = () => {
    const {form, changeSaleVisible, tempSaleOrderType, saleOrderTypeList, dispatch} = this.props;
    form.validateFieldsAndScroll((err, fieldsValue) => {
      if (err) return;
      let add = true;
      const data = saleOrderTypeList.map(item => {
        if (item.ticketId === tempSaleOrderType.ticketId && item.touristId === tempSaleOrderType.touristId &&
            item.discountId === tempSaleOrderType.discountId) {
          item.personCount = item.personCount + tempSaleOrderType.personCount;
          item.amount = item.personCount * item.price;
          // 判断一票一人
          if (tempSaleOrderType.ticket.printMethod === 0) {
            item.ticketCount = item.personCount;
          }
          add = false;
        }
        return item;
      });
      if (add) {
        tempSaleOrderType.key = data.length.toString();
        data.push(tempSaleOrderType);
      }
      dispatch({
        type: 'order/refreshOrderTypeList',
        payload: data,
      });
      changeSaleVisible(false);
    });
  };

  render() {
    const {touristList, ticketList, discountList, tempSaleOrderType, form, saleVisible, changeSaleVisible} = this.props;
    const {getFieldDecorator} = form;

    return(
      <Modal
        destroyOnClose
        title='销售信息'
        visible={saleVisible}
        onCancel={() => changeSaleVisible(false)}
        onOk={this.okHandle}
      >
        <Form {...formItemLayout}>
          <Form.Item label="游客类型">
            {getFieldDecorator('touristName', {
              initialValue: tempSaleOrderType.touristName,
              rules: [
                { required: true, message: '请选择游客类型' },
              ],
              validateTrigger: 'onBlur',
            })(<Select onSelect={this.selectTourist} placeholder='请选择游客类型'>
              {touristList.map( (item) => (
                <Select.Option key={item.id} value={item.id}>{item.touristName}</Select.Option>
              ))}
            </Select>)}
          </Form.Item>
          <Form.Item label="票类">
            {getFieldDecorator('ticketName', {
              initialValue: tempSaleOrderType.ticketName,
              rules: [
                { required: true, message: '请选择票类' },
              ],
              validateTrigger: 'onBlur',
            })(<Select onSelect={this.selectTicket} placeholder='请选择票类'>
              {ticketList.map((item) => (
                <Select.Option key={item.id} value={item.id}>{item.ticketName}</Select.Option>
              ))}
            </Select>)}
          </Form.Item>
          <Form.Item label="单价 ">
            {getFieldDecorator('price', {
              initialValue: tempSaleOrderType.price,
              validateTrigger: 'onBlur',
            })(<Input readOnly={true}/>)}
          </Form.Item>
          <Form.Item label="人数">
            {getFieldDecorator('personCount', {
              initialValue: tempSaleOrderType.personCount,
              rules: [
                { required: true, message: '请选择人数' },
              ],
              validateTrigger: 'onBlur',
            })(<InputNumber min={1} max={500} style={{width: '100%'}} onChange={this.changePerson} />)}
          </Form.Item>
          <Form.Item label="票数 ">
            {getFieldDecorator('ticketCount', {
              initialValue: tempSaleOrderType.ticketCount,
              validateTrigger: 'onBlur',
            })(<Input readOnly={true}/>)}
          </Form.Item>
          <Form.Item label="优惠方式">
            {getFieldDecorator('discountName', {
              initialValue: tempSaleOrderType.discountName,
            })(<Select onSelect={this.selectDiscount} placeholder='请选择优惠方式'>
              {discountList.map((item) => (
                <Select.Option key={item.id} value={item.id}>{item.discName}</Select.Option>
              ))}
            </Select>)}
          </Form.Item>
          <Form.Item label="金额 ">
            {getFieldDecorator('amount', {
              initialValue: tempSaleOrderType.amount,
              validateTrigger: 'onBlur',
            })(<Input readOnly={true}/>)}
          </Form.Item>
        </Form>
      </Modal>
    )
  }
}
