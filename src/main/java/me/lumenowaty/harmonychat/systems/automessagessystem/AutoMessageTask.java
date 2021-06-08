package me.lumenowaty.harmonychat.systems.automessagessystem;

import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.components.HRepeatingTask;
import me.lumenowaty.harmonychat.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

public class AutoMessageTask extends HRepeatingTask {

    private final AutoMessageManager manager;
    private final int displayTime;

    public AutoMessageTask(int period, int delayed, int displayTime, AutoMessageManager manager) {
        super(period, delayed);
        this.manager = manager;
        this.displayTime = displayTime;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void run() {
        this.id = new BukkitRunnable() {
            private final List<String> messages = manager.getMessagesHolder().getMessages().getList();
            private int i = 0;

            @Override
                public void run() {
                    if (i == messages.size()) i = 0;
                        // Bukkit.broadcastMessage(ChatUtils.formatText(messages.get(i)));
                        sendMessage(ChatUtils.formatText(messages.get(i)));
                        i++;
                }
        }.runTaskTimerAsynchronously(HarmonyChat.getInstance(), 20L*delayed, 20L*period).getTaskId();
    }

    private void sendMessage(String message) {
        BossBar bossBar = Bukkit.createBossBar(message, BarColor.PURPLE, BarStyle.SEGMENTED_10);

        Bukkit.getOnlinePlayers().forEach(bossBar::addPlayer);

        BukkitScheduler scheduler = Bukkit.getScheduler();
        AutoMessageDisplayTask displayTask = new AutoMessageDisplayTask(bossBar, 0, 1, this.displayTime);

        displayTask.run();

        scheduler.scheduleSyncDelayedTask(HarmonyChat.getInstance(), () -> {
            bossBar.removeAll();;
            scheduler.cancelTask(displayTask.getId());
        }, 20L*this.displayTime);
    }
}
