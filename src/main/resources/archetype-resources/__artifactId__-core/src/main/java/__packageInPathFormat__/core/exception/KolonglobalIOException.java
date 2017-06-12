/**
 * 
 */
package ${package}.core.exception;

/**
 * @author mike
 *
 */
public class KolonglobalIOException extends AbstractErrorCodeException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getCode() {
        return IO_ERROR_CODE;
    }

}
