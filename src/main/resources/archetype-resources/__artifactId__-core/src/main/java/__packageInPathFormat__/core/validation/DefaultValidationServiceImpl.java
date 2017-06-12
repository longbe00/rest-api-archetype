/**
 * 
 */
package ${package}.core.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mike
 *
 */
@Service
public class DefaultValidationServiceImpl implements ValidationService {

    @Autowired
    DefaultValidationMapper validationMapper;

    public Boolean isFieldExist(String table, String column, Object fieldValue, String idColumn, Object excludeId) {
        return !validationMapper.getMatchedFields(table, column, fieldValue, idColumn, excludeId).isEmpty();
    }

}
