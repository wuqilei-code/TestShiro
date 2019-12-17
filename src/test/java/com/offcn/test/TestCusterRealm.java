package com.offcn.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

public class TestCusterRealm {


    @Test
    public void test2() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("test", "123456");
        try {
            subject.login(token);
            System.out.println("认证成功！");
        } catch (Exception e) {
            System.out.println("认证失败！");
        }
        // 用户认证状态
        Boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("用户认证状态：" + isAuthenticated); // 用户授权检测 基于角色授权
        // 是否有某一个角色
        System.out.println("用户是否拥有一个角色：" + subject.hasRole("role1"));
        // 是否有多个角色

        System.out.println("用户是否拥有多个角色：" + subject.hasAllRoles(Arrays.asList("role1", "role2")));
        System.out.println("是否拥有某一个权限：" + subject.isPermitted("user:create"));
        System.out.println("是否拥有多个权限：" + subject.isPermittedAll("user:create:1", "user:delete")); //检查权限
        //subject.checkPermission("user:update");
        subject.checkPermissions("user:create:1", "user:delete");


    }
}
