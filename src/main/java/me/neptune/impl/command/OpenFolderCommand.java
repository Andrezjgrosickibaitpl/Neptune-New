package me.neptune.impl.command;

import me.neptune.Neptune;
import me.neptune.api.command.Command;
import me.neptune.api.util.logging.Logger;
import net.minecraft.util.Util;

public class OpenFolderCommand extends Command {
    public OpenFolderCommand() {
        super(new String[]{"OpenFolder", "folder"});
    }

    @Override
    public void run(String[] args) {
        Util.getOperatingSystem().open(Neptune.DIRECTORY.toURI());
        Logger.getLogger().log("Opened the neptune folder");
    }
}
