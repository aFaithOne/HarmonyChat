package me.afaithone.harmonychat.systems.privatemessagessystem;

import me.afaithone.harmonychat.PluginController;
import me.afaithone.harmonychat.components.HController;

public class PrivateMessageManager extends HController {

    private final IgnorePlayerHolder ignoreHolder;
    private final ReplyMessageHolder replyHolder;

    public PrivateMessageManager(PluginController controller) {
        super(controller);

        this.ignoreHolder = new IgnorePlayerHolder();
        this.replyHolder = new ReplyMessageHolder();
    }

    public void loadComponents() {
        IgnorePlayerLoader saver = new IgnorePlayerLoader(this.ignoreHolder);
        saver.load();

        this.ignoreHolder.getIgnoreMap()
                .setMap(saver.getHolder().getIgnoreMap().getMap());
    }

    public void saveComponents() {
        IgnorePlayerLoader saver = new IgnorePlayerLoader(this.ignoreHolder);
        saver.save();
    }

    public IgnorePlayerHolder getIgnoreHolder() {
        return this.ignoreHolder;
    }

    public ReplyMessageHolder getReplyHolder() {
        return this.replyHolder;
    }
}
