package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by Aimandis on 7/12/2016.
 */
public class InfoHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        resp.setContentType("text/html");
        Enumeration headers =  req.getHeaderNames();
        String response = "<table>";
        while(headers.hasMoreElements()){
            response+="<tr><td>";
            String elem = (String) headers.nextElement();
            response+=elem;
            response+="</td><td>" + req.getHeader(elem) + "</td>";
            response+="</tr>";
        }
        response+="</table>";
        resp.getWriter().write(response);

    }
}
