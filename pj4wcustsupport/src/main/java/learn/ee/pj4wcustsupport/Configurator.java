package learn.ee.pj4wcustsupport;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.EnumSet;

@WebListener
public class Configurator implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        FilterRegistration.Dynamic registration;

        registration = context.addFilter("loggingFilter", new LoggingFilter());
        registration.addMappingForUrlPatterns(EnumSet.of(
                DispatcherType.REQUEST, DispatcherType.INCLUDE, DispatcherType.FORWARD, DispatcherType.ERROR),
                false, "/*");

        registration = context.addFilter(
                "authenticationFilter", new AuthenticationFilter());
        registration.setAsyncSupported(true);
        registration.addMappingForUrlPatterns(null, false,
                "/sessions", "/tickets", "/chat");
    }
}
