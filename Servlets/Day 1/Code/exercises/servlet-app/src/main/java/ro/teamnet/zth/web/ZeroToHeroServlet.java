package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Aimandis on 7/12/2016.
 */
public class ZeroToHeroServlet extends HttpServlet {
    private String handleRequest(HttpServletRequest req){
        String response = "Hello <b>"+req.getParameter("first")+" "+req.getParameter("last")+"</b>! Enjoy Zero To Hero!!!";

        return response;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        resp.setContentType("text/html");
        resp.getWriter().write(handleRequest(req));
    }
}
