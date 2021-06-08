package me.lumenowaty.harmonychat.components;

import me.lumenowaty.harmonychat.PluginController;
import org.bukkit.event.Listener;

public abstract class HListener extends HController implements Listener {

    public HListener(PluginController controller) {
        super(controller);
    }
}
