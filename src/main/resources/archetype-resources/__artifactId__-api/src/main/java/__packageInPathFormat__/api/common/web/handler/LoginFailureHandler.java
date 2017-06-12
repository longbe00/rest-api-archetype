package ${package}.api.common.web.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import ${package}.core.exception.ExceptionControllerAdvice.ExceptionVo;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler
{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private String ajaxHeader = "AJAX";
    @Autowired
    protected MessageSourceAccessor accessor;
    
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth) throws IOException, ServletException
    {   
//        String errorCode = ApiConstants.LOGIN_FAIL;
//        String[] messageArguments = {auth.getMessage()};
//        ErrorCodeResolver resolver = new ErrorCodeResolver(errorCode, accessor);
//        ResponseError responseError = 
//                        new ResponseError(resolver, request.getRequestURI(), messageArguments);
        
//        log.debug(responseError.getMessage());
        //responseError.setMessage(auth.getMessage());
        ObjectMapper mapper = new ObjectMapper();
        ModelMap mm = new ModelMap();
//        mm.put("error",responseError );
        mm.put("error", "error");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(400);
        response.getWriter().write(mapper.writeValueAsString(new ExceptionVo("10001", auth.getMessage())));
        response.getWriter().flush();
        response.getWriter().close();
        
    }
}

