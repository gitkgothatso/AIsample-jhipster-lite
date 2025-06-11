package com.mycompany.myapp.account.application;

import com.mycompany.myapp.account.domain.*;
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

  public Token createToken(AuthenticationQuery query) {
    return tokens.buildToken(query);
  }

  public void registerUser(RegisterDTO dto) {
    if (dto != null) {
      dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    }
    userRepository.register(dto);
  }
}
