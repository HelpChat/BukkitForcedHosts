package at.helpch.bukkitforcedhosts.commands;

import at.helpch.bukkitforcedhosts.lang.Lang;
import com.google.inject.Inject;
import me.piggypiglet.framework.commands.framework.BaseCommand;
import me.piggypiglet.framework.file.FileManager;
import me.piggypiglet.framework.user.User;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ReloadCommand extends BaseCommand {
    @Inject private FileManager fileManager;

    public ReloadCommand() {
        super("reload");
        options
                .description("Reload the hosts.yml file.")
                .permissions("bukkitforcedhosts.reload")
                .usage("");
    }

    @Override
    protected boolean execute(User user, String[] args) {
        try {
            fileManager.update("hosts");
        } catch (Exception e) {
            user.sendMessage(Lang.RELOAD_ERROR);
            e.printStackTrace();
            return true;
        }

        user.sendMessage(Lang.RELOAD_SUCCESS);
        return true;
    }
}
