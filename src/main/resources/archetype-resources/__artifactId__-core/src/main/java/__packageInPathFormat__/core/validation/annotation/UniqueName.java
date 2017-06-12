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

import ${package}.core.validation.validator.UniqueNameValidator;

/**
 * @author mike
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.FIELD })
@Constraint(validatedBy = UniqueNameValidator.class)
@ReportAsSingleViolation
@Documented
@NotNull
@Deprecated
public @interface UniqueName {

    String message() default "{com.kg.uppp.buyer.common.annotation.UniqueName.message}";

    Class<?>[]groups() default {};

    Class<? extends Payload>[]payload() default {};

    String tableName() default "";

    String columnName() default "";

}
