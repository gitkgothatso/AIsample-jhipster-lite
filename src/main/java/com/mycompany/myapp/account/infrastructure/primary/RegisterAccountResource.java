package com.mycompany.myapp.account.infrastructure.primary;

import com.mycompany.myapp.account.application.AccountApplicationService;
import com.mycompany.myapp.account.domain.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisterAccountResource {

  private final AccountApplicationService service;

  public RegisterAccountResource(AccountApplicationService service) {
    this.service = service;
  }

  /**
   * {@code POST  /account/register} : register new user.
   *
   * @return the current user.
   * @throws AccountResourceException
   *           {@code 500 (Internal Server Error)} if the user couldn't be returned.
   */
  @PostMapping("/account/register")
  public ResponseEntity<Void> registerAccount(@RequestBody @Valid RestRegister register) {
    this.service.registerUser(
        RegisterDTO.builder()
          .login(register.login())
          .email(register.email())
          .firstName(register.firstName())
          .lastName(register.lastName())
          .password(register.password())
          .build()
      );

    return ResponseEntity.ok().build();
  }
}
