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
  tempTravelType: travelAgency.tempTravelType,
}))
class TravelTypeForm extends Component {
  constructor(props) {
    super(props);
  }

  validateName = async (rule, value, callback) => {
    try {
      if (value) {
        const { dispatch, tempTravelType } = this.props;
        const param = {
          name: value,
        };
        if (tempTravelType.id) {
          param.id = tempTravelType.id;
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


  handleSubmit = () => {
    const { form, parentId } = this.props;
    debugger;
    form.validateFieldsAndScroll((err, fieldsValue) => {
      if (err) return;
      const { tempTravelType, dispatch, queryTree, changeEditTypeVisible } = this.props;
      const action = tempTravelType.id ? 'travelAgency/update' : 'travelAgency/save';
      tempTravelType.id ? (fieldsValue.id = tempTravelType.id) : '';
      fieldsValue.parentId = parentId;
      fieldsValue.level = 2; //类别等级为2
      dispatch({
        type: action,
        payload: {
          ...fieldsValue,
        },
      }).then(() => {
        const { handleResult, dispatch } = this.props;
        if (handleResult.status) {
          message.success('保存成功');
          dispatch({
            type: 'travelAgency/clearTypeData'
          });
          queryTree();
          changeEditTypeVisible('', false);
        } else {
          message.error('保存失败');
        }
      });
    });
  };

  render() {
    const {
      editTypeVisible,
      title,
      form,
      changeEditTypeVisible,
      tempTravelType,
    } = this.props;
    const { getFieldDecorator } = form;

    return (
      <Modal
        destroyOnClose
        title={title}
        visible={editTypeVisible}
        onOk={this.handleSubmit}
        onCancel={() => changeEditTypeVisible('', false)}
      >
        <Form {...formItemLayout}>
          <Form.Item label="类别名称">
            {getFieldDecorator('name', {
              initialValue: tempTravelType.name,
              rules: [
                { required: true, message: '请输入类别名称' },
                { max: 10, message: '类别名称不能超过10个字符' },
                { validator: this.validateName },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入类别名称" />)}
          </Form.Item>
          <Form.Item label="排序号">
            {getFieldDecorator('sortNum', {
              initialValue: tempTravelType.sortNum,
              rules: [
                { required: true, message: '请输入排序号' },
                { max: 4, message: '排序号不能超过4位数' },
                { pattern: `^[0-9]*$`, message: '排序号格式错误'}
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入排序号" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create()(TravelTypeForm);
