package com.mycompany.myapp.account.domain;

import com.mycompany.myapp.account.infrastructure.secondary.UserEntity;
import java.util.Optional;

public interface UserRepository {
  void register(RegisterDTO dto);

  // TO CHANGE from UserEntity to User
  Optional<UserEntity> findUserByEmail(String username);

  Optional<UserEntity> findOneByActivatedKey(String key);

  void save(UserEntity user);
}
