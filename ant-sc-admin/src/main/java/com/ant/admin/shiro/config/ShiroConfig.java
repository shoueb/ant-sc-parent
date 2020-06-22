package com.ant.admin.shiro.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: Shiro配置类
 * @Author:  Liu
 * @Date: 2020/6/15 09:19
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //登录地址
        shiroFilterFactoryBean.setLoginUrl("/login");
        //验证失败跳转地址
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");

        //添加【自定义过滤器】
        Map<String, Filter> filterMap=shiroFilterFactoryBean.getFilters();
        filterMap.put("authc",new MyShiroFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //设置拦截器
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();

        //登录不拦截   "anon" 允许立即访问路径而不执行任何类型的安全检查的筛选器
        filterChainDefinitionMap.put("/login","anon");
        //api目录下不拦
        filterChainDefinitionMap.put("/api/**","anon");
        //js、css、images等静态资源不设置拦截
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/turntalbe/**","anon");

        filterChainDefinitionMap.put("/static/image/**","anon");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/images/**","anon");
        filterChainDefinitionMap.put("/editor-app/**","anon");
        filterChainDefinitionMap.put("/fonts/**","anon");
        filterChainDefinitionMap.put("/img/**","anon");
        filterChainDefinitionMap.put("/ad/**","anon");
        // "authc" 需要对请求用户进行身份验证才能继续请求,否则强制用户进行登录
        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 注入securityManager，SecurityManager用于管理Realm、Session会话等
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //设置Realm,customRealm()为自定义的Realm
        securityManager.setRealm(customRealm());
        //设置SessionManager,此处使用DefaultWebSessionManager
        securityManager.setSessionManager(sessionManager());
        //设置缓存管理，此处使用RedsiCacheManager
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    /**
     * 创建RedisManager
     * @return
     */
    public RedisManager redisManager(){
        return new RedisManager();
    }

    /**
     * 缓存管理，设置缓存管理器为RedisCacheManager
     * @return
     */
    private CacheManager cacheManager() {
        RedisCacheManager cacheManager=new RedisCacheManager();
        cacheManager.setRedisManager(redisManager());
        return cacheManager;
    }

    /**
     * SessionManager，用于Session信息的管理
     * @return
     */
    public DefaultWebSessionManager sessionManager() {
        //SessionDao,Shiro用于会话的CRUD,创建的SessionDao重写的AbstractSessionDao
        ShiroSessionDao sessionDao=new ShiroSessionDao();
        //SessionManager,会话管理器，为减少重复读取Redis,重写DefaultWebSessionManager的retrieveSession()方法
        ShiroSessionManager sessionManager=new ShiroSessionManager();
        sessionManager.setSessionDAO(sessionDao);
        return sessionManager;
    }


    /**
     * 自定义身份认证，
     * @return
     */
    @Bean
    public CustomRealm customRealm(){
        return new CustomRealm();
    }


    @Bean(name="lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }



}
