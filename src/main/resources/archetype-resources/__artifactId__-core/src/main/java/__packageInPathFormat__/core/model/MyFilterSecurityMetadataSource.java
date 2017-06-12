package ${package}.core.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
/**
 * TODO Security Filter를 위한 MetadataSource를 통한 url권한 attribute 처리 클래스
 *
 * @author mike Ryu, BD Apis
 * @date 2015. 3. 09
 * @version 1.0
 */
public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    //private final Logger log = LoggerFactory.getLogger(this.getClass());
//    private MenuService menuService;
    
//    public MyFilterSecurityMetadataSource(MenuService menuService) {
//        super();
//        this.menuService = menuService;
//    }

    public List<ConfigAttribute> getAttributes(Object object) {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        String method = fi.getHttpRequest().getMethod();
        List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();

        attributes = getAttributesByURL(url, method);

        return attributes;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public List<ConfigAttribute> getAttributesByURL(String inputUrl, String method)
    {
        List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
        try{

//            if(inputUrl.equals("")){
//                
//            }else{
//                String apiUrl = "";
//                List<String> patterns = StringUtil.getTokens(inputUrl, "/");
//                if(patterns.size()==2){
//                    apiUrl = "/" + patterns.get(0) + "/" + patterns.get(1).split("\\?")[0];
//                }else if(patterns.size()>=3){
//                    if(!patterns.get(0).equals("eatsight")) //eatsight api가 아닌 경우 2번째 path까지 확인 
//                        apiUrl = "/" + patterns.get(0) + "/" + patterns.get(1).split("\\?")[0];
//                    else//eatsight api가 맞는 경우 3번째 path까지 확인 
//                        apiUrl = "/" + patterns.get(0) + "/" + patterns.get(1) + "/" + patterns.get(2).split("\\?")[0];
//                }else{
//                    if(patterns.get(0).equals("login")||patterns.get(0).equals("error-login")||patterns.get(0).equals("j_spring_security_check")){
//                        MyConfigAttribute temp = new MyConfigAttribute();
//                        temp.setAttribute("ROLE_ANONYMOUS");
//                        attributes.add(temp);
//                    }
//                }
//                
//                MenuAuthority menuAuthority = new MenuAuthority();
//                menuAuthority.setApiUrl(apiUrl);
//                List<MenuAuthority> accessRoleList = menuService.getAccessRole(menuAuthority);
//                for(MenuAuthority authority:accessRoleList){
//                    MyConfigAttribute temp = new MyConfigAttribute();
//                    if(authority.getMenuAuthorityCode().equals("CRUD")||method.equals(HttpMethod.GET.name())){ //모든 권한이 있거나 요청 자체가 Read인 경우 권한 처리 진행
//                        temp.setAttribute(authority.getRoleName());
//                        attributes.add(temp);
//                    }else{
//                        temp.setAttribute("ROLE_ACCESS_DENY");
//                        attributes.add(temp);
//                    }
//                }
//                if(accessRoleList.size()==0){
//                    MyConfigAttribute temp = new MyConfigAttribute();
//                    temp.setAttribute("ROLE_ANONYMOUS");
//                    attributes.add(temp);
//                }
//            }
            MyConfigAttribute temp = new MyConfigAttribute();
            temp.setAttribute("ROLE_ANONYMOUS");
            attributes.add(temp);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return attributes;
    }
}