package zerocoder.com.Estate.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zerocoder.com.Estate.dto.request.SignInRequest;
import zerocoder.com.Estate.dto.response.TokenResponse;
import zerocoder.com.Estate.enums.TokenType;
import zerocoder.com.Estate.model.Account;
import zerocoder.com.Estate.repository.AccountRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public TokenResponse authenticate(SignInRequest signInRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

        var account = accountRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name or password is incorrect"));

        String acessToken = jwtService.generateToken(account);
        String refreshToken = jwtService.generateRefreshToken(account);
        return TokenResponse.builder()
                .accessToken(acessToken)
                .refreshToken(refreshToken)
                .accountId(account.getId())
                .build();
    }

    public TokenResponse refresh(HttpServletRequest request) {
        String refreshToken = request.getHeader("x-token");
        if(StringUtils.isBlank(refreshToken)) {
            throw new RuntimeException("Token is empty");
        }
        final String username = jwtService.extractUsername(refreshToken, TokenType.REFRESH);
        log.info("Username: {}", username);

        Optional<Account> account = accountRepository.findByUsername(username);
        System.out.println("UserId: " + account.get().getId());

        if(!jwtService.isValid(refreshToken, TokenType.REFRESH, account.get())) {
            throw new RuntimeException("Token is invalid");
        }
        String accessToken = jwtService.generateToken(account.get());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accountId(account.get().getId())
                .build();
    }
}
