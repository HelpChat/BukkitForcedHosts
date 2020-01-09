package at.helpch.bukkitforcedhosts.registerables.hooks.authme;

import at.helpch.bukkitforcedhosts.annotations.hooks.AuthMe;
import at.helpch.bukkitforcedhosts.events.hooks.AuthMeLoginEvent;
import com.google.inject.Inject;
import me.piggypiglet.framework.registerables.StartupRegisterable;
import org.bukkit.plugin.java.JavaPlugin;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class AuthMeEventRegisterable extends StartupRegisterable {
    @Inject @AuthMe private boolean enabled;
    @Inject private JavaPlugin main;

    @Override
    protected void execute() {
        if (enabled) {
            main.getServer().getPluginManager().registerEvents(injector.getInstance(AuthMeLoginEvent.class), main);
        }
    }
}
