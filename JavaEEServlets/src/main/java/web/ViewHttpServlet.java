package web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class ViewHttpServlet extends HttpServlet {
    private static final String CONTENT_TYPE_HTML = "text/html";

    protected void setHtmlContentType(HttpServletResponse resp){
        resp.setContentType(CONTENT_TYPE_HTML);
    }
}
