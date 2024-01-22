package me.neptune.impl.command;

import me.neptune.Neptune;
import me.neptune.api.command.Command;
import me.neptune.api.util.logging.Logger;

public class ClearPopsCommand extends Command {
    public ClearPopsCommand() {
        super(new String[]{"ClearPops", "clear"});
    }

    @Override
    public void run(String[] args) {
        //Neptune.getPopManager().getPopMap().clear();
        Logger.getLogger().log("Totem pops cleared");
    }
}
