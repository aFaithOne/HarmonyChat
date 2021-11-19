package me.lumenowaty.harmonychat.components;

import me.lumenowaty.harmonychat.components.interfaces.SubCommand;

public abstract class HSubCommand<T extends HCommandExecutor> implements SubCommand {

    protected T commandExecutor;

    public HSubCommand(T commandExecutor) {
        this.commandExecutor = commandExecutor;
    }
}
