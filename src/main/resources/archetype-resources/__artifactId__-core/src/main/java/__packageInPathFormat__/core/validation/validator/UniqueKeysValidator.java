/**
 * 
 */
package ${package}.core.validation.validator;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.text.MessageFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;

import ${package}.core.validation.ValidationService;
import ${package}.core.validation.annotation.UniqueKey;
import ${package}.core.validation.annotation.UniqueKeys;

/**
 * @author mike
 *
 */
public class UniqueKeysValidator implements ConstraintValidator<UniqueKeys, Object> {

    @Autowired
    ValidationService validationService;

    @Autowired
    MessageSourceAccessor messageSourceAccessor;

    private String table;
    private String idColumn;
    private String idProperty;

    public void initialize(UniqueKeys constraintAnnotation) {
        this.table = constraintAnnotation.table();
        this.idColumn = constraintAnnotation.idColumn();
        this.idProperty = constraintAnnotation.idProperty();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Object excludeId = new PropertyDescriptor(idProperty, value.getClass()).getReadMethod().invoke(value,
                    (Object[]) null);
            for (Field field : value.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(UniqueKey.class)) {
                    String column = field.getAnnotation(UniqueKey.class).column();
                    Object fieldValue = new PropertyDescriptor(field.getName(), value.getClass()).getReadMethod()
                            .invoke(value, (Object[]) null);
                    if (validationService.isFieldExist(table, column, fieldValue, idColumn, excludeId)) {
                        String property = context.getDefaultConstraintMessageTemplate();
                        String message = messageSourceAccessor.getMessage(property.substring(1, property.length() - 1));
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate(
                                MessageFormat.format(message, field.getAnnotation(UniqueKey.class).description()))
                                .addConstraintViolation();
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
