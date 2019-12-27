package at.helpch.bukkitforcedhosts.registerables;

import com.google.inject.Inject;
import me.piggypiglet.framework.registerables.StartupRegisterable;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class BStatsRegisterable extends StartupRegisterable {
    @Inject private JavaPlugin main;

    @Override
    protected void execute() {
        new Metrics(main);
    }
}
