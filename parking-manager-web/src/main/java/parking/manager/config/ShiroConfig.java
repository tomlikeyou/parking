package parking.manager.config;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Map;

/**
 * @author huang
 * @date 2020/1/13 14:20
 * @Disc
 **/
@Configuration
public class ShiroConfig {

    /**
     * @param securityManager
     * @return shiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        //设置默认的登录URL
        bean.setLoginUrl("/login");
        //设置拦截器
        Map<String, String> map = new LinkedMap();
        //登录不需要权限
        map.put("/login", "anon");
        map.put("/file", "anon");
        map.put("/images/**","anon");
        map.put("/logout", "logout");
        map.put("/user/**", "roles[admin]");
        map.put("/file/**", "roles[admin]");
        map.put("/menu/**","roles[admin]");
        map.put("/druid/**", "roles[admin]");
        map.put("/**", "authc");
        bean.setUnauthorizedUrl("/unauthorized");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    /**
     * @param myRealm
     * @param mySessionManager
     * @return securityManager
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm, @Qualifier("mySessionManager") SessionManager mySessionManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        defaultWebSecurityManager.setSessionManager(mySessionManager);
        return defaultWebSecurityManager;
    }

    /**
     * @param hashedCredentialsMatcher
     * @return 创建自定义realm
     */
    @Bean(value = "myRealm")
    public MyRealm getMyRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher) {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }

    /**
     * @return 创建加密匹配器
     */
    @Bean(value = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    /**
     * @return 自定义sessionManager
     */
    @Bean(value = "mySessionManager")
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        return mySessionManager;
    }

    /**
     * 开启shiro aop注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 注册全局异常处理器
     */
    /*@Bean(value = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new MyExceptionHandler();
    }*/
}
