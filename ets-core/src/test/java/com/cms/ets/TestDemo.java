package com.cms.ets;

import cn.hutool.crypto.digest.MD5;
import org.junit.Test;

public class TestDemo {

    @Test
    public void testMD5(){
        MD5 md5 = new MD5();
        String pwd = md5.digestHex("111111");
        System.out.println(pwd);
    }
}
