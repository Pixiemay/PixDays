package org.pixiemays.pixDays;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.TimeSkipEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class PixDays extends JavaPlugin implements Listener {
    private int dayCount;
    private final Set<UUID> disabledPlayers = new HashSet<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadDisabledPlayers();
        new DayCounterTast(this).runTaskTimer(this, 0, 5);
        this.getCommand("toggledays").setExecutor(new ToggleDaysCommand(this));
    }

    @Override
    public void onDisable() {
        saveDisabledPlayers();
        saveConfig();
    }

    public Set<UUID> getDisabledPlayers() {
        return disabledPlayers;
    }

    @EventHandler
    public void onTimeSkip(TimeSkipEvent event) {
        if (event.getSkipReason() == TimeSkipEvent.SkipReason.NIGHT_SKIP) {
            dayCount++;
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!disabledPlayers.contains(event.getPlayer().getUniqueId())) {
            event.getPlayer().sendActionBar(ChatColor.GREEN + "Прожито дней: " + ChatColor.WHITE + dayCount);
        }
    }

    public void loadDisabledPlayers() {
        if (getConfig().isSet("disabledPlayers")) {
            for (String uuidString : getConfig().getStringList("disabledPlayers")) {
                disabledPlayers.add(UUID.fromString(uuidString));
            }
        }
    }
    public void saveDisabledPlayers() {
        getConfig().set("disabledPlayers", null);
        getConfig().set("disabledPlayers", disabledPlayers.stream().map(UUID::toString).toList());
    }
}
