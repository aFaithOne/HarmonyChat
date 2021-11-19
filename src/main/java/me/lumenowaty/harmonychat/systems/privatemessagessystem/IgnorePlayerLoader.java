package me.lumenowaty.harmonychat.systems.privatemessagessystem;

import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HLoader;
import org.bukkit.Bukkit;

import java.io.*;
import java.util.logging.Logger;

public class IgnorePlayerLoader extends HLoader {

    private IgnorePlayerHolder holder;

    public IgnorePlayerLoader(IgnorePlayerHolder holder) {
        super("ignoreMap.ser");
        this.holder = holder;
    }

    public void load() {
        Logger logger = Bukkit.getLogger();

        this.holder = null;

        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            this.holder = (IgnorePlayerHolder) in.readObject();

            in.close();
            file.close();

            logger.info(PluginController.HARMONY_CHAT_PREFIX + " IgnorePlayerMap has been deserialized.");
        } catch (IOException ex) {
            this.holder = new IgnorePlayerHolder();
            logger.info(PluginController.HARMONY_CHAT_PREFIX + " could not load IgnorePlayerMap");
        } catch (ClassNotFoundException ex) {
            logger.info(PluginController.HARMONY_CHAT_PREFIX + " Not found a class.");
        }
    }

    public void save() {
        Logger logger = Bukkit.getLogger();
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(holder);
            out.close();
            file.close();
            logger.info(PluginController.HARMONY_CHAT_PREFIX + " IgnorePlayerMap has been serialized.");

        } catch (IOException ex) {
            logger.info(PluginController.HARMONY_CHAT_PREFIX + " some problems with IOException.");
        }
    }

    public IgnorePlayerHolder getHolder() {
        return this.holder;
    }
}
