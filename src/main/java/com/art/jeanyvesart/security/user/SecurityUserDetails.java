package com.art.jeanyvesart.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
//@Component

public class SecurityUserDetails implements UserDetails {
  private final MyUser user;

  public SecurityUserDetails(MyUser user) {

    this.user = user;
  }
  public String getFullName() {
    return user.getFullName();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }
  @Override
  public String getPassword() {
    return user.getPassword();
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(user::getAuthority);
  }
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }




  // Omitted code

}