package me.afaithone.harmonychat.systems.privatemessagessystem;

import me.afaithone.harmonychat.components.HMap;

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
