import React, {Component} from 'react';
import {Form, Input, message, Modal, Radio} from 'antd';
import {connect} from 'dva';

const formItemLayout = {
  labelCol: {
    span: 5,
  },
  wrapperCol: {
    span: 15,
  },
};

@connect(({ user, loading }) => ({
  user,
  handleResult: user.handleResult,
  tempUser: user.tempUser,
  pageData: user.pageData,
}))
class EditForm extends Component {
  constructor(props) {
    super(props);
  }

  validateName = async (rule, value, callback) => {
    try {
      if (value) {
        const { dispatch, tempUser } = this.props;
        const param = {
          userName: value,
        };
        if (tempUser.id) {
          param.id = tempUser.id;
        }
        return dispatch({
          type: 'user/checkNameExist',
          payload: {
            ...param,
          },
        }).then(() => {
          const { handleResult } = this.props;
          if (handleResult.data) {
            return Promise.reject(new Error('用户名已存在'));
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

  compareToPassword = (rule, value, callback) => {
    if (value) {
      const { form } = this.props;
      const password = form.getFieldValue('password');
      if (value !== password) {
        callback('密码和确认密码不一致');
      } else {
        callback();
      }
    }
    callback();
  };

  compareToCfmPassword = (rule, value, callback) => {
    if (value) {
      const { form } = this.props;
      form.validateFields(['cfmPassword'], { force: true });
    }
    callback();
  };

  handleSubmit = () => {
    const { form } = this.props;
    form.validateFieldsAndScroll((err, fieldsValue) => {
      if (err) return;
      const { tempUser, dispatch, queryPage, changeEidtVisible } = this.props;
      const action = tempUser.id ? 'user/update' : 'user/save';
      tempUser.id ? (fieldsValue.id = tempUser.id) : '';
      dispatch({
        type: action,
        payload: {
          ...fieldsValue,
        },
      }).then(() => {
        const { handleResult, pageData } = this.props;
        if (handleResult.status) {
          const param = {
            current: 1,
            size: pageData.pagination.size,
          };
          queryPage(param);
          changeEidtVisible('', false);
          message.success('保存成功');
        } else {
          message.error('保存失败');
        }
      });
    });
  };

  render() {
    const { editVisible, title, form, changeEidtVisible, tempUser } = this.props;
    const { getFieldDecorator } = form;

    return (
      <Modal
        destroyOnClose
        title={title}
        visible={editVisible}
        onOk={this.handleSubmit}
        onCancel={() => changeEidtVisible('', false)}
      >
        <Form {...formItemLayout}>
          <Form.Item label="用户名">
            {getFieldDecorator('userName', {
              initialValue: tempUser.userName,
              rules: [
                { required: true, message: '请输入用户名' },
                { pattern: /^[a-zA-Z\s]*$/, message: '用户名只能英文！' },
                { max: 20, message: '用户名不能超过20个字符' },
                { validator: this.validateName },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入用户名" />)}
          </Form.Item>
          <Form.Item label="姓名">
            {getFieldDecorator('realName', {
              initialValue: tempUser.realName,
              rules: [
                { required: true, message: '请输入姓名' },
                { max: 8, message: '用户名不能超过8个字符' },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入姓名" />)}
          </Form.Item>
          <Form.Item label="密码">
            {getFieldDecorator('password', {
              initialValue: tempUser.password,
              rules: [
                { required: true, message: '请输入密码' },
                { min: 6, message: '密码不能少于6位' },
                { max: 32, message: '密码不能超过32个字符' },
                { validator: this.compareToCfmPassword },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入密码" type="password" />)}
          </Form.Item>

          <Form.Item label="确认密码">
            {getFieldDecorator('cfmPassword', {
              initialValue: tempUser.password,
              rules: [
                { required: true, message: '请输入确认密码' },
                { min: 6, message: '确认密码不能少于6位' },
                { max: 32, message: '确认密码不能超过32个字符' },
                { validator: this.compareToPassword },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入确认密码" type="password" />)}
          </Form.Item>

          <Form.Item label="手机号">
            {getFieldDecorator('phone', {
              initialValue: tempUser.phone,
              rules: [
                { required: true, message: '请输入手机号' },
                { pattern: /^1(3|4|5|7|8)\d{9}$/, message: '手机号格式错误' },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入手机号" />)}
          </Form.Item>

          <Form.Item label="性别">
            {getFieldDecorator('gender', {
              initialValue: tempUser.gender,
              rules: [{ required: true, message: '请选择性别' }],
              validateTrigger: 'onBlur',
            })(
              <Radio.Group>
                <Radio value="男">男</Radio>
                <Radio value="女">女</Radio>
              </Radio.Group>,
            )}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create()(EditForm);
