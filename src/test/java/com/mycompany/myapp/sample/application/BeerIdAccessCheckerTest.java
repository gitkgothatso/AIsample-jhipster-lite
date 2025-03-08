package com.mycompany.myapp.sample.application;

import static com.mycompany.myapp.sample.domain.BeersIdentityFixture.*;
import static com.mycompany.myapp.shared.kipe.application.TestAuthentications.*;
import static org.assertj.core.api.Assertions.*;

import com.mycompany.myapp.UnitTest;
import com.mycompany.myapp.shared.kipe.application.AISampleAppAuthorizations;
import com.mycompany.myapp.shared.kipe.application.AccessContext;
import java.util.List;
import org.junit.jupiter.api.Test;

@UnitTest
class BeerIdAccessCheckerTest {

  private static final BeerIdAccessChecker checker = new BeerIdAccessChecker(
    new AISampleAppAuthorizations(List.of(new BeersAccessesConfiguration().beersAccesses()))
  );

  @Test
  void shouldNotAuthorizedUnauthorizedAction() {
    assertThat(checker.can(AccessContext.of(admin(), "unauthorized", cloackOfFeathersId()))).isFalse();
  }

  @Test
  void shouldAuthorizedAuthorizedAction() {
    assertThat(checker.can(AccessContext.of(admin(), "create", cloackOfFeathersId()))).isTrue();
  }
}
