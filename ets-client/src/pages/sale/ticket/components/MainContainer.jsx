import React, {Component} from 'react';
import { Col, Row} from 'antd';
import TopPart from './TopPart'
import MiddleSaleTable from './MiddleSaleTable'
import styles from  './MainContainer.less';


export default class MainContainer extends Component{

  render() {
    return(
      <div className={styles.main}>
        <TopPart />
        <MiddleSaleTable />
      </div>
    );
  }
}
