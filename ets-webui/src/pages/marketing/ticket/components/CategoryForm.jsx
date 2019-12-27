import React, {Component} from 'react';
import {Form, Input, message, Modal} from 'antd';
import {connect} from 'dva';

const formItemLayout = {
  labelCol: {
    span: 5,
  },
  wrapperCol: {
    span: 15,
  },
};

@connect(({ ticket }) => ({
  ticket,
  handleResult: ticket.handleResult,
  tempTicketCategory: ticket.tempTicketCategory,
}))
class CategoryForm extends Component {
  constructor(props) {
    super(props);
  }

  validateName = async (rule, value, callback) => {
    try {
      if (value) {
        const { dispatch, tempTicketCategory } = this.props;
        const param = {
          name: value,
        };
        if (tempTicketCategory.id) {
          param.id = tempTicketCategory.id;
        }
        return dispatch({
          type: 'ticket/checkCategoryNameExist',
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
      const { tempTicketCategory, dispatch, queryTree, changeEditTypeVisible } = this.props;
      const action = tempTicketCategory.id ? 'ticket/updateCategory' : 'ticket/saveCategory';
      tempTicketCategory.id ? (fieldsValue.id = tempTicketCategory.id) : '';
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
            type: 'ticket/clearTypeData'
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
      tempTicketCategory,
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
          <Form.Item label="票种名称">
            {getFieldDecorator('categoryName', {
              initialValue: tempTicketCategory.categoryName,
              rules: [
                { required: true, message: '请输入票种名称' },
                { max: 10, message: '票种名称不能超过10个字符' },
                { validator: this.validateName },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入票种名称" />)}
          </Form.Item>
          <Form.Item label="排序号">
            {getFieldDecorator('sortNum', {
              initialValue: tempTicketCategory.sortNum,
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

export default Form.create()(CategoryForm);
