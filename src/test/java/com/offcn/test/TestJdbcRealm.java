package com.offcn.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

public class TestJdbcRealm {

    @Test
    public void test03() {

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "888888");
        boolean b = false;
        try {
            subject.login(token);
            b = subject.hasRole("admin888");
            System.out.println("有admin权限：" + b);
            System.out.println("认证成功！");
        } catch (Exception e) {
            System.out.println("认证失败！");
        }

        if (b) {
            boolean isquanxian = subject.isPermitted("update");
            System.out.println("是否有update权限:" + isquanxian);
            boolean isquanxian2 = subject.isPermitted("add");
            System.out.println("是否有add权限:" + isquanxian2);
            boolean isquanxian3 = subject.isPermitted("delete");
            System.out.println("是否有delete权限:" + isquanxian3);
            boolean isquanxian4 = subject.isPermitted("query");
            System.out.println("是否有query权限:" + isquanxian4);
        }

        try{
        boolean isall = subject.hasAllRoles(Arrays.asList("admin", "admin888"));
        System.out.println("是否同时具备admin 、admin888 角色：" + isall); //传入一个角色集合，分别返回是否具备该角色
        boolean[] isalls = subject.hasRoles(Arrays.asList("admin", "admin888", "admin999"));
        System.out.println("是否具备admin角色:" + isalls[0]);
        System.out.println("是否具备admin888角色:" + isalls[1]);
        System.out.println("是否具备admin999角色:" + isalls[2]); //是否具备全部权限
        boolean isquanxian = subject.isPermittedAll("add", "update", "query");
        System.out.println("是否具备add、update、query这3组权 限:" + isquanxian); //分别判断是否具备该权限
        boolean[] group = subject.isPermitted("add", "update", "query", "list");
        System.out.println("是否具备add权限:" + group[0]);
        System.out.println("是否具备update权限:" + group[1]);
        System.out.println("是否具备query权限:" + group[2]);
        System.out.println("是否具备list权限:" + group[3]);
    } catch(
    AuthenticationException e)

    {
        System.out.println("登录失败");
    }//退出登陆 subject.logout();
}
}
