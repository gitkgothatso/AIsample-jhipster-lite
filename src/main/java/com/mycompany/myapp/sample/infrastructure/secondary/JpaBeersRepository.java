package com.mycompany.myapp.sample.infrastructure.secondary;

import com.mycompany.myapp.sample.domain.beer.BeerSellingState;
import java.util.Collection;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface JpaBeersRepository extends JpaRepository<BeerEntity, UUID> {
  Collection<BeerEntity> findBySellingState(BeerSellingState sellingState);
}
