/**
 * 
 */
package ${package}.core.exception;

/**
 * @author mike
 *
 */
public class ValidationException extends AbstractErrorCodeException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getCode() {
        return VALIDATION_ERROR_CODE;
    }

}
