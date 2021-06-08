package me.lumenowaty.harmonychat.components;

import me.lumenowaty.harmonychat.PluginController;
import org.bukkit.command.CommandExecutor;

public abstract class HCommandExecutor extends HController implements CommandExecutor {

    public HCommandExecutor(PluginController controller) {
        super(controller);
    }
}
