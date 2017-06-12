/**
 * 
 */
package ${package}.core.exception;

/**
 * @author mike
 *
 */
public class AlreadySendAuthException extends AbstractErrorCodeException {

    private static final long serialVersionUID = 1L;
    
    public AlreadySendAuthException(Object... args) {
        super(args);
        // TODO Auto-generated constructor stub
    }


    @Override
    public String getCode() {
        return ALREADY_SEND_AUTH_CODE;
    }

}
