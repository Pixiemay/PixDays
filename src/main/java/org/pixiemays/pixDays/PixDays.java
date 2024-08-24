package org.pixiemays.pixDays;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class PixDays extends JavaPlugin {
    private final Set<UUID> disabledPlayers = new HashSet<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        new DayCounterTast(this).runTaskTimer(this, 0, 5);
        this.getCommand("toggledays").setExecutor(new ToggleDaysCommand(this));
    }

    public Set<UUID> getDisabledPlayers() {
        return disabledPlayers;
    }
}
