package io.wisoft.accounttutorial.utils;

import java.util.Random;

public class RandomValueUtils {

  public static String getRandom() {
    Random random = new Random(System.currentTimeMillis());
    StringBuilder stringBuilder = new StringBuilder();
    while (stringBuilder.length() < 8) {
      stringBuilder.append(random.nextInt(10));
    }
    return stringBuilder.toString();
  }

}
