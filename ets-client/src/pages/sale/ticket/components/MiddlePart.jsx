import React, {Component} from 'react';
import {Form, Input, InputNumber, Select, Table} from 'antd';
import styles from './MiddlePart.less';
import {connect} from "dva";

const EditableContext = React.createContext();

@connect( ({order, sale}) => ({
  saleOrderTypeList: order.saleOrderTypeList,
  touristList: sale.touristList,
  ticketList: sale.ticketList,
  discountList: sale.discountList,
}))
class EditableCell extends Component {

  constructor(props) {
    super(props);
  }

  getInput = () => {
    console.log('this.props.dataIndex', this.props.dataIndex);
    if (this.props.editable) {
      if (this.props.dataIndex === 'personCount') {
        return <InputNumber min={1} max={500} onChange={this.changePerson} />;
      } else if (this.props.dataIndex === 'touristName' || this.props.dataIndex === 'ticketName' || this.props.dataIndex === 'discountName') {
        return this.renderSelect();
      }
    } else {
      return <Input readOnly={true}/>;
    }
  };

  renderSelect = () => {
    const {touristList, ticketList, discountList} = this.props;

    if (this.props.dataIndex === 'touristName')  {
      return (<Select className={styles.tdSelect} onSelect={this.selectTourist} placeholder='请选择游客类型'>
        {touristList.map( (item) => (
          <Select.Option key={item.id} value={item.id}>{item.touristName}</Select.Option>
        ))}
      </Select>);
    } else if (this.props.dataIndex === 'ticketName') {
      return (<Select className={styles.tdSelect} onSelect={this.selectTicket} placeholder='请选择票类'>
        {ticketList.map((item) => (
          <Select.Option key={item.id} value={item.id}>{item.ticketName}</Select.Option>
        ))}
      </Select> );
    } else if (this.props.dataIndex === 'discountName'){
      return (<Select className={styles.tdSelect} onSelect={this.selectDiscount} placeholder='请选择优惠方式'>
        {discountList.map((item) => (
          <Select.Option key={item.id} value={item.id}>{item.discName}</Select.Option>
        ))}
      </Select> );
    }
    return <Input readOnly={true}/>;
  };
  /**
   * 选中游客类型
   * @param val
   * @param option
   */
  selectTourist = (val, option) => {
    const {saleOrderTypeList, editRow} = this.props;
    const data = saleOrderTypeList.map(item => {
      if (item.key === editRow) {
        item.touristId = val;
        item.touristName = option.props.children;
      }
      return item;
    });

    this.refreshOrder(data);
  };
  /**
   * 选中门票处理
   * @param val
   * @param option
   */
  selectTicket = (val) => {
    const {saleOrderTypeList, ticketList, editRow} = this.props;
    const ticket = ticketList.find((item) => {
      if (item.id === val)  {
        return item;
      }
    });
    const data = saleOrderTypeList.map(item => {
      if (item.key === editRow) {
        item.ticketId = ticket.id;
        item.ticketName = ticket.ticketName;
        item.price = ticket.salePrice;
        item.amount = item.price * item.personCount;
        item.ticket = ticket;
      }
      return item;
    });

    this.refreshOrder(data);
  };

  selectDiscount = val => {
    const {saleOrderTypeList, discountList, editRow} = this.props;
    const discount = discountList.find((item) => {
      if (item.id === val)  {
        return item;
      }
    });
    const data = saleOrderTypeList.map(item => {
      if (item.key === editRow) {
        item.discountId = discount.id;
        item.discountName = discount.discName;
        // 0：打折（如：8折、九折）
        if (discount.discountWay === 0) {
          item.price = item.price * discount.discountScale;
        }
        //1：减免（如：减10/人，减15/人）
        if (discount.discountWay === 1) {
          item.price = item.price - discount.discountScale;
        }
        //2：按照优惠金额销售（如：直接￥50）
        if (discount.discountWay === 2) {
          item.price = discount.discountScale;
        }
        item.amount = item.price * item.personCount;
        item.discount = discount;
      }
      return item;
    });
    this.refreshOrder(data);
  };

