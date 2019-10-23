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
class RoleForm extends Component {

  constructor(props) {
    super(props);
  }

  validateName = async(rule, value, callback) => {
    try {
      if (value) {
        const { dispatch, tempRole } = this.props;
        const param = {
          roleName: value,
        };
        if (tempRole.id) {
          param.id = tempRole.id;
        }
        return dispatch({
          type: 'role/checkNameExist',
          payload: {
            ...param,
          },
        }).then(() => {
          const { handleResult } = this.props;
          if (handleResult.data) {
            return Promise.reject(new Error('角色名已存在'));
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
    form.validateFieldsAndScroll(['roleName',],(err, fieldsValue) => {
      if (err) return;
      const { tempRole, dispatch, queryPage, clearModelsData, changeEditVisible } = this.props;
      const action = tempRole.id ? 'role/update' : 'role/save';
      tempRole.id ? (fieldsValue.id = tempRole.id) : '';
      fieldsValue.userIdList = tempRole.userIdList;
      fieldsValue.menuIdList = tempRole.menuIdList;
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
          changeEditVisible('', false);
          message.success('保存成功');
        } else {
          message.error('保存失败');
        }
      });
    });
  };

  handleUserSelectChange = (targetKeys, direction, moveKeys) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'role/changeRoleUserIds',
      payload: targetKeys,
    });
  };

  handleCancel = () => {
    const {changeEditVisible, dispatch} = this.props;
    dispatch({
      type: 'role/clearData',
    });
    changeEditVisible('', false);
  };


  render() {
    const { editVisible, title, form, tempRole, userList } = this.props;
    const { getFieldDecorator } = form;

    return (
      <Modal
        destroyOnClose={true}
        title={title}
        visible={editVisible}
        onOk={this.handleSubmit}
        onCancel={this.handleCancel}
        width={700}
      >
        <Form {...formItemLayout}>
          <Form.Item label="角色名称">
            {getFieldDecorator('roleName', {
              initialValue: tempRole.roleName,
              rules: [
                { required: true, message: '请输入角色名称' },
                { max: 10, message: '角色名称不能超过10个字符' },
                { validator: this.validateName },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入角色名称" />)}
          </Form.Item>
          <Form.Item label="授权用户">
            {(<Transfer
              dataSource={userList}
              targetKeys={tempRole.userIdList}
              showSearch
              onChange={this.handleUserSelectChange}
              render={item => item.userName}
              rowKey={item => item.id}
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

export default Form.create()(RoleForm);
