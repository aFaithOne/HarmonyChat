package me.afaithone.harmonychat.components;

import me.afaithone.harmonychat.PluginController;
import org.bukkit.command.CommandExecutor;

public abstract class HCommandExecutor extends HController implements CommandExecutor {

    public HCommandExecutor(PluginController controller) {
        super(controller);
    }
}
