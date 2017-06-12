/**
 * 
 */
package ${package}.core.exception;

/**
 * @author mike
 *
 */
public abstract class AbstractErrorCodeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // Error code constatns.

    public static final String UNKNOWN_SERVER_ERROR_CODE = "0000";

    public static final String VALIDATION_ERROR_CODE = "0001";

    public static final String IO_ERROR_CODE = "0002";

    public static final String DATA_NOT_FOUND_ERROR_CODE = "0003";
    
    public static final String USER_NOT_FOUND_ERROR_CODE = "1400";
    
    public static final String NOT_MATCH_PASSWORD_ERROR_CODE = "1401";
    public static final String NOT_MATCH_AUTH_NUM_ERROR_CODE = "1405";
    
    public static final String UNAUTHORIZED_ERROR_CODE = "1500";
    
    public static final String LOCK_ID_ERROR_CODE = "1402";
    public static final String DISABLED_ID_ERROR_CODE = "1403";
    public static final String EXTERNAL_AUTH_REQUIRED = "1404";
    
    public static final String PRODUCT_CLASSIFY_IN_USE = "2001";
    public static final String INTERFACE_ERROR_CODE = "5001";
    public static final String PG_ERROR_CODE = "6001";
    
    public static final String CLASS_NOT_FOUND = "4001";
    
    public static final String ALREADY_SEND_AUTH_CODE = "4002";

    //

    protected Object[] args;

    public abstract String getCode();

    public AbstractErrorCodeException() {
        super();
    }

    public AbstractErrorCodeException(final Object... args) {
        super();
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }

}
