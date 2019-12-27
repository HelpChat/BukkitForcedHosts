package at.helpch.bukkitforcedhosts;

import at.helpch.bukkitforcedhosts.annotations.Hosts;
import me.piggypiglet.framework.Framework;
import me.piggypiglet.framework.utils.annotations.files.Lang;
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
                .commandPrefixes("bfh", "bukkitforcedhosts")
                .pckg("at.helpch.bukkitforcedhosts")
                .fileDir(getDataFolder().getPath())
                .file(true, "hosts", "/hosts.yml", "hosts.yml", Hosts.class)
                .file(true, "lang", "/lang.yml", "lang.yml", Lang.class)
                .customLang("lang", at.helpch.bukkitforcedhosts.lang.Lang.values())
                .build()
                .init();
    }
}
