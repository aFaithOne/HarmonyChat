package me.afaithone.harmonychat.systems.automessagessystem;

import me.afaithone.harmonychat.components.HList;

public class AutoMessageHolder {

    private final HList<String> messages;

    public AutoMessageHolder() {
        this.messages = new HList<>();
    }

    public HList<String> getMessages() {
        return this.messages;
    }
}
