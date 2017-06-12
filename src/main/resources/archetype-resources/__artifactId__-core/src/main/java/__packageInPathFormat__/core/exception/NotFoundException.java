/**
 * 
 */
package ${package}.core.exception;

/**
 * @author mike
 *
 */
public class NotFoundException extends AbstractErrorCodeException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getCode() {
        return DATA_NOT_FOUND_ERROR_CODE;
    }

}
