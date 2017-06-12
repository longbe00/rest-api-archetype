/**
 *
 */
package ${package}.core.mybatis.handler;

import java.sql.CallableStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * @author mike
 *
 */
@MappedJdbcTypes(JdbcType.CHAR)
@MappedTypes(Boolean.class)
public class BooleanYNTypeHandler extends BaseTypeHandler<Boolean> {

    private static final String YES = "Y";
    private static final String NO = "N";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType)
            throws SQLException {
        boolean b = ((Boolean) parameter).booleanValue();
        ps.setString(i, b ? YES : NO);
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return convertStringToBooelan(rs.getString(columnName));
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convertStringToBooelan(rs.getString(columnIndex));
    }

    @Override
    public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convertStringToBooelan(cs.getString(columnIndex));
    }

    private Boolean convertStringToBooelan(String strValue) throws SQLException {
        if (YES.equalsIgnoreCase(strValue))
            return new Boolean(true);
        else if (NO.equalsIgnoreCase(strValue))
            return new Boolean(false);
        else
            throw new SQLException(
                    "Unexpected value " + strValue + " found where " + YES + " or " + NO + " was expected.");
    }

}