package com.offcn.test;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

public class customRealm extends AuthorizingRealm {
    /**
     * 授权
     * @param principals
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取身份信息
        String username = (String) principals.getPrimaryPrincipal();
        // 根据身份信息从数据库中查询角色数据
        // ....这里使用静态数据模拟
        List<String> roles = new ArrayList<String>();
        roles.add("role1");
        roles.add("role2"); // 根据身份信息从数据库中查询权限数据
        // ....这里使用静态数据模拟
        List<String> permissions = new ArrayList<>();
        permissions.add("user:create");
        permissions.add("user:delete");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //将权限信息封装到  AuthorizationInfo
        for (String permission : permissions) {
            simpleAuthorizationInfo.addStringPermission(permission);
        }
        //将角色信息封装到 AuthorizationInfo
        for (String role : roles) {
            simpleAuthorizationInfo.addRole(role);
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        String username = (String) token.getPrincipal();

        //获取密码
        String password = String.valueOf((char[]) token.getCredentials());
        System.out.println("用户名：" + username + "===密码：" + password);
        if (!"test".equals(username)) throw new UnknownAccountException();
        if (!"123456".equals(password)) throw new IncorrectCredentialsException();
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
