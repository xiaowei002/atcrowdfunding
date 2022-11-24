package com.xiaowei.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.xiaowei.entity.Admin;
import com.xiaowei.service.AdminService;
import com.xiaowei.util.Constant.ConstantCrowd;
import com.xiaowei.util.result.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 获取列表数据
     * @param keyword
     * @param pageNumber
     * @param pageSize
     * @param modelMap
     * @return
     */
    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(@RequestParam(value = "keyword",defaultValue = "") String keyword,
                              @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                              @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
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

    /**
     * 删除数据
     * @param id
     * @return
     */
    @RequestMapping("admin/remove/{id}")
    public String remove(@PathVariable("id") Integer id){
        adminService.remove(id);
        return "redirect:/admin/get/page.html";
    }

    /**
     * 新增
     * @param admin
     * @return
     */
    @RequestMapping("admin/add.html")
    public String save(Admin admin){
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNumber="+Integer.MAX_VALUE;
    }

    /**
     * 根据ID获取
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping("admin/update")
    public String update(@RequestParam("id") Integer id,
                         ModelMap modelMap){
        Admin admin = adminService.getAdminById(id);
        modelMap.addAttribute(ConstantCrowd.ATTR_NAME_LOGIN_ADMIN,admin);
        return "admin-edit";
    }

    @RequestMapping("admin/update.html")
    public String update(Admin admin,
                         @RequestParam("pageNumber") Integer pageNumber,
                         @RequestParam("keyword") String keyword){
        adminService.update(admin);
        return "redirect:/admin/get/page.html?pageNumber="+pageNumber+"&keyword="+keyword;
    }

}
