package me.lumenowaty.harmonychat.systems.privategroupssystem;

import me.lumenowaty.harmonychat.components.HLoader;
import org.bukkit.Bukkit;

import java.io.*;
import java.util.logging.Logger;

import static me.lumenowaty.harmonychat.PluginController.HARMONY_CHAT_PREFIX;

public class SocialGroupLoader extends HLoader {

    private SocialGroupsHolder holder;

    public SocialGroupLoader(String fileName) {
        super(fileName);
    }

    public void load() {
        Logger logger = Bukkit.getLogger();

        this.holder = null;

        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            this.holder = (SocialGroupsHolder) in.readObject();

            in.close();
            file.close();

            logger.info(HARMONY_CHAT_PREFIX + " GroupMessageMap has been deserialized.");
        } catch (IOException ex) {
            this.holder = new SocialGroupsHolder();
            logger.info(HARMONY_CHAT_PREFIX + " could not load GroupMessageMap");
        } catch (ClassNotFoundException ex) {
            logger.info(HARMONY_CHAT_PREFIX + " Not found a class.");
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
            logger.info(HARMONY_CHAT_PREFIX + " GroupMessageMap has been serialized.");

        } catch (IOException ex) {
            logger.info(HARMONY_CHAT_PREFIX + " some problems with IOException.");
        }
    }

    public SocialGroupsHolder getHolder() {
        return holder;
    }
}
