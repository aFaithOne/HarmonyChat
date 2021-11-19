package me.lumenowaty.harmonychat.components;

import me.lumenowaty.harmonychat.PluginController;

public abstract class HController {

    protected PluginController controller;

    public HController(PluginController controller) {
        this.controller = controller;
    }
}
