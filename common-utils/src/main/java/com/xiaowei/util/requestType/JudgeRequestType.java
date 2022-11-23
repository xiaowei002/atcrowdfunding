package com.xiaowei.util.requestType;

import javax.servlet.http.HttpServletRequest;

public class JudgeRequestType {

    /**
     * 判断请求是否为ajax请求，true：是
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request){
        String accept = request.getHeader("Accept");
        String header = request.getHeader("X-Requested-With");

        return ((accept != null && accept.contains("Application/json")) || (header != null && header.equals("XMLHttpRequest")));
    }
}
