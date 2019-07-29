package com.baizhi.shiro.conf;

import com.baizhi.shiro.realm.MyRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 类描述信息 (配置shiro过滤器)
 *              将这个过滤器交由spring工厂去管理
 * @author : buxiaoyu
 * @date : 2019-07-28 11:44
 * @version: V_1.0.0
 */
@Configuration
public class ShiroFilterConf {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new LinkedHashMap<>();
        //静态资源放行
        map.put("/login/assets/**","anon");
        map.put("/statics/**","anon");
        //验证码放行
        map.put("/code/getCode","anon");
        //登陆请求放行--原页面
        map.put("/admin/login","anon");
        //Shiro登陆请求放行
        map.put("/shiroAdmin/login","anon");
        //首页可以访问
        map.put("/index.jsp","anon");
        //拦截所有请求
        map.put("/**","authc");
        shiroFilterFactoryBean.setLoginUrl("/login/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager getSecurityManager(Realm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        MyRealm myRealm = new MyRealm();
//        securityManager.setRealm(myRealm);
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public Realm getRealm(CredentialsMatcher credentialsMatcher){
        MyRealm myRealm = new MyRealm();
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        credentialsMatcher.setHashAlgorithmName("MD5");
//        credentialsMatcher.setHashIterations(1024);
        myRealm.setCredentialsMatcher(credentialsMatcher);
        return myRealm;
    }

    @Bean
    public CredentialsMatcher getCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        return credentialsMatcher;
    }




}
