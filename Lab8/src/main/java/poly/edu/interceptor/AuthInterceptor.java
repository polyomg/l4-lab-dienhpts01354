package poly.edu.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.edu.entity.Account;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    
    @Autowired
    HttpSession session; 
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        Account user = (Account) session.getAttribute("user");
        
        if (user == null) { // Chưa đăng nhập
            session.setAttribute("securityUri", uri); 
            response.sendRedirect("/auth/login"); 
            return false;
        } else if (!user.isAdmin() && uri.startsWith("/admin/")) { // Không phải admin mà truy cập /admin/
            session.setAttribute("securityUri", uri); 
            response.sendRedirect("/auth/login");
            return false;
        }
        return true; 
    }
}