package com.mtuci.lazarev.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final String username;
    private final String password;
    private List<GrantedAuthority> authorities;
    private final  boolean isActive;

    @Override
    public boolean isAccountNonExpired() { return isActive; }

    @Override
    public boolean isAccountNonLocked() { return isActive; }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() { return isActive; }

     public static UserDetails fromApplicationUser(ApplicationUser user) {
        return new User(
                user.getLogin(),
                user.getPassword_hash(),
                user.getApplicationRole().getGrantedAuthorities()
        );
     }
}
