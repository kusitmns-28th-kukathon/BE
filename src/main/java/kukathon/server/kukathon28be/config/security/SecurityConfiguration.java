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
                        "/api/home/new-child",
                        "/api/child-medicine/enroll-medicine",
                        "/api/adhd/test/result",
                        "/api/adhd/test",
                        "/api/child-medicine/medicine-manage",
                        "/api/alarm/add-page",
                        "/api/alarm/add",
                        "/api/child-medicine/dose-record",
                        "/api/users/details",
                        "/api/users/{child-id}/details",
                        "/api/alarm/dose/",
                        "/api/users/alarms",
                        "/api/users/family"
                ).authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity
                .ignoring()
                .antMatchers(
                        "/api/user/auth/kakao-login",
                        "/api/user/auth/logout"
                );
    }
}