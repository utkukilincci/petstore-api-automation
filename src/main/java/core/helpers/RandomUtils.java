package core.helpers;

import java.util.Random;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class RandomUtils {
    private static final Random random = new Random();

    public static Long randomId() {
        return Long.valueOf(randomInt());
    }

    public static String randomName() {
        return randomAlphabetic(5);
    }

    private static String randomInt() {
        return String.valueOf(random.nextInt(Integer.MAX_VALUE - 1) + 1);
    }
}
