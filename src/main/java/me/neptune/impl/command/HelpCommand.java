package me.neptune.impl.command;

import me.neptune.Neptune;
import me.neptune.api.command.Command;
import me.neptune.api.util.logging.Logger;

import java.util.StringJoiner;

public class HelpCommand extends Command {
    public HelpCommand() {
        super(new String[]{"help", "h"});
    }

    @Override
    public void run(String[] args) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        Neptune.getCommandManager().getCommands().forEach(module -> stringJoiner.add(module.getAlias()[0]));
        Logger.getLogger().log(String.format("List of commands you can use: %s", stringJoiner));
    }
}
