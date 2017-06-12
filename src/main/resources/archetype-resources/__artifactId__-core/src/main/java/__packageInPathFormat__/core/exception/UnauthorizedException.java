/**
 * 
 */
package ${package}.core.exception;

/**
 * @author mike
 *
 */
public class UnauthorizedException extends AbstractErrorCodeException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getCode() {
        return UNAUTHORIZED_ERROR_CODE;
    }

}
