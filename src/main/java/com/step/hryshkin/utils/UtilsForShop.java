package com.step.hryshkin.utils;

import com.step.hryshkin.model.User;

import javax.servlet.http.HttpServletRequest;

public class UtilsForShop {

    private UtilsForShop() {
    }

    public static void setUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute("user", user);
    }

    public static void setCheckStatus(HttpServletRequest request, String check) {
        request.getSession().setAttribute("check", check);
    }

    public static boolean isUserEquals(HttpServletRequest request) {
        return ((User) request.getSession().getAttribute("user"))
                .getName().equals(request.getParameter("UserName"));
    }
}
