package com.cms.ets.web.shiro;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.cms.ets.common.enums.CodeEnum;
import com.cms.ets.common.response.HandleResult;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 当页面无权限访问时，我们在过滤器里直接返回错误信息，
 * 不利用shiro自带的跳转。看过滤器中的onAccessDenied函数
 * @date 2019年11月19日09:54:17
 * @author Cms
 */
public class CORSAuthenticationFilter extends FormAuthenticationFilter {

    private static final Log logger = LogFactory.get(CORSAuthenticationFilter.class);
    public CORSAuthenticationFilter() {
        super();
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //Always return true if the request's method is OPTIONS
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse)response;
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        HandleResult handleResult = HandleResult.error(CodeEnum.AUTHORIZE_UNAUTH);
        writer.write(JSONUtil.toJsonStr(handleResult));
        writer.close();
        return false;
    }
}
