package ${package}.core.model;

import org.springframework.security.access.ConfigAttribute;
/**
 * TODO MyFilterSecurityMetadataSource를 위한 attribute 클래스
 *
 * @author mike Ryu, BD Apis
 * @date 2015. 3. 09
 * @version 1.0
 */
public class MyConfigAttribute implements ConfigAttribute{
    /**
     * 
     */
    private static final long serialVersionUID = 7956692005147783683L;
    String attribute;
    public String getAttribute() {
        // TODO Auto-generated method stub
        return this.attribute;
    }
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    
    

}
