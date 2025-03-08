package com.mycompany.myapp.account.infrastructure.secondary;

import com.mycompany.myapp.account.domain.RegisterDTO;
import com.mycompany.myapp.account.domain.UserRepository;
import com.mycompany.myapp.shared.authentication.domain.Role;
import com.mycompany.myapp.shared.generation.domain.RandomUtil;
import java.util.Optional;
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
  public Optional<UserEntity> findOneByActivatedKey(String key) {
    return userRepository.findOneByActivatedKey(key);
  }

  @Override
  public void save(UserEntity user) {
    userRepository.save(user);
  }

  @Override
  public void register(RegisterDTO dto) {
    if (dto != null) {
      log.info("Registering user {} ..", dto.getFirstName());

      UserEntity newUser = UserEntity.builder()
        .email(dto.getEmail())
        .password(dto.getPassword()) // hashed at service layer
        .role(Role.USER)
        .firstName(dto.getFirstName())
        .lastName(dto.getLastName())
        .activated(false)
        .activatedKey(RandomUtil.generateActivationKey())
        .build();

      this.userRepository.save(newUser);

      log.info("Sending activation key to user with email {} ..", dto.getEmail());
    }
  }

  @Override
  public Optional<UserEntity> findUserByEmail(String username) {
    return Optional.ofNullable(
      this.userRepository.findOneByEmailIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException("User not found"))
    );
  }
}
