package learn.ee.pj4w09filterasync;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

public class AnyRequestFilter implements Filter {
    private String name;

    @Override
    public void init(FilterConfig filterConfig) {
        name = filterConfig.getFilterName();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("Entering " + name + ".doFilter()");
        chain.doFilter(
                new HttpServletRequestWrapper((HttpServletRequest) req),
                new HttpServletResponseWrapper((HttpServletResponse) resp)
        );
        if (req.isAsyncSupported() && req.isAsyncStarted()) {
            AsyncContext context = req.getAsyncContext();
            System.out.println("Leaving " + name + ".doFilter(), async "
                    + "context holds wrapped request/response = " + !context.hasOriginalRequestAndResponse());
        } else {
            System.out.println("Leaving " + name + ".doFilter()");
        }
    }

    @Override
    public void destroy() { }
}
