package at.helpch.bukkitforcedhosts.events;

import at.helpch.bukkitforcedhosts.annotations.Hosts;
import at.helpch.bukkitforcedhosts.expiringhashmap.SelfExpiringHashMap;
import at.helpch.bukkitforcedhosts.expiringhashmap.SelfExpiringMap;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class JoinEvent implements Listener {
    @Inject @Hosts private FileConfiguration locations;

    private final SelfExpiringMap<UUID, Set<String>> commandsToBeRan = new SelfExpiringHashMap<>(20000);

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        final List<String> commands = this.locations.getStringList(e.getHostname().split(":")[0].toLowerCase().replace(".", "-"));

        if (commands != null && !commands.isEmpty()) {
            commandsToBeRan.put(e.getPlayer().getUniqueId(), commands.stream()
                    .map(c -> String.format(c, e.getPlayer().getName()))
                    .collect(Collectors.toSet()));
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        final UUID uuid = e.getPlayer().getUniqueId();

        if (commandsToBeRan.containsKey(uuid)) {
            commandsToBeRan.get(uuid).forEach(c -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), c));
            commandsToBeRan.remove(uuid);
        }
    }
}
