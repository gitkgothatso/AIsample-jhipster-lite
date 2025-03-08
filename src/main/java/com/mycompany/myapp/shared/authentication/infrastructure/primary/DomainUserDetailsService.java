package com.mycompany.myapp.shared.authentication.infrastructure.primary;

import com.mycompany.myapp.account.domain.UserRepository;
import com.mycompany.myapp.account.infrastructure.secondary.UserEntity;
import com.mycompany.myapp.shared.email.MailService;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public DomainUserDetailsService(UserRepository userRepository, MailService mailService) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
      .findUserByEmail(username)
      .filter(checkIfUserActivated())
      .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found"));
  }

  private Predicate<? super UserEntity> checkIfUserActivated() {
    return userEntity -> {
      if (userEntity.isActivated()) {
        return true;
      } else {
        log.info("Account not activated....");
        return false;
      }
    };
  }
}
