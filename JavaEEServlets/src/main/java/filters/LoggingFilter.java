package filters;

import services.DatesService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"*","/courses/*"})
public class LoggingFilter implements Filter {
    private final DatesService datesService;

    @Inject
    public LoggingFilter(DatesService datesService) {
        this.datesService = datesService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String method = request.getMethod();
        String date = this.datesService.getNowFormatted();

        String result = String.format("[%s]: %s -> %s %s",date,ip,method,url);
        System.out.println(result);
        filterChain.doFilter(request,response);

    }
}
