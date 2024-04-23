package com.shopify.api.utils;

import java.util.Random;

public class IdUtils {

    public static String generateRandomId(Long id) {
        int leftLimit = 48; // '0' character
        int rightLimit = 57; // '9' character

        int idLength = String.valueOf(id).length();
        int targetStringLength = 20 - idLength;

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomInt = leftLimit + (int) (random.nextDouble() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomInt);
        }
        return id + buffer.toString();
    }
}
