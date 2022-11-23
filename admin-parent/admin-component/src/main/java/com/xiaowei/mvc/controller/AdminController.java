package com.xiaowei.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.xiaowei.entity.Admin;
import com.xiaowei.service.AdminService;
import com.xiaowei.util.Constant.ConstantCrowd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;


    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                              @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                              @RequestParam(value = "pageSize",defaultValue = "1") Integer pageSize,
                              ModelMap modelMap){
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNumber, pageSize);
        modelMap.addAttribute(ConstantCrowd.ATTR_NAME_PAGE_INFO,pageInfo);
        return "admin-page";
    }
    /**
     * 登录方法
     * @param loginAcct
     * @param userPswd
     * @param session
     * @return
     */
    @RequestMapping("/admin/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct, @RequestParam("userPswd") String userPswd, HttpSession session){
        Admin admin = adminService.getAdminByLoginAcct(loginAcct,userPswd);
        session.setAttribute(ConstantCrowd.ATTR_NAME_LOGIN_ADMIN,admin);
        return "redirect:/admin/do/main.html";
    }


    /**
     * 退出登录，session失效
     * @param session
     * @return
     */
    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session){
        //强制session失效
        session.invalidate();
        return "redirect:/to/login/page";
    }
}
