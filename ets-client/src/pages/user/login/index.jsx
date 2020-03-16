import {Alert, Button, Form, Input} from 'antd';
import React, {Component} from 'react';
import {connect} from 'dva';
import styles from './style.less';

@connect(({ login, loading }) => ({
  login,
  loginResult: login.loginResult,
}))
@Form.create()
export default class Login extends Component{

  componentDidMount() {
    const {dispatch} = this.props;
    if (dispatch) {
      dispatch({
        type: 'login/getPublicKey',
      });
    }
  }

  handleSubmit = () => {
    const {form} = this.props;
    form.validateFields((err, values) => {
      if (err) return;
      const { dispatch } = this.props;
      dispatch({
        type: 'login/login',
        payload: { ...values, },
      });
    });
  };

  renderLoginMsg = msg => {
    return (
      <Alert
        style={{
          marginBottom: 24,
        }}
        message={msg}
        type="error"
        showIcon
      />
    );
  };

  render() {
    const {loginResult} = this.props;
    const {getFieldDecorator} = this.props.form;
    return (
      <div className={styles.main}>
        <Form onSubmit={this.handleSubmit}>
          <Form.Item>
            {getFieldDecorator('userName', {
              rules: [{ required: true, message: '请输入登录账号' }],
            })(
              <Input placeholder="登录账号"
              />,
            )}
          </Form.Item>
          <Form.Item>
            {getFieldDecorator('password', {
              rules: [{ required: true, message: '请输入登录密码' }],
            })(
              <Input type="password" placeholder="登录账号"
              />,
            )}
          </Form.Item>
          {!loginResult.status && this.renderLoginMsg(loginResult.message)}
          <Form.Item>
            <Button type="primary" htmlType="submit">
              登录
            </Button>
          </Form.Item>
        </Form>
      </div>
    );
  }
};
