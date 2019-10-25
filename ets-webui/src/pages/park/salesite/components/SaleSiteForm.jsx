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

@connect(({ saleSite }) => ({
  saleSite,
  handleResult: saleSite.handleResult,
  tempSaleSite: saleSite.tempSaleSite,
  pageData: saleSite.pageData,
  scenicSpotList: saleSite.scenicSpotList,
}))
class SaleSiteForm extends Component {
  constructor(props) {
    super(props);
  }

  validateName = async (rule, value, callback) => {
    try {
      if (value) {
        const { dispatch, tempSaleSite } = this.props;
        const param = {
          name: value,
        };
        if (tempSaleSite.id) {
          param.id = tempSaleSite.id;
        }
        return dispatch({
          type: 'saleSite/checkNameExist',
          payload: {
            ...param,
          },
        }).then(() => {
          const { handleResult } = this.props;
          if (handleResult.data) {
            return Promise.reject(new Error('景点名称已存在'));
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
      const { tempSaleSite, dispatch, queryPage, clearModelsData, changeEidtVisible } = this.props;
      const action = tempSaleSite.id ? 'saleSite/update' : 'saleSite/save';
      tempSaleSite.id ? (fieldsValue.id = tempSaleSite.id) : '';
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
    const {
      editVisible,
      title,
      form,
      changeEidtVisible,
      tempSaleSite,
      scenicSpotList,
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
          <Form.Item label="站点名称">
            {getFieldDecorator('siteName', {
              initialValue: tempSaleSite.siteName,
              rules: [
                { required: true, message: '请输入站点名称' },
                { max: 20, message: '站点名称不能超过20个字符' },
                { validator: this.validateName },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入站点名称" />)}
          </Form.Item>
          <Form.Item label="所属景区">
            {getFieldDecorator('scenicSpotId', {
              initialValue: tempSaleSite.scenicSpotId,
              rules: [{ required: true, message: '请选择所属景区' }],
              validateTrigger: 'onBlur',
            })(
              <Select placeholder="请选择所属景区">
                {scenicSpotList.map((item, index) => (
                  <Select.Option key={item.id} value={item.id}>
                    {item.spotName}
                  </Select.Option>
                ))}
              </Select>,
            )}
          </Form.Item>
          <Form.Item label="站点描述">
            {getFieldDecorator('description', {
              initialValue: tempSaleSite.description,
              rules: [{ max: 100, message: '站点描述不能超过100个字符' }],
            })(<Input.TextArea placeholder="请输入站点描述" rows={4} />)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create()(SaleSiteForm);
