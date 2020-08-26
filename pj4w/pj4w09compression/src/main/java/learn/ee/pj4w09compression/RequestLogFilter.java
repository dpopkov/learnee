package learn.ee.pj4w09compression;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

public class RequestLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Instant time = Instant.now();
        StopWatch timer = new StopWatch();
        try {
            timer.start();
            chain.doFilter(request, response);
        } finally {
            timer.stop();
            HttpServletRequest in = (HttpServletRequest) request;
            HttpServletResponse out = (HttpServletResponse) response;
            String length = out.getHeader("Content-Length");
            if (length == null || length.isEmpty()) {
                length = "-";
            }
            System.out.println(in.getRemoteAddr() + " - - [" + time + "] \"" + in.getMethod() + " " + in.getRequestURI()
                    + " " + in.getProtocol() + "\" " + out.getStatus() + " " + length + " " + timer);
        }
    }

    @Override
    public void destroy() {
    }

    private static class StopWatch {
        private long started;
        private long elapsed;

        public void start() {
            started = System.currentTimeMillis();
        }

        public void stop() {
            elapsed = System.currentTimeMillis() - started;
        }

        @Override
        public String toString() {
            return elapsed + "ms";
        }
    }
}
