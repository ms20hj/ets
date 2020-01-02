import React, { Component } from 'react';
import {Col, Form, Input, message, Modal, Row, Transfer} from 'antd';
import { connect } from 'dva';

@connect(({ ticket }) => ({
  ticket,
  userList: ticket.userList,
  userTicketList: ticket.userTicketList,
  handleResult: ticket.handleResult,
}))
class TicketUser extends React.Component{

  constructor(props) {
    super(props);
  }

  handleUserSelectChange = (targetKeys, direction, moveKeys) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'ticket/changeUserTicketList',
      payload: targetKeys,
    });
  };

  handleSubmit = () => {
    const {saleAuthTicketId, userTicketList, dispatch} = this.props;
    dispatch({
      type: 'ticket/authTicketUser',
      payload: {
        ticketId: saleAuthTicketId,
        userIds: userTicketList
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
    const {saleAuthVisible, userList, userTicketList, changeSaleAuthVisible} = this.props;
    console.log('userTicketList', userTicketList);
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
              dataSource={userList}
              targetKeys={userTicketList}
              showSearch
              onChange={this.handleUserSelectChange}
              render={item => item.userName}
              rowKey={item => item.id}
              titles={['未授权用户', '已授权用户']}
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

export default Form.create()(TicketUser);
