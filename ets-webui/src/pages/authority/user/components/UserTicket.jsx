import React, { Component } from 'react';
import {Col, Form, Input, message, Modal, Row, Transfer} from 'antd';
import { connect } from 'dva';

@connect(({ user }) => ({
  user,
  ticketList: user.ticketList,
  userTicketList: user.userTicketList,
  handleResult: user.handleResult,
}))
class UserTicket extends React.Component{

  constructor(props) {
    super(props);
  }

  handleUserSelectChange = (targetKeys, direction, moveKeys) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'user/changeUserTicketList',
      payload: targetKeys,
    });
  };

  handleSubmit = () => {
    const {saleAuthUserId, userTicketList, dispatch} = this.props;
    dispatch({
      type: 'user/authTicketUser',
      payload: {
        userId: saleAuthUserId,
        ticketIds: userTicketList
      }
    }).then( () => {
      const {changeSaleAuthVisible, handleResult} = this.props;
      if (handleResult.status) {
        changeSaleAuthVisible(false);
        message.success('保存成功')
      } else {
        message.error('保存失败');
      }
    });
  };

  render() {
    const {saleAuthVisible, ticketList, userTicketList, changeSaleAuthVisible} = this.props;
    return(
      <Modal
        destroyOnClose={true}
        title='销售授权'
        visible={saleAuthVisible}
        onOk={this.handleSubmit}
        onCancel={() => changeSaleAuthVisible(false)}
        width={700}
      >
        <Row>
          <Col span={24}>
            <Transfer
              dataSource={ticketList}
              targetKeys={userTicketList}
              showSearch
              onChange={this.handleUserSelectChange}
              render={item => item.ticketName}
              rowKey={item => item.id}
              titles={['未授权票类', '已授权票类']}
              listStyle={{
                width: 280,
                height: 400,
              }}
            />
          </Col>
        </Row>

      </Modal>
    );
  }
}

export default Form.create()(UserTicket);
