package com.xiaowei.mvc.controller;

import com.xiaowei.entity.Admin;
import com.xiaowei.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestSsm {

    @Autowired
    private AdminService adminService;

    /**
     * 测试ssm整合
     * @param modelMap
     * @return
     */
    @RequestMapping("test/ssm")
    public String testSsm(Model modelMap){
      List<Admin> admins = adminService.getAll();
      modelMap.addAttribute("admins",admins);
        System.out.println(10/0);
      return "target";
    }

    /**
     * 测试ajax获取参数
     * @param list
     * @return
     */
    @ResponseBody
    @PostMapping("send/array/one")
    public String receiveArrayOne(@RequestParam("array[]") List<Integer> list){
        for (Integer i:list
             ) {
            System.out.println(i);
        }
        return "target";
    }

    /**
     * 测试ajax获取参数
     * @param list
     * @return
     */
    @ResponseBody
    @PostMapping("send/array/two")
    public String receiveArrayTwo(@RequestParam("array") List<Integer> list){
        for (Integer i:list
        ) {
            System.out.println(i);
        }
        return "target";
    }


    /**
     * 测试ajax获取参数
     * @param list
     * @return
     */
    @ResponseBody
    @PostMapping("send/array/three")
    public String receiveArrayThree(@RequestBody List<Integer> list){
        Logger logger = LoggerFactory.getLogger(TestSsm.class);
        for (Integer i:list
        ) {
            logger.info("i="+i);
        }
        System.out.println(10/0);
        return "target";
    }
}
