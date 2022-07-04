package com.step.hryshkin.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.step.hryshkin.dao.UserDAO;
import com.step.hryshkin.dao.impl.UserDAOImpl;
import com.step.hryshkin.model.User;
import com.step.hryshkin.service.impl.GoodServiceImpl;
import com.step.hryshkin.utils.UtilsForShop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = {"/Shop.jsp"})
public class FilterForShop implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(FilterForShop.class);
    private final GoodServiceImpl goodServiceImpl = new GoodServiceImpl();
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse
            , FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setAttribute("goods", goodServiceImpl.printGoods());
        checkUser((HttpServletRequest) servletRequest);
        checkFlag(servletResponse, request);
        try {
            filterChain.doFilter(request, servletResponse);
        } catch (IOException e) {
            LOGGER.error("IOException in doFilter " + e);
        } catch (ServletException e) {
            LOGGER.error("ServletException in doFilter " + e);
        }
    }

    private void checkFlag(ServletResponse servletResponse, HttpServletRequest request) {
        if (request.getSession().getAttribute("check") == null) {
            if (request.getParameter("check") == null) {
                UtilsForShop.setCheckStatus(request, request.getParameter("check"));
            } else {
                String path = "/ErrorPage.jsp";                             //TODO ErrorPage.jsp
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
                try {
                    requestDispatcher.forward(request, servletResponse);
                } catch (ServletException e) {
                    LOGGER.error("ServletException in checkFlag");
                } catch (IOException e) {
                    LOGGER.error("IOException in checkFlag");
                }
            }
        }
    }

    private void checkUser(HttpServletRequest request) {
        String name = request.getParameter("UserName");                  //TODO remake
        String password = "123";                                            //TODO remake
        User user = new User(name, password);
        if (name != null) {
            if (userDAO.getUserByName(name).isEmpty()) {
                userDAO.createNewUser(user);
            }
            Optional<User> newUser = userDAO.getUserByName(name);
            if (newUser.isPresent()) {
                if (request.getSession().getAttribute("user") == null) {
                    UtilsForShop.setUser(request, newUser.get());
                } else if (!UtilsForShop.isUserEquals(request)) {
                    request.getSession().invalidate();
                    UtilsForShop.setUser(request, newUser.get());
                }
            }
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
