package com.xiaowei.crowd.test;

import com.xiaowei.entity.Admin;
import com.xiaowei.mapper.AdminMapper;
import com.xiaowei.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    /**
     * 测试声明式事务
     */
    @Test
    public void testTransactionManager(){
        Admin admin = new Admin(null, "jerry", "杰瑞", "123456", "jerry@qq.com", null);
        adminService.saveAdmin(admin);
    }

    /**
     * 测试日志打印功能
     */
    @Test
    public void testLog(){
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        logger.debug("hello,debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
    }

    @Test
    public void testMapper(){
        Admin admin = new Admin(null, "tom", "汤姆", "123123", "tom@qq.com", null);
        int insert = adminMapper.insert(admin);
        System.out.println(insert);
    }


    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
