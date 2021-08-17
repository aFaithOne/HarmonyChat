package me.afaithone.harmonychat.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeUtils {

    public static long getSecondsDifference(LocalDateTime first, LocalDateTime second) {
        return ChronoUnit.SECONDS.between(second.toLocalTime(), first.toLocalTime());
    }
}
