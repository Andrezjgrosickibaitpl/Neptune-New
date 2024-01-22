package me.neptune.api.util.render;

import me.neptune.api.util.math.StopWatch;

public class DotUtil {
    private static final StopWatch dotTimer = new StopWatch();

    private static String dots = "";

    public static String getDots() {
        if (dotTimer.passed(500)) {
            dots += ".";
            dotTimer.reset();
        }

        if (dots.length() > 3) {
            dots = "";
        }

        return dots;
    }
}