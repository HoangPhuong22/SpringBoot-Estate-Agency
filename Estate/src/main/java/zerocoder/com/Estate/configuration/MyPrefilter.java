package zerocoder.com.Estate.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import zerocoder.com.Estate.enums.TokenType;
import zerocoder.com.Estate.service.AccountService;
import zerocoder.com.Estate.service.JwtService;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyPrefilter extends OncePerRequestFilter {

    private final AccountService accountService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("------------------------------Prefilter---------------------------------");

        final String authorization = request.getHeader("Authorization");
        log.info("Authorization: {}", authorization);

        if(StringUtils.isBlank(authorization) || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;

        }

        final String token = authorization.substring(7);
        log.info("Token: {}", token);

        final String userName = jwtService.extractUsername(token, TokenType.ACCESS);
        log.info("Username: {}", userName);

        if(StringUtils.isNotEmpty(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = accountService.userDetailsService().loadUserByUsername(userName);
            if(jwtService.isValid(token, TokenType.ACCESS, userDetails)) {
                SecurityContext context = SecurityContextHolder.getContext();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }
}
