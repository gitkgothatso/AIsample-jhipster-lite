package com.mycompany.myapp.sample.application;

import org.springframework.stereotype.Component;
import com.mycompany.myapp.sample.domain.BeerId;
import com.mycompany.myapp.shared.kipe.application.AccessChecker;
import com.mycompany.myapp.shared.kipe.application.AccessContext;
import com.mycompany.myapp.shared.kipe.application.AISampleAppAuthorizations;

@Component
class BeerIdAccessChecker implements AccessChecker<BeerId> {

  private final AISampleAppAuthorizations authorizations;

  public BeerIdAccessChecker(AISampleAppAuthorizations authorizations) {
    this.authorizations = authorizations;
  }

  @Override
  public boolean can(AccessContext<BeerId> access) {
    return authorizations.allAuthorized(access.authentication(), access.action(), BeerResource.BEERS);
  }
}
