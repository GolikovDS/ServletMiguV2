package ru.artsok.controllers.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
//    public static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
//        LOGGER.debug("Is doFilter");
        Cookie[] cookie = request.getCookies();
        request.setAttribute("isLogin", "false");

        if (cookie != null) {
            String idSession = request.getSession().getId();
            String idSessionInCookie = "";
            String nameInCookie = "";
//            LOGGER.debug("Cookie exist");
            for (Cookie cookie1 : cookie) {
                if (cookie1.getName().equals("DBJSESSIONID")) {
                    idSessionInCookie = cookie1.getValue();
//                    LOGGER.debug("Cookie session-" + idSessionInCookie);
                }
                if (cookie1.getName().equals("userName")) {
                    nameInCookie = URLDecoder.decode(cookie1.getValue(), "UTF-8");
//                    LOGGER.debug("Cookie user name-" + nameInCookie);
                }
            }

            if (!nameInCookie.equals(""))
                if (idSessionInCookie.equals(idSession)) {
//                    LOGGER.debug("Id in cookie -  " + idSessionInCookie);
                    request.setAttribute("userName", nameInCookie);
                    request.setAttribute("isLogin", "true");
                }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
