package me.lumenowaty.harmonychat.systems.antispamsystem;

import java.time.LocalDateTime;

public class ASPMessage {

    private final String message;
    private final LocalDateTime time;

    public ASPMessage(String message, LocalDateTime time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
