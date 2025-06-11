package com.mycompany.myapp.account.infrastructure.secondary;

import com.mycompany.myapp.account.domain.RegisterDTO;
import com.mycompany.myapp.account.domain.User;
import com.mycompany.myapp.account.domain.UserRepository;
import com.mycompany.myapp.shared.authentication.domain.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SringDataUserRepository implements UserRepository {

  private final JpaUserRepository userRepository;

  @Override
  public void register(RegisterDTO dto) {
    if (dto != null) {
      log.info("Registering user {} ..", dto.getFirstName());

      UserEntity newUser = UserEntity.builder()
        .email(dto.getEmail())
        .password(dto.getPassword()) // hashed at service layer
        .role(Role.ADMIN)
        .firstName(dto.getFirstName())
        .lastName(dto.getLastName())
        .build();

      this.userRepository.save(newUser);

      log.info("Sending activation key to user with email {} ..", dto.getEmail());
    }
  }

  @Override
  public UserEntity findUserByEmail(String username) {
    return this.userRepository.findOneByEmailIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
