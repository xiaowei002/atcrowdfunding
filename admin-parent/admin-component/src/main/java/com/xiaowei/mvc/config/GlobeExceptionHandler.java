package com.xiaowei.mvc.config;

import com.google.gson.Gson;
import com.xiaowei.util.Constant.ConstantCrowd;
import com.xiaowei.util.Exception.AccessForbiddenException;
import com.xiaowei.util.Exception.LoginFailedException;
import com.xiaowei.util.requestType.JudgeRequestType;
import com.xiaowei.util.result.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobeExceptionHandler {

    @ExceptionHandler(LoginFailedException.class)
    public ModelAndView exceptionHandlerMethod(LoginFailedException exception,HttpServletResponse response,HttpServletRequest request) throws IOException {
        //1.判断请求类型,true:ajax 请求
        String viewName = "admin-login";
        return getModelAndView(exception, request, response,viewName);
    }
    @ExceptionHandler(AccessForbiddenException.class)
    public ModelAndView exceptionHandlerMethod(AccessForbiddenException exception,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String viewName = "admin-login";
        return getModelAndView(exception,request,response,viewName);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandlerMethod(Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.判断请求类型,true:ajax 请求
        String viewName = "system-error";
        return getModelAndView(exception, request, response,viewName);
    }

    private ModelAndView getModelAndView(Exception exception, HttpServletRequest request, HttpServletResponse response,String viewName) throws IOException {
        boolean ajaxRequest = JudgeRequestType.isAjaxRequest(request);

        if(ajaxRequest){
            //创建Result
            ResultEntity<Object> failed = ResultEntity.failed(exception.getMessage());

            Gson gson = new Gson();

            String s = gson.toJson(failed);

            response.getWriter().write(s);
            return null;
        }else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(ConstantCrowd.ATTR_NAME_EXCEPTION,exception);
            modelAndView.setViewName(viewName);
            return modelAndView;
        }
    }

}
