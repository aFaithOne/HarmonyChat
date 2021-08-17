package me.afaithone.harmonychat.components;

import me.afaithone.harmonychat.components.interfaces.Savable;
import me.afaithone.harmonychat.HarmonyChat;

import java.io.File;

public abstract class HLoader implements Savable {

    protected final String fileName;

    public HLoader(String fileName) {
        this.fileName = HarmonyChat.getInstance().getDataFolder()+ File.separator+fileName;
    }
}
