package com.mycompany.myapp.account.infrastructure.primary;

import com.mycompany.myapp.account.application.AccountApplicationService;
import com.mycompany.myapp.shared.authentication.application.AuthenticatedUser;
import com.mycompany.myapp.shared.authentication.domain.Role;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class AccountResource {

  private final AccountApplicationService applicationService;

  /**
   * {@code GET  /account} : get the current user.
   *
   * @return the current user.
   * @throws AccountResourceException
   *           {@code 500 (Internal Server Error)} if the user couldn't be returned.
   */
  @GetMapping("/account")
  public RestAccount getAccount() {
    return new RestAccount(AuthenticatedUser.username().get(), roles());
  }

  private Set<String> roles() {
    return AuthenticatedUser.roles().stream().map(Role::key).collect(Collectors.toUnmodifiableSet());
  }

  @GetMapping("/activate")
  public ResponseEntity<Void> activateAccount(@RequestParam(value = "key") String key) {
    boolean activated = applicationService.activateAccount(key);
    return activated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
  }
}
