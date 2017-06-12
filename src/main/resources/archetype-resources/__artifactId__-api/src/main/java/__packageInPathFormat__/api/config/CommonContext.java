package ${package}.api.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@PropertySource("classpath:config.properties")
public class CommonContext {
    /**
     * Ant Path Matcher
     * @return
     **/
    @Bean
    public PathMatcher antPathMater() {
        return new AntPathMatcher();
    }


    /**
     * Multipart Resolver
     * @return
     **/
    @Bean
    public CommonsMultipartResolver filterMultipartResolver() {
        CommonsMultipartResolver cmr = new CommonsMultipartResolver();
        cmr.setMaxUploadSize(100000000);
        return cmr;
    }

	

    /**
     * Configuration Properties
     * SpEL 사용 <util:properties id="config" location="classpath:/conf/config.properties" />
     **/
    @Bean
    public PropertiesFactoryBean config() {
        PropertiesFactoryBean pfb = new PropertiesFactoryBean();
        pfb.setLocation(new ClassPathResource("system.properties"));
        return pfb;
    }
	
    /**
     * Configuration Properties
     * SpEL 사용 <util:properties id="config" location="classpath:/conf/config.properties" />
     */
    @Bean
    public PropertiesFactoryBean systemConfig() {
        PropertiesFactoryBean pfb = new PropertiesFactoryBean();
        pfb.setLocation(new ClassPathResource("system.properties"));
        return pfb;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
