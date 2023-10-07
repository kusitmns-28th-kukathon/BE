package kukathon.server.kukathon28be.config.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUser implements UserDetails {


    Long userId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Override
    public boolean isEnabled() {
        return true;
    }
}