package com.cms.ets.web.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @description: 自定义密码校验器
 **/
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        // 获得用户输入的密码
        String inPassword = new String(utoken.getPassword());
        // 获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        // 进行密码的比对
        try {
            return this.equals(inPassword, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