  changePerson = count => {
    const {saleOrderTypeList, editRow} = this.props;
    const data = saleOrderTypeList.map(item => {
      if (item.key === editRow) {
        item.personCount = count;
        // 判断一票一人
        if (item.ticket !== null && item.ticket.printMethod === 0) {
          item.ticketCount = count;
        } else {
          item.ticketCount = 1;
        }
      }
    });
    this.refreshOrder(data);
  };

  refreshOrder = data => {
    const {dispatch} = this.props;
    dispatch({
      type: 'order/refreshOrderTypeList',
      payload: data,
    });
  };

  setEditCell = (dataIndex, key) => {
    this.setState({
      editCell: dataIndex,
      editRow: key,
    });
  };

  toggleEdit = () => {
    console.log('@@@@@@@@@@@@@@@',this);
    this.setEditCell(this.props.dataIndex, this.props.record.key);
  };

  renderCell = ({ getFieldDecorator }) => {
    const {
      editing,
      dataIndex,
      title,
      cellType,
      record,
      index,
      children,
      editable,
      editRow,
      ...restProps
    } = this.props;
    return (
      <td {...restProps}>
        { editing ? (
          <Form.Item style={{ margin: 0 }}>
            {getFieldDecorator(dataIndex, {
              rules: [
                {
                  required: true,
                  message: `请选择 ${title}!`,
                },
              ],
              initialValue: record[dataIndex],
            })(this.getInput())}
          </Form.Item>
        ) : (
          <div className={styles.tdDiv}>
            {children}
          </div>
        )}
      </td>
    );
  };

  render() {
    return <EditableContext.Consumer>{this.renderCell}</EditableContext.Consumer>;
  }
}
@connect( ({order}) => ({
  saleOrderTypeList: order.saleOrderTypeList,
}))
@Form.create()
export default class MiddlePart extends Component{

  constructor(props) {
    super(props);
    this.state = { editingKey: '0' };
    this.columns = [
      {
        title: '游客类型',
        dataIndex: 'touristName',
        width: '20%',
        editable: true,
        align: 'center',
      },
      {
        title: '票类',
        dataIndex: 'ticketName',
        width: '20%',
        editable: true,
        align: 'center',
      },
      {
        title: '单价',
        dataIndex: 'price',
        width: '10%',
        editable: false,
      },
      {
        title: '人数',
        dataIndex: 'personCount',
        width: '10%',
        editable: true,
        align: 'center',
      },
      {
        title: '票数',
        dataIndex: 'ticketCount',
        width: '10%',
        editable: false,
      },
      {
        title: '折扣方式',
        dataIndex: 'discountName',
        width: '20%',
        editable: true,
        align: 'center',
      },
      {
        title: '小计',
        dataIndex: 'amount',
        width: '10%',
        editable: false,
        align: 'center',
      },
    ];
  }

  componentWillMount() {
    const {dispatch} = this.props;
    dispatch({
      type: 'order/create',
    });
  }

  edit = (key) =>{
    console.log('key', key);
    this.setState({editingKey: key})
  };
  isEditing = record => record.key === this.state.editingKey;

  render() {

    const {saleOrderTypeList} = this.props;
    const components = {
      body: {
        cell: EditableCell,
      },
    };

    const columns = this.columns.map(col => {
      return {
        ...col,
        onCell: record => ({
          record,
          dataIndex: col.dataIndex,
          title: col.title,
          editable: col.editable,
          editing: this.state.editingKey === record.key,
          editRow: this.state.editingKey,
          onMouseEnter: () => {this.edit(record.key)}
        }),
      };
    });

    return (
      <EditableContext.Provider value={this.props.form}>
        <Table
          components={components}
          bordered
          dataSource={saleOrderTypeList}
          columns={columns}
          rowClassName={styles.editableRow}
          pagination={false}
          scroll={{ y: 240 }}
        />
      </EditableContext.Provider>
    );
  }
}
