/**
 * 
 */
package ${package}.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author mike
 *
 */
@Configuration
@ComponentScan(
        basePackages="${package}",
        excludeFilters = {
            @Filter(type = FilterType.ANNOTATION, value = Controller.class),
            @Filter(type = FilterType.ANNOTATION, value = ControllerAdvice.class) }
)
@Import({
    CommonContext.class,
    MessageContext.class,
    //RestContext.class,
    DatabaseConfig.class,
    CustomMyBatisContext.class,
    ValidatorContext.class,
    WebContext.class,
//  EhcacheContext.class,
//  RabbitMqConfig.class,
//  TaskContext.class
})
public class AppContext {
}
