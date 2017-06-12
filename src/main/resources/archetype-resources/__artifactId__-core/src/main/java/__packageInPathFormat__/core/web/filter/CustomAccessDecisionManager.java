package ${package}.core.web.filter;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

public class CustomAccessDecisionManager implements AccessDecisionManager{
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    
    public void decide(Authentication authentication, Object object,
            Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        // TODO Auto-generated method stub


//        log.debug("Deciding if {} has permission for target {}, which requires: {}", new Object[]{authentication, object, configAttributes});
//        boolean isAuthentication = false;
//        String requiredPermission ="";
//        for (ConfigAttribute attribute : configAttributes) {
//            requiredPermission = attribute.getAttribute();
//            isAuthentication = checkPermission(authentication, requiredPermission);
//            if(isAuthentication == true)
//                break;
//            if(requiredPermission.equals("ROLE_ACCESS_DENY"))
//                throw new CumstomAccessDeniedException(ApiConstants.WRITE_AUTHORITY_FAIL, String.format("User '%s' does not have permission '%s'", authentication.getPrincipal(), requiredPermission));
//        }
//        if(isAuthentication == false){
//            throw new CumstomAccessDeniedException(ApiConstants.URL_ACCESS_DENY, String.format("User '%s' does not have permission '%s'", authentication.getPrincipal(), requiredPermission));
//        }
        
    }
    public boolean checkPermission(Authentication authentication, String requiredPermission) {
        if (log.isTraceEnabled()) {
            log.debug("Checking for permission: {}", requiredPermission);
        }
        if (hasPermission(authentication, requiredPermission)) {
            log.debug("User {} does not have permission {}", authentication.getPrincipal(), requiredPermission);
            return true;
        }
        return false;
    }

    public boolean hasPermission(Authentication authentication, String requiredPermission) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String permission = authority.getAuthority();
            if (permission.equals(requiredPermission)||requiredPermission.equals("ROLE_ANONYMOUS")) {
                return true;
            }
        }
        return false;
    }
    public boolean supports(ConfigAttribute attribute) {
        // TODO Auto-generated method stub
        return false;
    }
    
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        if(clazz.equals(FilterInvocation.class)){
            return true;
        }
        
        return false;
    }
    
    

}
