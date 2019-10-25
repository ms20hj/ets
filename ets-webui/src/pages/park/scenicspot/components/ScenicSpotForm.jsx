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

@connect(({ scenicSpot }) => ({
  scenicSpot,
  handleResult: scenicSpot.handleResult,
  tempScenicSpot: scenicSpot.tempScenicSpot,
  pageData: scenicSpot.pageData,
}))
class ScenicSpotForm extends Component {
  constructor(props) {
    super(props);
  }

  validateName = async (rule, value, callback) => {
    try {
      if (value) {
        const { dispatch, tempScenicSpot } = this.props;
        const param = {
          name: value,
        };
        if (tempScenicSpot.id) {
          param.id = tempScenicSpot.id;
        }
        return dispatch({
          type: 'scenicSpot/checkNameExist',
          payload: {
            ...param,
          },
        }).then(() => {
          const { handleResult } = this.props;
          if (handleResult.data) {
            return Promise.reject(new Error('景区名称已存在'));
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
      const { tempScenicSpot, dispatch, queryPage, clearModelsData, changeEidtVisible } = this.props;
      const action = tempScenicSpot.id ? 'scenicSpot/update' : 'scenicSpot/save';
      tempScenicSpot.id ? (fieldsValue.id = tempScenicSpot.id) : '';
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
    const { editVisible, title, form, changeEidtVisible, tempScenicSpot } = this.props;
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
          <Form.Item label="景区名称">
            {getFieldDecorator('spotName', {
              initialValue: tempScenicSpot.spotName,
              rules: [
                { required: true, message: '请输入景区名称' },
                { max: 20, message: '景区名称不能超过20个字符' },
                { validator: this.validateName },
              ],
              validateTrigger: 'onBlur',
            })(<Input placeholder="请输入景区名称" />)}
          </Form.Item>
          <Form.Item label="景区描述">
            {getFieldDecorator('description', {
              initialValue: tempScenicSpot.description,
              rules: [
                { max: 100, message: '景区描述不能超过100个字符' },
              ],
            })(<Input.TextArea placeholder="请输入景区描述" rows={4}/>)}
          </Form.Item>
        </Form>
      </Modal>
    );
  }
}

export default Form.create()(ScenicSpotForm);
