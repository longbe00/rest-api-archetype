/**
 * 
 */
package ${package}.core.exception;

/**
 * @author mike
 *
 */
public class PgInterfaceException extends AbstractErrorCodeException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getCode() {
        return PG_ERROR_CODE;
    }

}
