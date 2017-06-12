package ${package}.api.common.web.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import ${package}.core.exception.ExceptionControllerAdvice.ExceptionVo;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler
{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    
    @Autowired
    MessageSourceAccessor msa;

    @Override
    public void handle(HttpServletRequest request,HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        
        String errorCode = "1500";
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(Integer.parseInt(errorCode));
        response.getWriter().write(mapper.writeValueAsString(new ExceptionVo(errorCode, msa.getMessage(errorCode).split(";")[1])));
        response.getWriter().flush();
        response.getWriter().close();
        
    }
}

