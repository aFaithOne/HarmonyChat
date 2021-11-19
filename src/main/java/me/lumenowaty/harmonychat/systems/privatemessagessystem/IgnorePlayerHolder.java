package me.lumenowaty.harmonychat.systems.privatemessagessystem;

import me.lumenowaty.harmonychat.components.HMap;

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
