package me.neptune.impl.command;

import me.neptune.Neptune;
import me.neptune.api.command.Command;
import me.neptune.api.util.logging.Logger;

public class PrefixCommand extends Command {
    public PrefixCommand() {
        super(new String[]{"Prefix", "p"});
    }

    @Override
    public void run(String[] args) {
        if (args.length < 2) {
            Logger.getLogger().log("No char was entered");
            return;
        }

        final String prefix = args[1];
        if (prefix.equalsIgnoreCase(Neptune.getCommandManager().getPrefix())) {
            Logger.getLogger().log("That is already your prefix");
            return;
        }

        if (prefix.length() < 1) {
            Logger.getLogger().log("The prefix can be only 1 char");
            return;
        }

        Neptune.getCommandManager().setPrefix(prefix);
        Logger.getLogger().log(String.format("%s is now your prefix", prefix));
    }
}
