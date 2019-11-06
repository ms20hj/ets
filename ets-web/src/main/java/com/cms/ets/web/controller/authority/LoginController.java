package com.cms.ets.web.controller.authority;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cms.ets.api.authority.IUserService;
import com.cms.ets.common.constant.RsaConstant;
import com.cms.ets.common.enums.CodeEnum;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.core.util.RSAUtil;
import com.cms.ets.model.mysql.authority.User;
import com.cms.ets.web.controller.BaseController;
import com.google.common.collect.ImmutableMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户登录
 * @author Cmingsen
 */
@RestController
@RequestMapping("login")
public class LoginController extends BaseController {

    private static final Log LOG = LogFactory.get(LoginController.class);

    @Reference
    private IUserService userService;

    /**
     * 获取RSA加密公钥
     * @return HandleResult
     * @date 2019年11月5日17:28:45
     */
    @GetMapping("getRsaPublicKey")
    public HandleResult getRsaPublicKey() {
        return success(RsaConstant.PUBLIC_KEY);
    }

    /**
     * 用户登录
     * @param userName 用户名
     * @param password 密码，此密码是经过前端RSA加密
     * @return HandleResult
     */
    @GetMapping("userLogin")
    public HandleResult userLogin(String userName, String password){
        try {
            User user = userService.getByUserName(userName);
            if (user == null) {
                return error("用户不存在");
            }
            // 对RSA密码进行解密，解密完的密码为明文，如123456
            String rsaDecryptPwd = RSAUtil.decrypt(password);
            MD5 md5 = new MD5();
            String md5Pwd = md5.digestHex(rsaDecryptPwd);
            if (!user.getPassword().equals(md5Pwd)) {
                return error("密码错误");
            }
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getPassword());
            Subject subject = SecurityUtils.getSubject();
            subject.login(usernamePasswordToken);

            if (subject.isAuthenticated()) {
                Map<String, String> map = ImmutableMap.of("userId", user.getId(), "token", subject.getSession().getId().toString());
                return success(map);
            }
        } catch (AccountException ue) {
            LOG.error(ue);
            return error(ue.getMessage());
        } catch (AuthenticationException e) {
            LOG.error(e);
            return error(CodeEnum.SYSTEM_ERROR);
        }
        return error(CodeEnum.SYSTEM_ERROR);
    }
}
