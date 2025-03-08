package com.mycompany.myapp.sample.application;

import com.mycompany.myapp.sample.domain.BeerId;
import com.mycompany.myapp.shared.kipe.application.AISampleAppAuthorizations;
import com.mycompany.myapp.shared.kipe.application.AccessChecker;
import com.mycompany.myapp.shared.kipe.application.AccessContext;
import org.springframework.stereotype.Component;

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
