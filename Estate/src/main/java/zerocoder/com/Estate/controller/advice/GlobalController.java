package zerocoder.com.Estate.controller.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import zerocoder.com.Estate.model.Account;
import zerocoder.com.Estate.utils.SecurityUtils;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute("servletPath")
    String getRequestServletPath(HttpServletRequest request) {
        return request.getServletPath();
    }

    @ModelAttribute("user")
    Account getUser(SecurityUtils securityUtils) {
        return securityUtils.getPrincipal();
    }
}