package com.offcn.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class TestShiro {


    @Test
    public void test01(){

        //创建工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        SecurityManager manager = factory.getInstance();

        SecurityUtils.setSecurityManager(manager);

        //获取subject 创建token
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin","admin");
        try {
            subject.login(token);
            System.out.println("登录认证成功！");
        }catch (Exception e){
            System.out.println("认证失败！");
        }

    }
}
