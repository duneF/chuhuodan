package filter;

import bean.UserDengLu;
import controller.UserDengLuController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class loginFilter implements Filter {
//    private FilterConfig filterConfig;
    private HttpSession session;
    private String request_uri;
    private String request_path;
    private String url;
    HttpServletRequest req;
    HttpServletResponse resp;

    public void destroy() {
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setContentType ( "text/html;charset=utf-8" );
        req = (HttpServletRequest) request;
        resp = (HttpServletResponse) response;
        session = req.getSession ();
        UserDengLu user = (UserDengLu) session.getAttribute ( "USER" );
        if (user != null) {
            chain.doFilter ( req, resp );
        } else {
            request_uri = req.getRequestURI ();
            request_path = req.getContextPath ();
            url = request_uri.substring ( request_path.length () );
            System.out.println ( "url" + url );
            if (url.contains ( "login.jsp" ) || request_uri.startsWith ( req.getContextPath () + "/loginOut.do" ) || request_uri.startsWith ( req.getContextPath () + "/login.do" ) || request_uri.startsWith ( req.getContextPath () + "/toLogin.do" )) {
                chain.doFilter ( req, resp );
            } else {
                resp.sendRedirect ( "login.jsp" );
            }
        }
    }
    public void init(FilterConfig config) throws ServletException {
    }

}
