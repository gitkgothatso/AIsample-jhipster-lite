package com.mycompany.myapp.sample.application;

import org.springframework.stereotype.Component;
import com.mycompany.myapp.sample.domain.beer.BeerToCreate;
import com.mycompany.myapp.shared.kipe.application.AccessChecker;
import com.mycompany.myapp.shared.kipe.application.AccessContext;
import com.mycompany.myapp.shared.kipe.application.AISampleAppAuthorizations;

@Component
class BeerToCreateAccessChecker implements AccessChecker<BeerToCreate> {

  private final AISampleAppAuthorizations authorizations;

  public BeerToCreateAccessChecker(AISampleAppAuthorizations authorizations) {
    this.authorizations = authorizations;
  }

  @Override
  public boolean can(AccessContext<BeerToCreate> access) {
    return authorizations.allAuthorized(access.authentication(), access.action(), BeerResource.BEERS);
  }
}
