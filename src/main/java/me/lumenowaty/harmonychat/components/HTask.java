package me.lumenowaty.harmonychat.components;

import me.lumenowaty.harmonychat.components.interfaces.Taskable;
import org.bukkit.Bukkit;

public abstract class HTask implements Taskable {

    protected int id;

    @Override
    public void stop() {
        Bukkit.getScheduler().cancelTask(id);
    }
}
