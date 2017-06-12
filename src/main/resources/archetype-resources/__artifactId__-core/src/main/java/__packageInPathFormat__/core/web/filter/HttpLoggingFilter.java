package ${package}.core.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ${package}.core.util.StringHelper;

@Component
public class HttpLoggingFilter implements Filter{
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        log.debug("======================================          REQUEST         ======================================{}" );
        Enumeration<String> requestHeaderNames = requestWrapper.getHeaderNames();
        while (requestHeaderNames.hasMoreElements()) {
            String headerName = requestHeaderNames.nextElement();
            Enumeration<String> headers = requestWrapper.getHeaders(headerName);
            while (headers.hasMoreElements()) {
                String headerValue = headers.nextElement();
                log.debug(" Request HEADER - {} : {} ", headerName, headerValue );
            }
            
        }
        if(requestWrapper.getHeader("Server")==null||requestWrapper.getHeader("Server").isEmpty()){ //response데이타는 제외
            log.debug(" Request METHOD:{} URI:{}  QUERYSTRING:{}", requestWrapper.getMethod(), requestWrapper.getRequestURI(), requestWrapper.getQueryString());
            if(requestWrapper.getMethod().equalsIgnoreCase("POST")||requestWrapper.getMethod().equalsIgnoreCase("PUT")){
                if((requestWrapper.getHeader("content-type")!=null&&requestWrapper.getHeader("content-type").indexOf("multipart")<0)){ // 이미지 업로드는 제외
                    log.debug(" Request Body:  {}", StringHelper.getString(requestWrapper.getInputStream()));
                }
                
            }
        }
        
        chain.doFilter(requestWrapper, response);
    }

    public void destroy() {}
    
    
}
