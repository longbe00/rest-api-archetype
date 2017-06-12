/**
 * 
 */
package ${package}.core.web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author mike
 *
 */
public class DefaultSessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se) {

        se.getSession().setMaxInactiveInterval(1 * 60 * 60);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
    }

}
