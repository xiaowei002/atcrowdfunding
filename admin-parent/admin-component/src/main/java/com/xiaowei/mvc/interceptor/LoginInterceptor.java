package com.xiaowei.mvc.interceptor;

import com.xiaowei.entity.Admin;
import com.xiaowei.util.Constant.ConstantCrowd;
import com.xiaowei.util.Exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
       //1.通过session获取admin对象
        HttpSession session = httpServletRequest.getSession();
        Admin admin = (Admin) session.getAttribute(ConstantCrowd.ATTR_NAME_LOGIN_ADMIN);

        //判断admin是否为空
        if(admin == null){
            throw new AccessForbiddenException(ConstantCrowd.MESSAGE_ACCESS_FORBIDEN);
        }
        return true;
    }
}
