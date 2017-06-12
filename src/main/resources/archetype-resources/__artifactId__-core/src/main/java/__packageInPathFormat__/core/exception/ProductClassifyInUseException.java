package ${package}.core.exception;

public class ProductClassifyInUseException extends AbstractErrorCodeException {

    private static final long serialVersionUID = 3859380843870971058L;

    @Override
    public String getCode() {
        return PRODUCT_CLASSIFY_IN_USE;
    }

}
