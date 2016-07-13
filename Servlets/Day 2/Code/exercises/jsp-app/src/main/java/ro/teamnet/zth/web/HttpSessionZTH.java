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
public class HttpSessionZTH extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        resp.setContentType("text/html");
        String password = req.getParameter("password");
        String user = req.getParameter("username");
        if(password.equals("admin") && user.equals("admin"))
            resp.getWriter().write("Welcome back <br> Username: " + user + "<br>" + req.getSession().getId());
        else{
            RequestDispatcher requestDispatcher =
                    req.getRequestDispatcher("/views/loginFail.jsp");
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("session", req.getSession());
            requestDispatcher.forward(req,resp);
        }
    }
}
