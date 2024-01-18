package lk.ijse.shoe_store.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/*")
public class CORSFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("CORS Filter");
        String origin = req.getHeader("Origin");
        System.out.println(origin);
        System.out.println(getServletContext().getInitParameter("origin"));
        System.out.println(origin.contains(getServletContext().getInitParameter("origin")));
        if (origin.contains(getServletContext().getInitParameter("origin"))) {
            System.out.println("in");
            res.setHeader("Access-Control-Allow-Origin", origin);
            res.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,HEADER");
            res.setHeader("Access-Control-Allow-Headers", "Content-Type");
            res.setHeader("Access-Control-Expose-Headers", "Content-Type");
            System.out.println("finished Filtering");
        }
        chain.doFilter(req,res);
        System.out.println("Finshed Filtering");
    }
}
