import React, {Component} from 'react';
import {Form, Input, InputNumber, Popconfirm, Select, Table} from 'antd'

const data = [];
for (let i = 0; i < 5; i++) {
  data.push({
    key: i.toString(),
    name: `Edrward ${i}`,
    cellType: 'select',
    age: 32,
    address: `London Park no. ${i}`,
  });
}
const EditableContext = React.createContext();

class EditableCell extends Component {

  constructor(props) {
    super(props);

    this.state = {
      editCell: '',
      editRow: '',
    }
  }

  getInput = () => {
    console.log('this.props.cellType', this.props.cellType);
    if (this.props.cellType === 'number') {
      return <InputNumber />;
    } else if (this.props.cellType === 'select') {
      return <Select />
    }
    return <Input />;
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
      ...restProps
    } = this.props;
    const {editCell, editRow} = this.state;
    return (
      <td {...restProps}>
        {editCell === dataIndex && record.key === editRow ? (
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
          <div
            onClick={this.toggleEdit}
          >
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

@Form.create()
export default class MiddlePart extends Component{

  constructor(props) {
    super(props);
    this.state = { data, editingKey: '' };
    this.columns = [
      {
        title: 'name',
        dataIndex: 'name',
        width: '25%',
        editable: true,
      },
      {
        title: 'age',
        dataIndex: 'age',
        width: '15%',
        editable: true,
      },
      {
        title: 'address',
        dataIndex: 'address',
        width: '40%',
        editable: true,
      },
    ];
  }


  render() {
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
          cellType: record.cellType,
          dataIndex: col.dataIndex,
          title: col.title,
        }),
      };
    });

    return (
      <EditableContext.Provider value={this.props.form}>
        <Table
          components={components}
          bordered
          dataSource={this.state.data}
          columns={columns}
          rowClassName="editable-row"
          pagination={false}
        />
      </EditableContext.Provider>
    );
  }
}
