package model.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class sessionUtils {

    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession(true);
    }

    public static boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null && session.getAttribute("username") != null);
    }

    public static String getUsername(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null) ? (String) session.getAttribute("username") : null;
    }

    public static String getUserRole(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null) ? (String) session.getAttribute("role") : null;
    }

    public static void setSessionAttribute(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession(true);
        session.setAttribute(key, value);
    }

    public static void removeSessionAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
