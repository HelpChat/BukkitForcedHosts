package at.helpch.bukkitforcedhosts.events.hooks;

import at.helpch.bukkitforcedhosts.annotations.hooks.AuthMe;
import at.helpch.bukkitforcedhosts.events.JoinEvent;
import com.google.inject.Inject;
import fr.xephi.authme.events.LoginEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class AuthMeLoginEvent implements Listener {
    @Inject @AuthMe private boolean enabled;
    @Inject private JoinEvent joinEvent;

    @EventHandler
    public void onLogin(LoginEvent e) {
        if (enabled) {
            joinEvent.run(e.getPlayer());
        }
    }
}
