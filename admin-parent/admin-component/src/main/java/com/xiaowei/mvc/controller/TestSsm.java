package com.xiaowei.mvc.controller;

import com.xiaowei.entity.Admin;
import com.xiaowei.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestSsm {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/test/ssm")
    public String testSsm(Model modelMap){
      List<Admin> admins = adminService.getAll();
      modelMap.addAttribute("admins",admins);
      return "target";
    }
}
