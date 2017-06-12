/**
 * 
 */
package ${package}.core.validation;

/**
 * @author mike
 *
 */
public interface ValidationService {

    public Boolean isFieldExist(final String table, final String column, final Object fieldValue, final String idColumn,
            final Object excludeId);

}
