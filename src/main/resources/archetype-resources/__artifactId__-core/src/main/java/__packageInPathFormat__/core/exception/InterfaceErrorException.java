/**
 * 
 */
package ${package}.core.exception;

/**
 * @author mike
 *
 */
public class InterfaceErrorException extends AbstractErrorCodeException {

    private static final long serialVersionUID = 1L;

    
    public InterfaceErrorException(Object... args) {
        super(args);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getCode() {
        return UNKNOWN_SERVER_ERROR_CODE;
    }

}
