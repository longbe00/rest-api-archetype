package ${package}.core.model;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

        public void setAuthentication(AbstractObject model);
        
        public Authentication getAuthentication();
}
