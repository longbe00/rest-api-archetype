/**
 * 
 */
package ${package}.core.exception;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author mike
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @Autowired
    MessageSourceAccessor msa;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionVo handleUnknownException(HttpServletResponse response, Exception e) {

        LOGGER.error("Unknown Exception has caught.", e);
        String errorCode = new UnknownServerErrorException().getCode();
        return new ExceptionVo(errorCode, msa.getMessage(errorCode).split(";")[1]);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionVo handleMethodArgumentNotValidException(HttpServletResponse response,
            MethodArgumentNotValidException e) {

        LOGGER.error("Validation Exception has caught.", e);
        String errorCode = new ValidationException().getCode();
        String message = "";
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            message = error.getDefaultMessage();
            break;
        }
        return new ExceptionVo(errorCode, message);
    }

    @ExceptionHandler(AbstractErrorCodeException.class)
    public @ResponseBody ExceptionVo handleAbstractCommonException(HttpServletResponse response,
            AbstractErrorCodeException e) {

        LOGGER.error("Exception has caught.", e);
        response.setStatus(Integer.parseInt(msa.getMessage(e.getCode(), e.getArgs()).split(";")[0]));
        return new ExceptionVo(e.getCode(), msa.getMessage(e.getCode(), e.getArgs()).split(";")[1]);
    }

    @Data
    @AllArgsConstructor
    public static class ExceptionVo {

        private String errorCode;
        private String message;

    }

}
