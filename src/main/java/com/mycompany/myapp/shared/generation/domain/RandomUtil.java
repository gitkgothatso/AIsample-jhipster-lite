package com.mycompany.myapp.shared.generation.domain;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class RandomUtil {

  private static final SecureRandom RANDOM = new SecureRandom();
  private static final int DEF_COUNT = 20;

  private RandomUtil() {}

  public static String generateActivationKey() {
    return generateRandomAlphanumericString(DEF_COUNT);
  }

  private static String generateRandomAlphanumericString(int length) {
    return new BigInteger(130, RANDOM).toString(32).substring(0, length);
  }
}
