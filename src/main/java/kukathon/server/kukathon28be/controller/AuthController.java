package kukathon.server.kukathon28be.controller;

import kukathon.server.kukathon28be.config.security.JwtTokenProvider;
import kukathon.server.kukathon28be.dto.response.TokenResponseDto;
import kukathon.server.kukathon28be.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/user/auth")
public class AuthController {
    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(AuthService authService, JwtTokenProvider jwtTokenProvider){
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @PostMapping(value = "/kakao-login")
    public TokenResponseDto kakaoLogin(HttpServletRequest request)  {

        String accessToken = jwtTokenProvider.resolveAccessToken(request);

        TokenResponseDto reissueTokenResponseDto = authService.kakaoLogin(accessToken);

        LOGGER.info("카카오 로그인 완료");

        return reissueTokenResponseDto;
    }







}