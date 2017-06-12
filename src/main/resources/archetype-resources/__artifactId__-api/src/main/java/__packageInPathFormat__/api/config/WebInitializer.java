/**
 * 
 */
package ${package}.api.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Conventions;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import ${package}.core.web.filter.HttpLoggingFilter;
import ${package}.core.web.filter.CORSFilter;

/**
 * @author mike
 *
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private void addCORSFilter(ServletContext servletContext) {
        CORSFilter filter = new CORSFilter();
        String filterName = Conventions.getVariableName(filter);
        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
        registration.setAsyncSupported(isAsyncSupported());
        registration.addMappingForUrlPatterns(getDispatcherTypes(), false, "/*");
    }

    private void addCharacterEncodingFilter(ServletContext servletContext) {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        String filterName = Conventions.getVariableName(filter);
        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
        registration.setAsyncSupported(isAsyncSupported());
        // registration.addMappingForServletNames(getDispatcherTypes(), false, getServletName());
        registration.addMappingForUrlPatterns(getDispatcherTypes(), false, "/*");
    }

    private void addMultipartFilter(ServletContext servletContext) {
        MultipartFilter filter = new MultipartFilter();

        String filterName = Conventions.getVariableName(filter);
        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
        registration.setAsyncSupported(isAsyncSupported());
        // registration.addMappingForServletNames(getDispatcherTypes(), false, getServletName());
        registration.addMappingForUrlPatterns(getDispatcherTypes(), false, "/*");
        registration.setInitParameter("multipartResolverBeanName", "filterMultipartResolver");
    }

    private void addHiddenHttpMethodFilter(ServletContext servletContext) {
        HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
        String filterName = Conventions.getVariableName(filter);
        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
        registration.setAsyncSupported(isAsyncSupported());
        // registration.addMappingForServletNames(getDispatcherTypes(), false, getServletName());
        registration.addMappingForUrlPatterns(getDispatcherTypes(), false, "/*");
    }
    
    private void addHttpLoggingFilter(ServletContext servletContext) {
        HttpLoggingFilter filter = new HttpLoggingFilter();
        String filterName = Conventions.getVariableName(filter);
        FilterRegistration.Dynamic  registration = servletContext.addFilter(filterName, filter);
        registration.setAsyncSupported(isAsyncSupported());
        registration.addMappingForUrlPatterns(getDispatcherTypes(), false, "/*");
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        addCORSFilter(servletContext);
        addCharacterEncodingFilter(servletContext);
        addMultipartFilter(servletContext);
        addHiddenHttpMethodFilter(servletContext);
        addHttpLoggingFilter(servletContext);
        
        super.onStartup(servletContext);

        //servletContext.addListener(new DefaultSessionListener());
        servletContext.addListener(new SessionListener());
        servletContext.addListener(new RequestContextListener());
        // for log4j2
        Configurator.initialize("config", null, "classpath:/log/log4j2.xml");

    }

    private EnumSet<DispatcherType> getDispatcherTypes() {
        // null 은 DispatcherType.REQUEST 임
        // return null

        return isAsyncSupported()
                ? EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE,
                        DispatcherType.ASYNC)
                : EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { AppContext.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebContext.class , SwaggerMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        // 옵션과 url mapping을 사용할 수 없어서 직접 위에서 등록함
        return null;
    }

    @Override
    protected String getServletName() {
        return "dispatcherServlet";

    }

    @Override
    protected boolean isAsyncSupported() {
        return false;
    }
    
    //실제  Request 객체를 사용하지 않은 클래스에서 해당 Request를 사용하기 위하여 등록
    @Bean public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    } 
}