package com.cms.ets;

import com.cms.ets.core.util.RSAUtil;
import org.junit.Test;

public class TestRsa {

    @Test
    public void encrypt(){
        String data = RSAUtil.encryptByPublicKey("111111");
        System.out.println(data);
    }

    @Test
    public void decrypt() {
        String rsaPwd = "M++cZaXG4rg4bkYTs6p+zmMA29CHy6HiBRLVmsteo569b4zQCCQjXGAfH2qbnbcqHBmgvl4KW+bqKQ48+Jx7DzsD/sDczTFZQhygYbytvPaR5nxWsCj5cZ6FUKX7cKw9Dqtoh66ys2eqOvrvHKEZZQuupp8X2Yi3HG4fNNb9yrg=";
        String data = RSAUtil.decrypt(rsaPwd);
        System.out.println(data);
    }
}
