package learn.ee.pj4wfilterorder;

import javax.servlet.*;
import java.io.IOException;

public class FilterC implements Filter {
    @Override
    public void init(FilterConfig filterConfig) { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Entering FilterC.doFilter()");
        chain.doFilter(request, response);
        System.out.println("Leaving FilterC.doFilter()");
    }

    @Override
    public void destroy() { }
}
