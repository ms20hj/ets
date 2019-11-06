package com.cms.ets.web.shiro;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.cms.ets.common.constant.ShiroConstant;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @description: 自定义session容器
 **/
public class SessionManager extends DefaultWebSessionManager {

    public SessionManager() {
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //获取请求头，或者请求参数中的Token
        String id = StringUtils.isEmpty(WebUtils.toHttp(request).getHeader(ShiroConstant.AUTHORIZATION))
                ? request.getParameter(ShiroConstant.AUTHORIZATION) : WebUtils.toHttp(request).getHeader(ShiroConstant.AUTHORIZATION);
        // 如果请求头中有 Token 则其值为sessionId
        if (StringUtils.isNotEmpty(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroConstant.REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        } else {
            // 否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }

    /**
     * 获取session 优化单次请求需要多次访问redis的问题
     *
     * @param sessionKey
     * @return
     * @throws UnknownSessionException
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);

        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }

        if (request != null && null != sessionId) {
            Object sessionObj = request.getAttribute(sessionId.toString());
            if (sessionObj != null) {
                return (Session) sessionObj;
            }
        }

        Session session = super.retrieveSession(sessionKey);
        if (request != null && null != sessionId) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}
