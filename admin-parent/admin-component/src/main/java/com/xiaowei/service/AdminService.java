package com.xiaowei.service;

import com.github.pagehelper.PageInfo;
import com.xiaowei.entity.Admin;

import java.util.List;

public interface AdminService {
    /**
     * 保存操作
     * @param admin
     */
    void saveAdmin(Admin admin);

    List<Admin> getAll();

    /**
     * 登录方法
     * @param loginAcct
     * @param userPswd
     * @return
     */
    Admin getAdminByLoginAcct(String loginAcct, String userPswd);


    PageInfo<Admin> getPageInfo(String keyword,Integer pageNumber,Integer pageSize);

    void remove(Integer id);

    Admin getAdminById(Integer id);

    void update(Admin admin);
}
