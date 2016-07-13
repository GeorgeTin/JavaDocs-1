package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Aimandis on 7/13/2016.
 */
public class HttpServletInclude extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        resp.setContentType("text/html");
        RequestDispatcher requestDispatcher =
                req.getRequestDispatcher("/helloIncluded");
        requestDispatcher.include(req,resp);
        resp.getWriter().write("Am fost in include");
    }
}
