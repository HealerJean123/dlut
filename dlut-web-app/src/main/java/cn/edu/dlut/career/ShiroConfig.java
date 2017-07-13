package cn.edu.dlut.career;

import java.util.*;

import cn.edu.dlut.career.shiro.UserRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;


/**
 * shiro 相关配置
 */
@Configuration
public class ShiroConfig {
    private static String LOGIN_URL = "/login.html";
    private static String SUCCESS_URL = "/showPage";
    private static String UNAUTHORIZED_URL = "/login.html";
    @Bean(name = "userRealm")
    public UserRealm getShiroRealm() {
        return new UserRealm();
    }

    /**
     * redis 共享session , 修改session的共享域
     * @return
     */
    @Bean
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager d = new DefaultWebSessionManager();
        //d.getSessionIdCookie().setDomain(cookie_domain);
        return d;
    }



    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager dsm = new DefaultWebSecurityManager();
        //CAS dsm.setRealm(myRealm());
        List<Realm> realms = new ArrayList<>();
        realms.add(getShiroRealm());
        dsm.setRealms(realms);
        //CAS
        //dsm.setSubjectFactory(defaultWebSubjectFactory());
        dsm.setSessionManager(defaultWebSessionManager());
        dsm.setCacheManager(ehCacheManager());
        return dsm;
    }

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean sf = new ShiroFilterFactoryBean();
        sf.setSecurityManager(defaultWebSecurityManager());
        sf.setSuccessUrl(SUCCESS_URL);
        sf.setLoginUrl(LOGIN_URL);
        sf.setUnauthorizedUrl(UNAUTHORIZED_URL);

        Map<String, String> chainMap = new LinkedHashMap<>();
        chainMap.put("/index.html", "anon");
        chainMap.put("/portals/**", "anon");
        chainMap.put("/login.html", "anon");
        chainMap.put("/register/**", "anon");
        chainMap.put("/js/**", "anon");
        chainMap.put("/css/**", "anon");
        chainMap.put("/img/**", "anon");
        chainMap.put("/fonts/**", "anon");
        chainMap.put("/less/**", "anon");
        chainMap.put("/**.ico","anon");
        chainMap.put("/prepCompany/**","authc,roles[PREPCOMPANY]");
        chainMap.put("/student/**","authc,roles[STUDENT]");
        chainMap.put("/teacher/**","authc,roles[TEACHER]");
        chainMap.put("/company/**","authc,roles[COMPANY]");
        chainMap.put("/**", "authc");
        sf.setFilterChainDefinitionMap(chainMap);
        return sf;
    }

    //保证实现了Shiro内部lifecycle函数的bean执行
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        LifecycleBeanPostProcessor lb = new LifecycleBeanPostProcessor();
        return lb;
    }

    //<!--AOP式方法级权限检查 -->
    //<!--Enable Shiro Annotations for Spring-configured beans. Only run after -->
    //<!--the lifecycleBeanProcessor has run: -->
    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor auth = new AuthorizationAttributeSourceAdvisor();
        auth.setSecurityManager(defaultWebSecurityManager());
        return auth;
    }


    @Bean(name = "ehCacheManager")
    public EhCacheManager ehCacheManager() {
        return new EhCacheManager();
    }
}
