import React, { Component } from 'react';
import { Form, Input, message, Modal } from 'antd';
import { connect } from 'dva';

const formItemLayout = {
  labelCol: {
    span: 5,
  },
  wrapperCol: {
    span: 15,
  },
};

@connect(({ tourist }) => ({
  tourist,
  handleResult: tourist.handleResult,
  tempTourist: tourist.tempTourist,
}))
class TouristForm extends Component {
  constructor(props) {
    super(props);
  }

  validateName = async (rule, value, callback) => {
    try {
      if (value) {
        const { dispatch, tempTourist } = this.props;
        const param = {
          name: value,
        };
        if (tempTourist.id) {
          param.id = tempTourist.id;
        }
        return dispatch({
          type: 'tourist/checkNameExist',
          payload: {
            ...param,
          },
        }).then(() => {
          const { handleResult } = this.props;
          if (handleResult.data) {
            return Promise.reject(new Error('游客类型已存在'));
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
    const { form } = this.props;
    form.validateFieldsAndScroll((err, fieldsValue) => {
      if (err) return;
      const { tempTourist, dispatch, queryPage, changeEditVisible, refreshCurrentPage } = this.props;
      const action = tempTourist.id ? 'tourist/update' : 'tourist/save';
      tempTourist.id ? (fieldsValue.id = tempTourist.id) : '';
      dispatch({
        type: action,
        payload: {
          ...fieldsValue,
        },
      }).then(() => {
        const { handleResult } = this.props;
        if (handleResult.status) {
          tempTourist.id ? refreshCurrentPage() : queryPage();
          changeEditVisible('', false);
          message.success('保存成功');
        } else {
          message.error('保存失败');
        }
      });
    });
  };

  render() {
    const { editVisible, title, form, changeEditVisible, tempTourist } = this.props;
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
          <Form.Item label="游客类型">
            {getFieldDecorator('touristName', {
              initialValue: tempTourist.touristName,
              rules: [
                { required: true, message: '请输入游客类型' },
                { max: 8, message: '游客类型不能超过8个字符' },
                { validator: this.validateName },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入游客类型" />)}
          </Form.Item>
          <Form.Item label="排序号">
            {getFieldDecorator('sortNum', {
              initialValue: tempTourist.sortNum,
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

export default Form.create()(TouristForm);
