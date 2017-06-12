/**
 * 
 */
package ${package}.core.exception;

/**
 * @author mike
 *
 */
public class ServiceException extends AbstractErrorCodeException {

    private static final long serialVersionUID = 1L;
    
    private String code;

    public ServiceException(String code, Object... args) {
        super(args);
        // TODO Auto-generated constructor stub
        this.code = code;
    }


    @Override
    public String getCode() {
        return this.code;
    }

}
