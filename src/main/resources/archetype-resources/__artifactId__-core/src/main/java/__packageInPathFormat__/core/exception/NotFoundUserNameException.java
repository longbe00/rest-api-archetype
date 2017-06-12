/**
 * 
 */
package ${package}.core.exception;

/**
 * @author mike
 *
 */
public class NotFoundUserNameException extends AbstractErrorCodeException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getCode() {
        return USER_NOT_FOUND_ERROR_CODE;
    }

}
