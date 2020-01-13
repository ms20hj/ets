package com.cms.ets.web.shiro;

import com.cms.ets.core.util.SpringUtils;
import com.cms.ets.model.mysql.authority.Role;
import com.cms.ets.model.mysql.authority.User;
import com.cms.ets.web.shiro.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;
import java.util.stream.Collectors;

public class ShiroRealm extends AuthorizingRealm {

    private ShiroService shiroService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }
        List<Role> roleList = getShiroService().getByUserId(user.getId());
        if (roleList.isEmpty()) {
            throw new AccountException("用户未授权");
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<String> roleIdList = roleList.stream().map(Role::getId).collect(Collectors.toList());
        authorizationInfo.addRoles(roleIdList);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String userName = usernamePasswordToken.getUsername();
        if (StringUtils.isEmpty(userName)) {
            throw new UnknownAccountException("用户不存在");
        }
        User user = getShiroService().getByUserName(userName);
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }
        //这里验证authenticationToken和simpleAuthenticationInfo的信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
                new ShiroByteSource(user.getPassword()), getName());
        return simpleAuthenticationInfo;
    }

    public ShiroService getShiroService() {
        if (shiroService == null) {
            shiroService = SpringUtils.getBean(ShiroService.class);
        }
        return shiroService;
    }
}
