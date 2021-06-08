package me.lumenowaty.harmonychat.components;

import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.components.interfaces.Savable;

import java.io.File;

public abstract class HLoader implements Savable {

    protected final String fileName;

    public HLoader(String fileName) {
        this.fileName = HarmonyChat.getInstance().getDataFolder()+ File.separator+fileName;
    }
}
