import React, { Component } from 'react';
import { Form, Input, message, Modal, Transfer } from 'antd';
import { connect } from 'dva';

const formItemLayout = {
  labelCol: {
    span: 5,
  },
  wrapperCol: {
    span: 15,
  },
};

@connect(({ role }) => ({
  role,
  handleResult: role.handleResult,
  tempRole: role.tempRole,
  pageData: role.pageData,
  userList: role.userList,
}))
class EditForm extends Component {

  constructor(props) {
    super(props);
  }

  state= {

  };

  validateUserName = (rule, value, callback) => {
    try {
      if (value) {
        const { dispatch, tempRole } = this.props;
        const param = {
          userName: value,
        };
        if (tempRole.id) {
          param.id = tempRole.id;
        }
        dispatch({
          type: 'user/checkNameExist',
          payload: {
            ...param,
          },
        }).then(() => {
          const { handleResult } = this.props;
          if (handleResult.data) {
            callback('用户名已存在');
          } else {
            callback();
          }
        });
      }
      callback();
    } catch (e) {
      callback(e);
    }
  };

  handleSubmit = () => {
    const { form } = this.props;
    form.validateFieldsAndScroll((err, fieldsValue) => {
      if (err) return;
      const { tempRole, dispatch, queryPage, clearModelsData, changeEidtVisible } = this.props;
      const action = tempRole.id ? 'role/update' : 'role/save';
      tempRole.id ? (fieldsValue.id = tempRole.id) : '';
      dispatch({
        type: action,
        payload: {
          ...fieldsValue,
        },
      }).then(() => {
        const { handleResult, pageData } = this.props;
        if (handleResult.status) {
          clearModelsData();
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
    const { editVisible, title, form, changeEidtVisible, tempRole, userList } = this.props;
    const { getFieldDecorator } = form;

    return (
      <Modal
        destroyOnClose
        title={title}
        visible={editVisible}
        onOk={this.handleSubmit}
        onCancel={() => changeEidtVisible('', false)}
        width={700}
      >
        <Form {...formItemLayout}>
          <Form.Item label="角色名称">
            {getFieldDecorator('roleName', {
              initialValue: tempRole.roleName,
              rules: [
                { required: true, message: '请输入角色名称' },
                { max: 20, message: '用户名不能超过20个字符' },
                // { validator: this.validateUserName },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入角色名称" />)}
          </Form.Item>
          <Form.Item label="授权用户">
            {(<Transfer
              dataSource={userList}
              targetKeys={tempRole.userIdList}
              showSearch
              render={item => item.userName}
              titles=	{['未授权用户', '已授权用户']}
              listStyle={{
                width: 180,
                height: 400,
              }}
            />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create()(EditForm);
