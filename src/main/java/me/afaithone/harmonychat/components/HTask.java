package me.afaithone.harmonychat.components;

import me.afaithone.harmonychat.components.interfaces.Taskable;
import org.bukkit.Bukkit;

public abstract class HTask implements Taskable {

    protected int id;

    @Override
    public void stop() {
        Bukkit.getScheduler().cancelTask(id);
    }
}
