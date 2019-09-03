package at.helpch.bukkitforcedhosts;

import at.helpch.bukkitforcedhosts.annotations.Locations;
import me.piggypiglet.framework.Framework;
import org.bukkit.plugin.java.JavaPlugin;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class BukkitForcedHosts extends JavaPlugin {
    @Override
    public void onEnable() {
        Framework.builder()
                .main(JavaPlugin.class, this)
                .commandPrefix("bfh")
                .pckg("at.helpch.bukkitforcedhosts")
                .file(true, "locations", "/locations.yml", getDataFolder() + "/locations.yml", Locations.class)
                .build()
                .init();
    }
}
