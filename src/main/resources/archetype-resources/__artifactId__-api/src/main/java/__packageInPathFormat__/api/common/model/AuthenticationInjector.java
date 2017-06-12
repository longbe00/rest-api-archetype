package ${package}.api.common.model;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ${package}.core.model.AbstractObject;
import ${package}.core.model.AuthenticationFacade;

/**
 * createId or modifyId 자동 셋팅 모듈
 * 
 * @author Mike Ryu
 *
 */
@Component
public class AuthenticationInjector implements AuthenticationFacade {
    @Autowired
    private HttpServletRequest request;

    @Override
    public void setAuthentication(AbstractObject model) {
        // TODO Auto-generated method stub
        if (request != null) {
            switch (request.getMethod()) {
            case "POST":
                model.setCreateId(getAuthentication().getName());
                model.setModifyId(getAuthentication().getName());
                break;
            case "PUT":
                model.setCreateId(getAuthentication().getName());
                model.setModifyId(getAuthentication().getName());
                break;
            case "DELETE":
                model.setModifyId(getAuthentication().getName());
                model.setModifyId(getAuthentication().getName());
                break;
            default:
                break;
            }
        }
    }

    @Override
    public Authentication getAuthentication() {
        // TODO Auto-generated method stub
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getName())) { // 테스트 코드용으로 인증이 안된 경우 TESTER로
                                                                                          // 강제로 셋팅해준다.
            authentication = new Authentication() {

                private static final long serialVersionUID = 8813227692358021354L;

                @Override
                public String getName() {
                    // TODO Auto-generated method stub
                    return "backoffice";
                }

                @Override
                public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
                    // TODO Auto-generated method stub

                }

                @Override
                public boolean isAuthenticated() {
                    // TODO Auto-generated method stub
                    return false;
                }

                @Override
                public Object getPrincipal() {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public Object getDetails() {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public Object getCredentials() {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    // TODO Auto-generated method stub
                    return null;
                }
            };
        }

        return authentication;
    }

}
