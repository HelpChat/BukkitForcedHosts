package at.helpch.bukkitforcedhosts.events;

import at.helpch.bukkitforcedhosts.annotations.Hosts;
import at.helpch.bukkitforcedhosts.annotations.hooks.AuthMe;
import at.helpch.bukkitforcedhosts.lang.Lang;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import me.piggypiglet.framework.lang.LanguageGetter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.*;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class JoinEvent implements Listener {
    @Inject @Hosts private FileConfiguration locations;
    @Inject @AuthMe private boolean authMe;

    private final Map<UUID, Set<String>> commandsToBeRan = new HashMap<>();

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        final FileConfiguration spawn = locations.getConfigSection(e.getHostname().split(":")[0].toLowerCase().replace('.', '-'), null);

        if (spawn == null) return;

        final Set<String> hooks = spawn.getStringList("hooks", new ArrayList<>()).stream().map(String::toLowerCase).collect(Collectors.toSet());
        final List<String> commands = spawn.getStringList("commands");

        if (hooks.contains("authme") && !authMe) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, LanguageGetter.get(Lang.NO_AUTHME.getPath()));
            return;
        }

        if (commands != null && !commands.isEmpty()) {
            commandsToBeRan.put(e.getPlayer().getUniqueId(), commands.stream()
                    .map(c -> String.format(c, e.getPlayer().getName()))
                    .collect(Collectors.toSet()));
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!authMe) {
            run(e.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        commandsToBeRan.remove(e.getPlayer().getUniqueId());
    }

    public void run(Player player) {
        final UUID uuid = player.getUniqueId();

        if (commandsToBeRan.containsKey(uuid)) {
            commandsToBeRan.get(uuid).forEach(c -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), c));
            commandsToBeRan.remove(uuid);
        }
    }
}
