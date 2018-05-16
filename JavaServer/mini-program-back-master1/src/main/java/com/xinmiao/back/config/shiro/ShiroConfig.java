package com.xinmiao.back.config.shiro;

import com.xinmiao.back.config.shiro.filter.KickoutSessionControlFilter;
import com.xinmiao.back.config.shiro.realm.SupportedLoginType;
import com.xinmiao.back.config.shiro.realm.TokenRealm;
import com.xinmiao.back.config.shiro.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;


@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是会报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /*
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager(shiroCacheManager());
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        kickoutSessionControlFilter.setKickoutUrl("/api/sessions/authLose"); //配置登录失效的重定向路径
        shiroFilterFactoryBean.getFilters().put("kickout",kickoutSessionControlFilter);
        */

        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        filterChainDefinitionMap.put("/api/**", "anon");

        filterChainDefinitionMap.put("/**", "anon");
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/api/sessions/unauth");//配置权限不够时的重定向路径，在web的话是登录路径

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器,用于校验用户身份
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * @return
     */
    /*
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }
    */

    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCacheManager(shiroCacheManager());
        //userRealm.setCredentialsMatcher(hashedCredentialsMatcher());//为此realm设置凭证匹配器
        //设置此realm只接受TelephoneAndPasswd类型的认证凭证
        userRealm.setSupportedLoginType(SupportedLoginType.TelephoneAndPasswd.getCode());//
        userRealm.setName("userRealm");
        return userRealm;
    }

    @Bean
    public TokenRealm tokenRealm(){
        TokenRealm tokenRealm = new TokenRealm();
        tokenRealm.setCacheManager(shiroCacheManager());
        //tokenRealm.setCredentialsMatcher(hashedCredentialsMatcher());//为此realm设置凭证匹配器
        //设置此realm只接受TelephoneAndToken类型的认证凭证
        tokenRealm.setSupportedLoginType(SupportedLoginType.TelephoneAndToken.getCode());

        tokenRealm.setName("tokenRealm");
        return tokenRealm;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //System.out.println("default Authenticator = " + securityManager.getAuthenticator().getClass().toString());//class org.apache.shiro.authc.pam.ModularRealmAuthenticator
//        ModularRealmAuthenticator默认使用AtLeastOneSuccessfulStrategy策略

        //System.out.println("getAuthenticator = " + securityManager.getAuthenticator().getClass());

        //配置FirstSuccessfulStrategy：只要有一个Realm验证成功即可，只返回第一个Realm身份验证成功的认证信息，其他的忽略；
//        ModularRealmAuthenticator modularRealmAuthenticator = (ModularRealmAuthenticator)securityManager.getAuthenticator();
//        modularRealmAuthenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());

        //设置realm.
//        securityManager.setRealm(myShiroRealm());

        //设置自定义多realm认证器
        securityManager.setAuthenticator(new MyModularRealmAuthenticator());

        //添加多个realm认证
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm());
        realms.add(tokenRealm());
        securityManager.setRealms(realms);

        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(shiroCacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }


    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisCacheManager shiroCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }


    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(1800);// 配置缓存过期时间
        redisManager.setTimeout(timeout);
        // redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * shiro session的管理
     */
    //自定义sessionManager
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        mySessionManager.setSessionDAO(redisSessionDAO());

        return mySessionManager;
    }


    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
