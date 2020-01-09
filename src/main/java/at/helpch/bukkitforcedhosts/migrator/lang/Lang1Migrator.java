package at.helpch.bukkitforcedhosts.migrator.lang;

import me.piggypiglet.framework.file.framework.MutableFileConfiguration;
import me.piggypiglet.framework.file.migration.Migrator;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class Lang1Migrator extends Migrator {
    public Lang1Migrator() {
        super("lang", c -> c.getInt("config-version", -1) == -1);
    }

    @Override
    protected void migrate(MutableFileConfiguration config) {
        config.set("config-version", 1);
        config.set("kick.no_authme", "AuthMe is enabled for this domain, but AuthMe isn't present on the server.");
    }
}
