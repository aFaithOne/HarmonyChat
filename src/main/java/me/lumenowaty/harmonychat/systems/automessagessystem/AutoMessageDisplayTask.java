package me.lumenowaty.harmonychat.systems.automessagessystem;

import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.components.HRepeatingTask;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;

public class AutoMessageDisplayTask extends HRepeatingTask {

    private final BossBar bossBar;

    private final int displayTime;

    public AutoMessageDisplayTask(BossBar bossBar, int delayed, int period, int displayTime) {
        super(period, delayed);
        this.bossBar = bossBar;
        this.displayTime = displayTime;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void run() {
        this.id = Bukkit.getScheduler().scheduleSyncRepeatingTask(HarmonyChat.getInstance(),
                () -> this.bossBar.setProgress(this.bossBar.getProgress()-((1/((float) this.displayTime*10)))+0.0001),
                this.delayed, 2L*this.period);
    }
    // () -> this.bossBar.setProgress(this.bossBar.getProgress()-((1/((float) this.displayTime*10)))+0.0001), this.delayed, 2L*this.period);

    // this.bossBar.setProgress(this.bossBar.getProgress()+((1/((float) this.displayTime*10)))-0.00001);
}
