package com.mycompany.myapp.sample.infrastructure.secondary;

import static com.mycompany.myapp.sample.domain.beer.BeersFixture.*;
import static org.assertj.core.api.Assertions.*;

import com.mycompany.myapp.UnitTest;
import org.junit.jupiter.api.Test;

@UnitTest
class BeerEntityTest {

  @Test
  void shouldConvertFromAndToDomain() {
    assertThat(BeerEntity.from(beer()).toDomain()).usingRecursiveComparison().isEqualTo(beer());
  }
}
