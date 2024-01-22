package me.neptune.impl.command;

import me.neptune.Neptune;
import me.neptune.api.command.Command;
import me.neptune.api.module.Module;
import me.neptune.api.util.logging.Logger;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super(new String[]{"Toggle", "t"});
    }

    @Override
    public void run(String[] args) {
        if (args.length < 2) {
            Logger.getLogger().log("No module was entered");
            return;
        }

        Module module = Neptune.getModuleManager().getModuleByAlias(args[1]);

        if (module == null) {
            Logger.getLogger().log("No such module exists");
            return;
        }

        module.toggle();
        //ChatUtil.printMessage(String.format("%s has been toggled %s", module.getName(), module.isEnabled() ? "on." : "off."));
    }
}
