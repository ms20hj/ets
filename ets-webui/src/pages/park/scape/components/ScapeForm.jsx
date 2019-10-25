import React, {Component} from 'react';
import {Form, Input, message, Modal, Select} from 'antd';
import {connect} from 'dva';

const formItemLayout = {
  labelCol: {
    span: 5,
  },
  wrapperCol: {
    span: 15,
  },
};

@connect(({ scape }) => ({
  scape,
  handleResult: scape.handleResult,
  tempScape: scape.tempScape,
  pageData: scape.pageData,
  scenicSpotList: scape.scenicSpotList,
}))
class ScapeForm extends Component {
  constructor(props) {
    super(props);
  }

  validateName = async (rule, value, callback) => {
    try {
      if (value) {
        const { dispatch, tempScape } = this.props;
        const param = {
          name: value,
        };
        if (tempScape.id) {
          param.id = tempScape.id;
        }
        return dispatch({
          type: 'scape/checkNameExist',
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
      const { tempScape, dispatch, queryPage, clearModelsData, changeEidtVisible } = this.props;
      const action = tempScape.id ? 'scape/update' : 'scape/save';
      tempScape.id ? (fieldsValue.id = tempScape.id) : '';
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
    const { editVisible, title, form, changeEidtVisible, tempScape, scenicSpotList } = this.props;
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
          <Form.Item label="景点名称">
            {getFieldDecorator('scapeName', {
              initialValue: tempScape.scapeName,
              rules: [
                { required: true, message: '请输入景点名称' },
                { max: 20, message: '景点名称不能超过20个字符' },
                { validator: this.validateName },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入景点名称" />)}
          </Form.Item>
          <Form.Item label="所属景区">
            {getFieldDecorator('scenicSpotId', {
              initialValue: tempScape.scenicSpotId,
              rules: [
                { required: true, message: '请选择所属景区' },
              ],
              validateTrigger: 'onBlur',
            })(<Select placeholder="请选择所属景区" >
              {
                scenicSpotList.map((item, index) =>
                <Select.Option key={item.id} value={item.id}>{item.spotName}</Select.Option>)
              }
            </Select>)}
          </Form.Item>
          <Form.Item label="景点描述">
            {getFieldDecorator('description', {
              initialValue: tempScape.description,
              rules: [
                { max: 100, message: '景点描述不能超过100个字符' },
              ],
            })(<Input.TextArea placeholder="请输入景点描述" rows={4}/>)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create()(ScapeForm);
