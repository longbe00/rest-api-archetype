/**
 * 
 */
package ${package}.core.mybatis.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import ${package}.core.annotation.DbValue;
import ${package}.core.mybatis.enums.NoticeType;
import ${package}.core.mybatis.enums.YesNoType;

/**
 * @author mike
 *
 */
@MappedTypes({YesNoType.class,NoticeType.class})
public class CustomEnumTypeHandler<E extends Enum<E>> extends EnumTypeHandler<E> {

    private Class<E> type;
    private final E[] enums;
    private final Method invoker;

    public CustomEnumTypeHandler(Class<E> type) {
        super(type);
        this.type = type;
        this.enums = type.getEnumConstants();
        for (Method method : type.getMethods()) {
            if (method.isAnnotationPresent(DbValue.class)) {
                this.invoker = method;
                return;
            }
        }
        this.invoker = null;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        if (invoker != null) {
            Object value = null;
            try {
                value = invoker.invoke(parameter, (Object[]) null);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JdbcType jdbcTypeFromAnnotation = (JdbcType) invoker.getAnnotation(DbValue.class).jdbcType();
            if (jdbcTypeFromAnnotation.equals(JdbcType.UNDEFINED)) {
                Class<?> valueClass = value.getClass();
                if (valueClass.equals(String.class)) {
                    ps.setString(i, (String) value);
                } else if (valueClass.equals(Integer.class)) {
                    ps.setInt(i, (Integer) value);
                } else if (valueClass.equals(Date.class)) {
                    ps.setDate(i, (java.sql.Date) value);
                }
            } else {
                ps.setObject(i, value, jdbcTypeFromAnnotation.TYPE_CODE);
            }
        } else {
            super.setNonNullParameter(ps, i, parameter, jdbcType);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (invoker != null) {
            JdbcType jdbcTypeFromAnnotation = (JdbcType) invoker.getAnnotation(DbValue.class).jdbcType();
            Object valueFromDb = null;
            switch (jdbcTypeFromAnnotation) {
            case VARCHAR:
                valueFromDb = rs.getString(columnName);
                break;
            case DATE:
                valueFromDb = rs.getDate(columnName);
                break;
            case INTEGER:
                valueFromDb = rs.getInt(columnName);
                break;
            case TINYINT:
                valueFromDb = rs.getInt(columnName);
                break;
            default:
                valueFromDb = rs.getObject(columnName);
                break;
            }
            for (E enm : enums) {
                Object value = null;
                try {
                    value = invoker.invoke(enm, (Object[]) null);
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (value.equals(valueFromDb))
                    return enm;
            }
        }
        return super.getNullableResult(rs, columnName);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (invoker == null) {
            JdbcType jdbcTypeFromAnnotation = (JdbcType) invoker.getAnnotation(DbValue.class).jdbcType();
            Object valueFromDb = null;
            switch (jdbcTypeFromAnnotation) {
            case VARCHAR:
                valueFromDb = rs.getString(columnIndex);
                break;
            case DATE:
                valueFromDb = rs.getDate(columnIndex);
                break;
            case INTEGER:
                valueFromDb = rs.getInt(columnIndex);
                break;
            default:
                valueFromDb = rs.getObject(columnIndex);
                break;
            }
            for (E enm : enums) {
                Object value = null;
                try {
                    value = invoker.invoke(type, (Object[]) null);
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (value.equals(valueFromDb))
                    return enm;
            }
        }
        return super.getNullableResult(rs, columnIndex);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (invoker == null) {
            JdbcType jdbcTypeFromAnnotation = (JdbcType) invoker.getAnnotation(DbValue.class).jdbcType();
            Object valueFromDb = null;
            switch (jdbcTypeFromAnnotation) {
            case VARCHAR:
                valueFromDb = cs.getString(columnIndex);
                break;
            case DATE:
                valueFromDb = cs.getDate(columnIndex);
                break;
            case INTEGER:
                valueFromDb = cs.getInt(columnIndex);
                break;
            default:
                valueFromDb = cs.getObject(columnIndex);
                break;
            }
            for (E enm : enums) {
                Object value = null;
                try {
                    value = invoker.invoke(type, (Object[]) null);
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (value.equals(valueFromDb))
                    return enm;
            }
        }
        return super.getNullableResult(cs, columnIndex);
    }

}
