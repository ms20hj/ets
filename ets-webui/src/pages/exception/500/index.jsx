import Link from 'umi/link';
import { Result, Button } from 'antd';
import React from 'react';

export default () => (
  <Result
    status="500"
    title="500"
    style={{
      background: 'none',
    }}
    subTitle="服务请求异常了"
    extra={
      <Link to="/">
        <Button type="primary">返回</Button>
      </Link>
    }
  />
);
