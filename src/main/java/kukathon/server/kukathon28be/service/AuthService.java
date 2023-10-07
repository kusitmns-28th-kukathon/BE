package kukathon.server.kukathon28be.service;

import kukathon.server.kukathon28be.config.security.JwtTokenProvider;
import kukathon.server.kukathon28be.dto.response.KakaoUserResponse;
import kukathon.server.kukathon28be.dto.response.TokenResponseDto;
import kukathon.server.kukathon28be.entity.User;
import kukathon.server.kukathon28be.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;



@Service
public class AuthService{



    public JwtTokenProvider jwtTokenProvider;
    public RedisTemplate<String, String> redisTemplate;

    public UserRepository userRepository;

    private final WebClient webClient;


    private final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    public AuthService(WebClient webClient, UserRepository userRepository, JwtTokenProvider jwtTokenProvider, RedisTemplate<String, String> redisTemplate) {
        this.webClient = webClient;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.redisTemplate = redisTemplate;
    }





    public TokenResponseDto kakaoLogin(String accessToken) {

        User user;


        Mono<KakaoUserResponse> userInfoMono = getUserInfo(accessToken);
        KakaoUserResponse userInfo = userInfoMono.block();

//        LOGGER.info(String.valueOf(userInfo.getAccount_email()));
//        LOGGER.info(String.valueOf(userInfo.getProfile_nickname()));
//        LOGGER.info(String.valueOf(userInfo.getProfile_image()));

        LOGGER.info(String.valueOf(userInfo.getKakao_account().getEmail()));
        LOGGER.info(String.valueOf(userInfo.getKakao_account().getProfile().getProfileImageUrl()));
        LOGGER.info(String.valueOf(userInfo.getKakao_account().getProfile().getNickname()));

        Optional<User> userData = userRepository.findByNum(String.valueOf(userInfo.getId()));

        if(userData.isEmpty()){
            user = User.builder()
                    .num(String.valueOf(userInfo.getId()))
                    .userRole("USER")
                    .email(userInfo.getKakao_account().getEmail())
                    .nickname(userInfo.getKakao_account().getProfile().getNickname())
                    .userProfile(userInfo.getKakao_account().getProfile().getProfileImageUrl())
                    .build();

            userRepository.save(user);
        }

        Optional<User> userLoginData = userRepository.findByNum(String.valueOf(userInfo.getId()));


        String refreshToken = jwtTokenProvider.createRereshToken();

        TokenResponseDto tokenResponseDto = TokenResponseDto.builder()
                .message("OK")
                .code(200)
                .accessToken(jwtTokenProvider.createAccessToken(
                        userLoginData.get().getId(),
                        String.valueOf(userLoginData.get().getUserRole())))
                .refreshToken(refreshToken)
                .build();

        redisTemplate.opsForHash().put(jwtTokenProvider.createRereshToken(),"userId", String.valueOf(userLoginData.get().getId()));
        redisTemplate.opsForHash().put(jwtTokenProvider.createRereshToken(),"role", String.valueOf(userLoginData.get().getUserRole()));


        return tokenResponseDto;
    }




    public Mono<KakaoUserResponse> getUserInfo(String accessToken) {
        return webClient
                .get()
                .uri("https://kapi.kakao.com/v2/user/me") // 카카오 사용자 정보 엔드포인트
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(KakaoUserResponse.class);
    }


}
