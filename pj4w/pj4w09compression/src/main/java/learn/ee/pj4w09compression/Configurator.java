package learn.ee.pj4w09compression;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Configurator implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        FilterRegistration.Dynamic reg = context.addFilter("requestLogFilter", new RequestLogFilter());
        reg.addMappingForUrlPatterns(null, false, "/*");
        reg = context.addFilter("compressionFilter", new CompressionFilter());
        reg.setAsyncSupported(true);
        reg.addMappingForUrlPatterns(null, false, "/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) { }
}
