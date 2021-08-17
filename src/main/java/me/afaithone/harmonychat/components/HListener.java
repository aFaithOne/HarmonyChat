package me.afaithone.harmonychat.components;

import me.afaithone.harmonychat.PluginController;
import org.bukkit.event.Listener;

public abstract class HListener extends HController implements Listener {

    public HListener(PluginController controller) {
        super(controller);
    }
}
