package com.mycompany.myapp.sample.domain.beer;

import com.mycompany.myapp.sample.domain.BeerId;
import java.util.Optional;

public interface BeersRepository {
  void save(Beer beer);

  Beers catalog();

  Optional<Beer> get(BeerId beer);
}
