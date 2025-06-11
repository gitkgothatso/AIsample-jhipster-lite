package com.mycompany.myapp.account.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDTO {

  private String login;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
}
