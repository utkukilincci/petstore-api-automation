package helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LogUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogUtils.class);
    private static final StringBuilder sb = new StringBuilder();

    private LogUtils() {
    }

    public static void logInfo(final String message) {
        sb.append(message);
        LOGGER.info(message);
    }

    public static void logError(final String message) {
        sb.append(message);
        LOGGER.error(message);
    }

    public static void logWarn(final String message) {
        sb.append(message);
        LOGGER.warn(message);
    }
}
