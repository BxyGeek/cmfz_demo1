package com.baizhi.conf;

import org.apache.shiro.mgt.SecurityManager;
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
        //登陆放行
        map.put("/login/**","anon");
        //静态资源放行
        map.put("/statics/**","anon");
        //拦截所有请求
        map.put("/**","authc");
        shiroFilterFactoryBean.setLoginUrl("/login/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager getSecurityManager(){
        SecurityManager securityManager = new DefaultWebSecurityManager();
        return securityManager;
    }

}
