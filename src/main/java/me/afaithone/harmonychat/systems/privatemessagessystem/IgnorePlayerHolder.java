package me.afaithone.harmonychat.systems.privatemessagessystem;

import me.afaithone.harmonychat.components.HMap;

import java.io.Serializable;
import java.util.*;

public class IgnorePlayerHolder implements Serializable {

    private final HMap<UUID, Set<UUID>> ignoreMap;

    public IgnorePlayerHolder() {
        this.ignoreMap = new HMap<>();
    }

    public HMap<UUID, Set<UUID>> getIgnoreMap() {
        return ignoreMap;
    }
}
