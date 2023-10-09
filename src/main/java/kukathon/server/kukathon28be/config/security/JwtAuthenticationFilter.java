package kukathon.server.kukathon28be.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtTokenProvider jwtTokenProvider;



    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse,
                                    FilterChain filterChain) {
        try {
            String token = jwtTokenProvider.resolveToken(servletRequest);
            if (token != null) {

                jwtTokenProvider.validateToken(token);

                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (IllegalArgumentException e) {
            // 예외 처리: Invalid access token header
            setErrorResponse(servletRequest, servletResponse, "유효하지 않은 토큰 헤더", 400);
            return;
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            setErrorResponse(servletRequest, servletResponse, "검증 에러 ",421);
            return;
        } catch (ExpiredJwtException e) {
            setErrorResponse(servletRequest, servletResponse, "토큰 만료",419);
            return;
        } catch (JwtException e) {
            setErrorResponse(servletRequest, servletResponse, "jwt 에러",420);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    public void setErrorResponse(HttpServletRequest request, HttpServletResponse response, String message,
                                 int code) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final Map<String, Object> body = new HashMap<>();

        body.put("message", message);
        body.put("code", code);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
        response.setStatus(HttpServletResponse.SC_OK);


    }


}
