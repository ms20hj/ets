import React, {Component} from 'react';
import {Cascader, Col, Divider, Row} from "antd";
import {getCityJson} from '@/assets/data/city'
import styles from './TopPart.less';
import {connect} from "dva";

const city = getCityJson();

@connect(({sale}) => ({
  sale,
  travelAgencyTree: sale.travelAgencyTree,
}))
export default class TopPart extends Component{

  constructor(props) {
    super(props);
    this.state = {
      travelAgency: {},
      citySource: {},
    }
  }

  componentDidMount() {
    const {dispatch} = this.props;
    dispatch({
      type: 'sale/getTravelAgencyTree'
    })
  }

  onChangeCity = (value, selectedOptions) => {
    if (selectedOptions.length === 2) {
      this.setState({
        citySource: selectedOptions[1]
      })
    } else {
      this.setState({
        citySource: selectedOptions[0]
      })
    }
  };

  onChangeTR = (value, selectedOptions) => {
    if (selectedOptions.length === 2) {
      this.setState({
        travelAgency: selectedOptions[1]
      })
    } else {
      this.setState({
        travelAgency: selectedOptions[0]
      })
    }
  };


  filterCascader = (inputValue, path) => {
    return path.some(option => option.label.toLowerCase().indexOf(inputValue.toLowerCase()) > -1);
  };
  render() {
    const {travelAgencyTree} = this.props;
    return(
      <div className={styles.main}>
        <Row>
          <Col span={4} offset={2}>
            <div>
              <label>客源地:</label>
              <Cascader
                options={city}
                defaultValue={['350000000000', '350100000000']}
                fieldNames={{label:'name', value: 'id', children: 'children'}}
                showSearch={this.filterCascader}
                onChange={this.onChangeCity}
              />
            </div>
          </Col>
          <Col span={4} offset={2}>
            <div>
              <label>旅行社:</label>
              <Cascader
                options={travelAgencyTree}
                fieldNames={{label:'name', value: 'id', children: 'children'}}
                showSearch={this.filterCascader}
                onChange={this.onChangeTR}
              />
            </div>
          </Col>
        </Row>
        <Divider />
      </div>
    )
  }
}
