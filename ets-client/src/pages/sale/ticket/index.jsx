import React, {Component} from 'react';
import {Avatar, Col, Layout, Row} from 'antd';
import styles from './style.less';
import {connect} from "dva";
import logo from '../../../assets/logo_small.png';
import MainContainer from './components/MainContainer'

const { Header, Footer, Content } = Layout;

@connect( ({user}) => ({
  currentUser: user.currentUser
}))
export default class SaleTicket extends Component{


  render() {
    const {currentUser} = this.props;
    return(
      <div className={styles.main}>
        <Layout>
          <Header>
            <Row>
              <Col span={20}>
                <img src={logo} />
              </Col>
              <Col span={2} offset={2}>
                <Avatar icon='user' size='large' alt='U'/>
              </Col>
            </Row>
          </Header>
          <Content>
            <MainContainer />
          </Content>
          <Footer>Footer</Footer>
        </Layout>
      </div>
    );
  }
}
