package com.baizhi.shiro.realm;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类描述信息 (自定义Realm)
 *
 * @author : buxiaoyu
 * @date : 2019-07-28 15:23
 * @version: V_1.0.0
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;


    @Override
    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //通过令牌拿到主体对象
        String principal = (String) authenticationToken.getPrincipal();

        //通过主体对象
        Admin admin = adminService.queryAdminByUsername(principal);

        //创建认证对象
        if(admin!=null){
            if (principal.equals(admin.getAdminName())) {
                AuthenticationInfo authenticationInfo =
                        new SimpleAuthenticationInfo(admin.getAdminName(),admin.getPassword(), ByteSource.Util.bytes(admin.getSalt()),this.getName());
                return authenticationInfo;
            }
            return null;
        }else {
            System.err.println("未查询到用户");
            return null;
        }
    }

    @Override
    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
