import React, { Component } from 'react';
import { Form, Input, message, Modal, Select } from 'antd';
import { connect } from 'dva';

const formItemLayout = {
  labelCol: {
    span: 5,
  },
  wrapperCol: {
    span: 15,
  },
};

@connect(({ travelAgency }) => ({
  travelAgency,
  handleResult: travelAgency.handleResult,
  tempTravelAgency: travelAgency.tempTravelAgency,
  pageData: travelAgency.pageData,
}))
class TravelAgencyForm extends Component {
  constructor(props) {
    super(props);
  }

  validateName = async (rule, value, callback) => {
    try {
      if (value) {
        const { dispatch, tempTravelAgency } = this.props;
        const param = {
          name: value,
        };
        if (tempTravelAgency.id) {
          param.id = tempTravelAgency.id;
        }
        return dispatch({
          type: 'travelAgency/checkNameExist',
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
      const { tempTravelAgency, dispatch, queryPage, changeEditVisible, refreshCurrentPage } = this.props;
      const action = tempTravelAgency.id ? 'travelAgency/update' : 'travelAgency/save';
      tempTravelAgency.id ? (fieldsValue.id = tempTravelAgency.id) : '';
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
          tempTravelAgency.id ? refreshCurrentPage() : queryPage();
          dispatch({
            type: 'travelAgency/clearData'
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
      tempTravelAgency,
    } = this.props;
    const { getFieldDecorator } = form;
    return (
      <Modal
        destroyOnClose
        title={title}
        visible={editVisible}
        onOk={this.handleSubmit}
        onCancel={() => changeEditVisible('', false)}
      >
        <Form {...formItemLayout}>
          <Form.Item label="旅行社名称">
            {getFieldDecorator('name', {
              initialValue: tempTravelAgency.name,
              rules: [
                { required: true, message: '请输入旅行社名称' },
                { max: 16, message: '旅行社名称不能超过16个字符' },
                { validator: this.validateName },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入旅行社名称" />)}
          </Form.Item>
          <Form.Item label="联系人">
            {getFieldDecorator('contact', {
              initialValue: tempTravelAgency.contact,
              rules: [
                { required: true, message: '请输入联系人' },
                { max: 8, message: '联系人不能超过8个字符' },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入联系人" />)}
          </Form.Item>
          <Form.Item label="联系电话">
            {getFieldDecorator('phone', {
              initialValue: tempTravelAgency.phone,
              rules: [
                { required: true, message: '请输入联系电话' },
                { pattern: `^(\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}$`, message: '联系电话格式错误' },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入联系电话" />)}
          </Form.Item>
          <Form.Item label="联系地址">
            {getFieldDecorator('address', {
              initialValue: tempTravelAgency.address,
              rules: [
                { max: 64, message: '联系地址不能超过64个字符' },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入联系地址" />)}
          </Form.Item>
          <Form.Item label="排序号">
            {getFieldDecorator('sortNum', {
              initialValue: tempTravelAgency.sortNum,
              rules: [
                { required: true, message: '请输入排序号' },
                { validator: this.checkSortNum },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入排序号" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create()(TravelAgencyForm);
