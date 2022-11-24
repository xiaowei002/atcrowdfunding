package com.xiaowei.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaowei.entity.Admin;
import com.xiaowei.entity.AdminExample;
import com.xiaowei.mapper.AdminMapper;
import com.xiaowei.service.AdminService;
import com.xiaowei.util.Constant.ConstantCrowd;
import com.xiaowei.util.EncryptionAlgorithm.EncryptionAlgorithm;
import com.xiaowei.util.Exception.LoginAcctAlreadyInUseException;
import com.xiaowei.util.Exception.LoginAcctAlreadyInUseForUpdateException;
import com.xiaowei.util.Exception.LoginFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);


    /**
     * 保存操作
     * @param admin
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveAdmin(Admin admin) {
        String userPswd = admin.getUserPswd();
        userPswd = EncryptionAlgorithm.md5Algorithm(userPswd);

        admin.setUserPswd(userPswd);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String creatTime = simpleDateFormat.format(date);

        admin.setCreateTime(creatTime);
        try {
            adminMapper.insert(admin);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("异常全类名"+e.getClass().getName());

            if(e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseException(ConstantCrowd.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
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
        //调用pageHelper的静态方法开启分页
        PageHelper.startPage(pageNumber,pageSize);

        List<Admin> admins = adminMapper.selectAdminByKeyword(keyword);

        if(CollectionUtils.isEmpty(admins)){
            return null;
        }

        return new PageInfo<>(admins);
    }

    /***
     * 删除方法
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void remove(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    /***
     * 根据id获取数据
     * @param id
     * @return
     */
    @Override
    public Admin getAdminById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Admin admin) {
        try{
            adminMapper.updateByPrimaryKeySelective(admin);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("异常全类名"+e.getClass().getName());

            if(e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseForUpdateException(ConstantCrowd.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
    }


}
