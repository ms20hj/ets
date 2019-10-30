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

@connect(({ saleWindow }) => ({
  saleWindow,
  handleResult: saleWindow.handleResult,
  tempSaleWindow: saleWindow.tempSaleWindow,
  pageData: saleWindow.pageData,
  saleSiteList: saleWindow.saleSiteList,
}))
class SaleWindowForm extends Component {
  constructor(props) {
    super(props);
  }

  validateName = async (rule, value, callback) => {
    try {
      if (value) {
        const { dispatch, tempSaleWindow } = this.props;
        const param = {
          name: value,
        };
        if (tempSaleWindow.id) {
          param.id = tempSaleWindow.id;
        }
        return dispatch({
          type: 'saleWindow/checkNameExist',
          payload: {
            ...param,
          },
        }).then(() => {
          const { handleResult } = this.props;
          if (handleResult.data) {
            return Promise.reject(new Error('窗口名称已存在'));
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

  validateMac = async (rule, value, callback) => {
    try {
      if (value) {
        const { dispatch, tempSaleWindow } = this.props;
        const param = {
          mac: value,
        };
        if (tempSaleWindow.id) {
          param.id = tempSaleWindow.id;
        }
        return dispatch({
          type: 'saleWindow/checkMacExist',
          payload: {
            ...param,
          },
        }).then(() => {
          const { handleResult } = this.props;
          if (handleResult.data) {
            return Promise.reject(new Error('Mac已存在'));
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
      const { tempSaleWindow, dispatch, queryPage, changeEidtVisible } = this.props;
      const action = tempSaleWindow.id ? 'saleWindow/update' : 'saleWindow/save';
      tempSaleWindow.id ? (fieldsValue.id = tempSaleWindow.id) : '';
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
    const {
      editVisible,
      title,
      form,
      changeEidtVisible,
      tempSaleWindow,
      saleSiteList,
    } = this.props;
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
          <Form.Item label="窗口名称">
            {getFieldDecorator('windowName', {
              initialValue: tempSaleWindow.windowName,
              rules: [
                { required: true, message: '请输入窗口名称' },
                { max: 20, message: '窗口名称不能超过20个字符' },
                { validator: this.validateName },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入窗口名称" />)}
          </Form.Item>
          <Form.Item label="所属站点">
            {getFieldDecorator('saleSiteId', {
              initialValue: tempSaleWindow.saleSiteId,
              rules: [{ required: true, message: '请选择所属站点' }],
              validateTrigger: 'onBlur',
            })(
              <Select placeholder="请选择所属站点">
                {saleSiteList.map((item, index) => (
                  <Select.Option key={item.id} value={item.id}>
                    {item.siteName}
                  </Select.Option>
                ))}
              </Select>,
            )}
          </Form.Item>
          <Form.Item label="Mac地址">
            {getFieldDecorator('mac', {
              initialValue: tempSaleWindow.mac,
              rules: [
                { required: true, message: '请输入Mac地址' },
                { validator: this.validateMac },
                { pattern: `([A-Fa-f0-9]{2}-){5}[A-Fa-f0-9]{2}`, message: 'Mac地址格式错误'}
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入Mac地址" />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create()(SaleWindowForm);
