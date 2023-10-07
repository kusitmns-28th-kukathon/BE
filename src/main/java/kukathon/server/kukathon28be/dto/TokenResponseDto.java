package kukathon.server.kukathon28be.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class TokenResponseDto {

    private int code;

    private String message;

    private String accessToken;

    private String refreshToken;
}