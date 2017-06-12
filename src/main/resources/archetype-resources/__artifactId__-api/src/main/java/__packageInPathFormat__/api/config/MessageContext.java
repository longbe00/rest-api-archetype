package ${package}.api.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageContext {

    /**
     * MessageSource
     * 
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource rrbms = new ReloadableResourceBundleMessageSource();
        rrbms.setBasenames("classpath:/messages/message", "classpath:/messages/error/error", "classpath:/messages/validation/validation");
        rrbms.setDefaultEncoding("UTF-8");
        rrbms.setCacheSeconds(60);

        return rrbms;
    }

    @Bean(name = "messageSourceAccessor")
    public MessageSourceAccessor messageSourceAccessor() {
        return new MessageSourceAccessor(messageSource());
    }

}
