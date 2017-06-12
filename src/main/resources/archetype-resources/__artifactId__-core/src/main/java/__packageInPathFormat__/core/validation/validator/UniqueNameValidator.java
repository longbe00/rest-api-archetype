/**
 * 
 */
package ${package}.core.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ${package}.core.validation.ValidationService;
import ${package}.core.validation.annotation.UniqueName;

/**
 * @author mike
 *
 */
public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    @Autowired
    ValidationService validationService;

    private String tableName;
    private String columnName;

    public void initialize(UniqueName constraintAnnotation) {
        this.tableName = constraintAnnotation.tableName();
        this.columnName = constraintAnnotation.columnName();
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !validationService.isFieldExist(tableName, columnName, value, null, null);
    }

}
