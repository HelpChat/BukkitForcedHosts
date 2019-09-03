package at.helpch.bukkitforcedhosts.events;

import at.helpch.bukkitforcedhosts.annotations.Locations;
import com.google.inject.Inject;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class JoinEvent implements Listener {
    @Inject @Locations private FileConfiguration locations;

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        String location = locations.getString(e.getHostname().toLowerCase());

        if (!location.equals("null")) {
            String[] parts = location.replace(" ", "").split(":");
            String[] coords = parts[1].split(",");

            e.getPlayer().teleport(new Location(Bukkit.getWorld(parts[0]), Double.parseDouble(coords[0]), Double.parseDouble(coords[1]), Double.parseDouble(coords[2])));
        }
    }
}
