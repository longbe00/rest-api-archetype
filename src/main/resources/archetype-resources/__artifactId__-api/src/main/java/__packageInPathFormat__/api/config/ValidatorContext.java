package ${package}.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ${package}.core.validation.validator.UniqueNameValidator;

@Configuration
public class ValidatorContext {

    @Bean
    public UniqueNameValidator uniqueNameValidator() {
        return new UniqueNameValidator();
    }
    
}
