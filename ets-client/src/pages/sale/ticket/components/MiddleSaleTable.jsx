import React, {Component} from 'react';
import {Button, Col, Form, Input, InputNumber, Popconfirm, Row, Select, Table} from 'antd';
import {connect} from "dva";
import SaleForm from './SaleForm';
import styles from './MiddleSaleTable.less';

@connect( ({order, sale}) => ({
  saleOrderTypeList: order.saleOrderTypeList,
  touristList: sale.touristList,
  ticketList: sale.ticketList,
  discountList: sale.discountList,
}))
export default class MiddleSaleTable extends Component {

  constructor(props) {
    super(props);
    this.state = {
      saleVisible: false,
    }
  }

  componentWillMount() {
    const {dispatch} = this.props;
    dispatch({
      type: 'order/getSaleOrderType'
    })
  }

  changeSaleVisible = flag => {
    this.setState({saleVisible: flag});
  };
  /**
   * 添加销售
   * 打开销售表单页面
   */
  addRow = () => {
    const {dispatch} = this.props;
    dispatch({
      type: 'order/getSaleOrderType'
    }).then( ()=> this.changeSaleVisible(true));
  };

  render() {
    const {saleOrderTypeList} = this.props;
    const {saleVisible} = this.state;
    const columns = [
      {
        title: '游客类型',
        dataIndex: 'touristName',
        width: '20%',
        align: 'center',
      },
      {
        title: '票类',
        dataIndex: 'ticketName',
        width: '20%',
        align: 'center',
      },
      {
        title: '单价',
        dataIndex: 'price',
        width: '10%',
      },
      {
        title: '人数',
        dataIndex: 'personCount',
        width: '10%',
        align: 'center',
      },
      {
        title: '票数',
        dataIndex: 'ticketCount',
        width: '10%',
      },
      {
        title: '折扣方式',
        dataIndex: 'discountName',
        width: '20%',
        align: 'center',
      },
      {
        title: '小计',
        dataIndex: 'amount',
        width: '10%',
        align: 'center',
      },
    ];
    const saleMethod = {
      changeSaleVisible: this.changeSaleVisible,
    };
    return(
      <div>
        <Row>
          <Col span={24}>
            <Table
              bordered
              dataSource={saleOrderTypeList}
              columns={columns}
              pagination={false}
              scroll={{ y: 240 }}
            />
          </Col>
        </Row>
        <Row >
          <Col offset={2} span={8}>
            <Button type='primary' icon='plus' size='large' block style={{marginTop: 10}} onClick={this.addRow}>
              添加
            </Button>
          </Col>
          <Col offset={4} span={8}>
            <Button type='primary' icon='plus' size='large' block style={{marginTop: 10}} onClick={this.addRow}>
              销售
            </Button>
          </Col>
        </Row>
        <SaleForm saleVisible={saleVisible} {...saleMethod}/>
      </div>
    );
  }
}
