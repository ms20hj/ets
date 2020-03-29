import React, {Component} from 'react';
import { Col, Row} from 'antd';
import TopPart from './TopPart'
import MiddlePart from './MiddlePart'
import styles from  './MainContainer.less';

export default class MainContainer extends Component{

  render() {
    return(
      <div className={styles.main}>
        <TopPart />
        <MiddlePart />
      </div>
    );
  }
}
