package me.neptune.impl.command;

import me.neptune.Neptune;
import me.neptune.api.command.Command;
import me.neptune.api.module.Module;
import me.neptune.api.util.logging.Logger;
import me.neptune.api.value.util.KeyBind;

public class SetKeyBindCommand extends Command {
    public SetKeyBindCommand() {
        super(new String[]{"Bind", "SetKeyBind", "b"});
    }

    @Override
    public void run(String[] args) {
        final Module module = Neptune.getModuleManager().getModuleByAlias(args[1]);

        if (module == null) {
            Logger.getLogger().log("That module does not exist");
            return;
        }

        int bind = KeyBind.getKeyIndex(args[2]);

        if (bind == 0) {
            Logger.getLogger().log("Unknown bind");
            return;
        }

        module.setBind(new KeyBind(bind).getKey());
        Logger.getLogger().log(String.format("Bound %s to %s", module.getLabel(), KeyBind.getKeyString(bind)));
    }
}
