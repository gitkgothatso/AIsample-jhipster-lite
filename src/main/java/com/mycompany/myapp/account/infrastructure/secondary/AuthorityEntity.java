package com.mycompany.myapp.account.infrastructure.secondary;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AuthorityEntity {

  @Id
  @GeneratedValue
  private long authorityId;

  private String name;
}
