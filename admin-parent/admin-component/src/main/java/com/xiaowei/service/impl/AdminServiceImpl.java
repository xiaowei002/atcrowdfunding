package com.xiaowei.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaowei.entity.Admin;
import com.xiaowei.entity.AdminExample;
import com.xiaowei.mapper.AdminMapper;
import com.xiaowei.service.AdminService;
import com.xiaowei.util.Constant.ConstantCrowd;
import com.xiaowei.util.EncryptionAlgorithm.EncryptionAlgorithm;
import com.xiaowei.util.Exception.LoginFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

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

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    /**
     * 登录方法
     * @param loginAcct
     * @param userPswd
     * @return
     */
    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        //根据登录账号查询admin
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);

        List<Admin> admins = adminMapper.selectByExample(adminExample);

        if(admins == null || admins.size() == 0){
            throw new LoginFailedException(ConstantCrowd.MESSAGE_LOGIN_FAILED);
        }

        if(admins.size() > 1){
            throw new RuntimeException(ConstantCrowd.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        Admin admin = admins.get(0);

        //判断admin是否存在并密码是否一致
        if(admin == null){
            throw new LoginFailedException(ConstantCrowd.MESSAGE_LOGIN_FAILED);
        }

        String passWord = EncryptionAlgorithm.md5Algorithm(userPswd);

        if(!Objects.equals(passWord,admin.getUserPswd())){
            throw new LoginFailedException(ConstantCrowd.MESSAGE_LOGIN_FAILED);
        }

        return admin;
    }

    /**
     *  获取列表分页
     * @param keyword
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNumber, Integer pageSize) {
        //调用pagehelper的静态方法开启分页
        PageHelper.startPage(pageNumber,pageSize);

        List<Admin> admins = adminMapper.selectAdminByKeyword(keyword);

        if(CollectionUtils.isEmpty(admins)){
            return null;
        }

        return new PageInfo<>(admins);
    }
}
