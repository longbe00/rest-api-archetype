package ${package}.api.common.web.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import ${package}.core.exception.ExceptionControllerAdvice.ExceptionVo;


public class CustomAccessDenyEntryPoint implements AuthenticationEntryPoint{
    @Autowired
    MessageSourceAccessor msa;
    
    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        // TODO Auto-generated method stub

            String errorCode = "1500";
            String message = "";
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(Integer.parseInt(errorCode));
            response.getWriter().write(mapper.writeValueAsString(new ExceptionVo(errorCode, msa.getMessage(errorCode).split(";")[1])));
            response.getWriter().flush();
            response.getWriter().close();
        
    }

}
