package ro.teamnet.zth.web;

import ro.teamnet.zth.file.LogFileWriter;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * Created by Aimandis on 7/13/2016.
 */
public class HeadersLogFilter implements javax.servlet.Filter {
    LogFileWriter logFileWriter = new LogFileWriter();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LogFileWriter.logHeader("user",servletRequest.getParameter("user"));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
