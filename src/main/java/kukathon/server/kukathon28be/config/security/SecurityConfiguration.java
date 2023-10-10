package kukathon.server.kukathon28be.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    public final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/api/user/diary/add-diary",
                        "/api/user/diary/diary-record/{date}",
                        "/friends/request",
                        "/friend/received-request",
                        "/friend/sent-request",
                        "/api/user/diary/main",
                        "/send-alarm",
                        "/api/user/diary"
                ).authenticated()
//                .antMatchers(
//
//                        "/friends",
//                        "/api/user/auth/kakao-login",
//                        "/api/user/diary/friend-main"
//                ).permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity
                .ignoring()
                .antMatchers(
                        "/",
                        "/friends",
                        "/api/user/auth/kakao-login",
                        "/api/user/diary/friend-main"
                );
    }
}