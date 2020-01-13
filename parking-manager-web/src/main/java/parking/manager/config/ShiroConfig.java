package parking.manager.config;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author huang
 * @date 2020/1/13 14:20
 * @Disc
 **/
@Configuration
public class ShiroConfig {


    /*
    创建shirofilterfactorybean
     */
    @Bean()
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        //设置拦截器
        Map<String, String> map = new LinkedMap();
        //登录不需要权限
        map.put("/login", "anon");
        map.put("/user/**","roles[guest]");
        map.put("/car","roles[admin]");
        map.put("/*", "authc");

        bean.setUnauthorizedUrl("unauth");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    /*
    注入securityManager
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }

    /*
    创建自定义realm
     */
    @Bean(value = "myRealm")
    public MyRealm getMyRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher) {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }

    /*
   创建加密匹配器
    */
    @Bean(value = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(3);
        return hashedCredentialsMatcher;
    }
}
