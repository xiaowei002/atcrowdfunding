package com.xiaowei.service.impl;

import com.xiaowei.entity.Admin;
import com.xiaowei.mapper.AdminMapper;
import com.xiaowei.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


    /**
     * 保存操作
     * @param admin
     */
    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
    }
}
