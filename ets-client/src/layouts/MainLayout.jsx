import React, {Component} from 'react';
import {Card, Col, Collapse, Empty, Layout, Row} from "antd";
import {connect} from "dva";
import { sliceArray } from '@/utils/list';
import { router } from 'umi';

const { Header, Footer, Content } = Layout;
@connect( ({user}) => ({
  user,
  menuList: user.menuList,
}))
export default class MainLayout extends Component{
  constructor(props) {
    super(props);
  }
  componentDidMount() {
    const {dispatch} = this.props;
    dispatch({
      type: 'user/fetchMenu'
    });
  }

  menuClick = (url) => {
    router.push(url)
  };

  renderChildMenu = (children, parentMenu) => {
    const data = sliceArray(children, 4);
    return (
      <div>
        {
          data.map( list => (
            <Row gutter={6}>
              {
                list.map( item => (
                  <Col span={6}>
                    <Card title={item.menuName} key={item.id} onClick={this.menuClick.bind(this, `/${parentMenu.url}/${item.url}`)}>
                      <p>{item.menuName}</p>
                    </Card>
                  </Col>
                ))
              }
            </Row>
          ))
        }
      </div>
    );
  };

  render() {
    const {menuList} = this.props;
    return (
      <div>
        <Layout>
          <Header style={{background: '#5093d4'}}>Header</Header>
          <Content>
            {
              menuList && menuList.length === 0 ?
              <Empty description='暂无权限'/>
              :
              <Collapse defaultActiveKey={[`${menuList[0].id}`]}>
                {menuList.map((item) => (
                  <Collapse.Panel key={item.id} header={item.menuName}>
                    {this.renderChildMenu(item.children, item)}
                  </Collapse.Panel>
                ))}
              </Collapse>
            }
          </Content>
          <Footer>Footer</Footer>
        </Layout>
      </div>
    );
  }
}
