package me.lumenowaty.harmonychat.systems.privatemessagessystem;

import me.lumenowaty.harmonychat.components.HMap;

import java.util.UUID;

public class ReplyMessageHolder {

    private final HMap<UUID, UUID> replyMap;

    public ReplyMessageHolder() {
        this.replyMap = new HMap<>();
    }

    public HMap<UUID, UUID> getReplyMap() {
        return replyMap;
    }
}
