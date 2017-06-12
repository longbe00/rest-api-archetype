/**
 * 
 */
package ${package}.core.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;

import ${package}.core.validation.validator.UniqueKeysValidator;

/**
 * @author mike
 *
 */
@NotNull
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Constraint(validatedBy = UniqueKeysValidator.class)
@ReportAsSingleViolation
@Documented
public @interface UniqueKeys {

    String message() default "{com.kg.uppp.buyer.common.annotation.UniqueKeys.message}";

    Class<?>[]groups() default {};

    Class<? extends Payload>[]payload() default {};

    String table() default "";

    String idColumn() default "ID";

    String idProperty() default "id";

}
