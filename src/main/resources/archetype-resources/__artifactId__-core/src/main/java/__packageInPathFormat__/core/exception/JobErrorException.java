/**
 * 
 */
package ${package}.core.exception;

/**
 * @author mike
 *
 */
public class JobErrorException extends AbstractErrorCodeException {

    private static final long serialVersionUID = 1L;

    
    public JobErrorException(Object... args) {
        super(args);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getCode() {
        return CLASS_NOT_FOUND;
    }

}
