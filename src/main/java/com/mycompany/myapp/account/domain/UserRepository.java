package com.mycompany.myapp.account.domain;

import com.mycompany.myapp.account.infrastructure.secondary.UserEntity;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository {
  void register(RegisterDTO dto);

  // TO CHANGE from UserEntity to User
  UserEntity findUserByEmail(String username);
}
