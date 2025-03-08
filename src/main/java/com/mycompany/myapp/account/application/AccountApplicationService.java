package com.mycompany.myapp.account.application;

import com.mycompany.myapp.account.domain.*;
import com.mycompany.myapp.account.infrastructure.secondary.UserEntity;
import com.mycompany.myapp.shared.email.MailService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountApplicationService {

  private final TokensRepository tokens;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final MailService mailService;

  public Token createToken(AuthenticationQuery query) {
    return tokens.buildToken(query);
  }

  public void registerUser(RegisterDTO dto) {
    if (dto != null) {
      dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    }
    userRepository.register(dto);

    Optional<UserEntity> user = userRepository.findUserByEmail(dto.getEmail());

    // Optional if you implement email
    user.ifPresent(mailService::sendActivationEmail);
  }

  public boolean activateAccount(String key) {
    return userRepository
      .findOneByActivatedKey(key)
      .map(user -> {
        user.setActivated(true);
        user.setActivatedKey(null);
        userRepository.save(user);
        return true;
      })
      .orElse(false);
  }
}
