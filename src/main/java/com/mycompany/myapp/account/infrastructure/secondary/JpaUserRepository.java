package com.mycompany.myapp.account.infrastructure.secondary;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findOneByEmailIgnoreCase(String email);
}
