import React, { Component } from 'react';
import {Col, Form, Modal, Row, Table, InputNumber, Divider} from "antd";
import {connect} from 'dva';

@connect(({ ticket }) => ({
  ticket,
  ticketScapeList: ticket.ticketScapeList,
}))
class TicketScapeTable extends Component{

  constructor(props) {
    super(props);
  }

  handleSubmit = () => {

  };

  render() {
    const {editTsVisible, ticketScapeList, changeEditTsVisible} = this.props;
    const columns = [
      {
        title: '序号',
        dataIndex: 'id',
        width: '10%',
        key: 'id',
        render: (text, record, index) => <span>{index + 1}</span>,
      },
      {
        title: '景点名称',
        dataIndex: 'scapeName',
        key: 'scapeName',
      },
      {
        title: '总可进次数',
        dataIndex: 'allIn',
        key: 'allIn',
        render: allIn => <InputNumber defaultValue={allIn} min={1} max={999}/>,
      },
      {
        title: '日可进次数',
        dataIndex: 'dayIn',
        key: 'dayIn',
        render: dayIn => <InputNumber defaultValue={dayIn} min={1} max={999}/>,
      },
    ];
    return(
      <Modal
        destroyOnClose
        title='配置'
        visible={editTsVisible}
        onOk={this.handleSubmit}
        onCancel={() => changeEditTsVisible(false)}
        width={680}
      >
        <Row>
          <Col span={24}>
            <Table
              columns={columns}
              dataSource={ticketScapeList}
              pagination={false}
              rowKey={item => item.id}
              scroll={{y: true}}
            />
          </Col>
        </Row>

      </Modal>
    );
  }
}
export default Form.create()(TicketScapeTable);
