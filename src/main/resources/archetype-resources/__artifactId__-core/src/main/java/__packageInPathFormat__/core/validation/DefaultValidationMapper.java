/**
 * 
 */
package ${package}.core.validation;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author mike
 *
 */
@Mapper
public interface DefaultValidationMapper {

    @Select("<script>"
            + "SELECT ${column} FROM ${table} WHERE ${column} = #fieldValue "
            + "<if test='excludeId != null'>"
            + "    AND ${idColumn} != #excludeId"
            + "</if>"
            + "</script>")
    public List<Object> getMatchedFields(final @Param("table") String table,
            final @Param("column") String column, final @Param("fieldValue") Object fieldValue,
            final @Param("idColumn") String idColumn, final @Param("excludeId") Object excludeId);

}
