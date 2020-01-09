package at.helpch.bukkitforcedhosts.registerables.hooks.authme;

import at.helpch.bukkitforcedhosts.annotations.hooks.AuthMe;
import me.piggypiglet.framework.registerables.StartupRegisterable;
import org.bukkit.Bukkit;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class AuthMeBooleanRegisterable extends StartupRegisterable {
    @Override
    protected void execute() {
        addAnnotatedBinding(boolean.class, AuthMe.class, Bukkit.getServer().getPluginManager().isPluginEnabled("AuthMe"));
    }
}
