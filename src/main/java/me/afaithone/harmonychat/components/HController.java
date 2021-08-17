package me.afaithone.harmonychat.components;

import me.afaithone.harmonychat.PluginController;

public abstract class HController {

    protected PluginController controller;

    public HController(PluginController controller) {
        this.controller = controller;
    }
}
