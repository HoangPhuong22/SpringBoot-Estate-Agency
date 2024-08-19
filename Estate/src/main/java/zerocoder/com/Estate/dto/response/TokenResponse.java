package zerocoder.com.Estate.dto.response;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse implements Serializable {
    private String accessToken;
    private String refreshToken;
    private Long accountId;
}
