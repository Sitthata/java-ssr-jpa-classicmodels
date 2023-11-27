package sit.int202.classicmodels.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "LoggingFilter", servletNames = {"ProductListServlet", "AddToCartServlet"})
public class LoggingFilter implements Filter {

    private FilterConfig config;

    public void init(FilterConfig config) {
        this.config = config;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long before = System.currentTimeMillis();
        //before invoke resources
        chain.doFilter(request, response);
        //after invoke resources
        long duration = System.currentTimeMillis() - before;
        String msg = "Resource: " + ((HttpServletRequest) request).getRequestURI() +
                ", execution time: "+ duration+ " milliSeconds";
        config.getServletContext().log(msg);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
