import React, { Component } from 'react';
import { Form, Input, Modal, Radio } from 'antd';

const formItemLayout = {
  labelCol: {
    span: 5,
  },
  wrapperCol: {
    span: 15,
  },
};

const EditForm = props => {
  const { editVisible, title, form, handleSave, changeEidtVisible } = props;
  const { getFieldDecorator } = form;

  const okHandle = () => {
    form.validateFields((err, fieldsValue) => {
      if (err) return;
      form.resetFields();
      handleSave(fieldsValue);
    });
  };
  return (
    <Modal
      destroyOnClose
      title={title}
      visible={editVisible}
      onOk={okHandle}
      onCancel={() => changeEidtVisible('', false)}
    >
      <Form {...formItemLayout}>
        <Form.Item label="用户名">
          {getFieldDecorator('userName', {
            rules: [
              {
                required: true,
                message: '请输入用户名',
              },
              {
                pattern: /^[a-zA-Z\s]*$/,
                message: '用户名只能英文！',
              },
              { max: 20, message: '用户名不能超过20个字符' },
            ],
          })(<Input placeholder="请输入用户名" />)}
        </Form.Item>
        <Form.Item label="姓名">
          {getFieldDecorator('realName', {
            rules: [
              {
                required: true,
                message: '请输入姓名',
              },
              { max: 8, message: '用户名不能超过8个字符' },
            ],
          })(<Input placeholder="请输入姓名" />)}
        </Form.Item>
        <Form.Item label="密码">
          {getFieldDecorator('password', {
            rules: [
              {
                required: true,
                message: '请输入密码',
              },
              { min: 6, message: '密码不能少于6位' },
              { max: 32, message: '用户名不能超过8个字符' },
            ],
          })(<Input placeholder="请输入密码" type="password" />)}
        </Form.Item>
        <Form.Item label="性别">
          {getFieldDecorator('gender', {
            rules: [
              {
                required: true,
                message: '请选择性别',
              },
            ],
          })(
            <Radio.Group defaultValue="男">
              <Radio value="男">男</Radio>
              <Radio value="女">女</Radio>
            </Radio.Group>,
          )}
        </Form.Item>
        <Form.Item label="手机号">
          {getFieldDecorator('phone', {
            rules: [
              {
                required: true,
                message: '请输入手机号',
              },
              {
                pattern: /^1(3|4|5|7|8)\d{9}$/,
                message: '手机号格式错误',
              },
            ],
          })(<Input placeholder="请输入手机号" />)}
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default Form.create()(EditForm);