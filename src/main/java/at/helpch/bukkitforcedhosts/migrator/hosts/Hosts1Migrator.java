package at.helpch.bukkitforcedhosts.migrator.hosts;

import me.piggypiglet.framework.file.framework.AbstractFileConfiguration;
import me.piggypiglet.framework.file.framework.MutableFileConfiguration;
import me.piggypiglet.framework.file.migration.Migrator;

import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class Hosts1Migrator extends Migrator {
    public Hosts1Migrator() {
        super("hosts", r -> r.getInt("config-version", -1) == -1);
    }
    
    @Override
    protected void migrate(MutableFileConfiguration config) {
        config.set("config-version", 1);

        ((AbstractFileConfiguration) config).getAll().forEach((k, v) -> {
            if (v instanceof List<?>) {
                config.set(k, null);
                config.set(k + ".commands", v);
            }
        });
    }
}
