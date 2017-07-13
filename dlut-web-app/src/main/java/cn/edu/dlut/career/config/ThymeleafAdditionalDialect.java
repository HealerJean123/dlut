package cn.edu.dlut.career.config;

import cn.edu.dlut.career.thymeleaf.PubCodeDialect;
import cn.edu.dlut.career.thymeleaf.ShiroDialect;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 在Thymeleaf的自动配置前先将附加的Thymeleaf自定义方言注入进去
 */
@Configuration
@AutoConfigureBefore(ThymeleafAutoConfiguration.class)
public class ThymeleafAdditionalDialect {
    @Bean
    @ConditionalOnMissingBean
    public PubCodeDialect pubcodeDialect() {
        return new PubCodeDialect();
    }

    @Bean
    @ConditionalOnMissingBean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
