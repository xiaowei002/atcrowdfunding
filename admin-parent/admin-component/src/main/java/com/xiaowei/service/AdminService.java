package com.xiaowei.service;

import com.xiaowei.entity.Admin;

import java.util.List;

public interface AdminService {
    /**
     * 保存操作
     * @param admin
     */
    void saveAdmin(Admin admin);

    List<Admin> getAll();
}
