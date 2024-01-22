package me.neptune.impl.command;

import me.neptune.Neptune;
import me.neptune.api.command.Command;
import me.neptune.api.util.logging.Logger;

public class ConfigCommand extends Command {
    public ConfigCommand() {
        super(new String[]{"Config", "configs", "con"});
    }

    @Override
    public void run(String[] args) {
        switch (args[1].toUpperCase()) {
            case "SAVE" ->  {
                Neptune.getConfigManager().saveConfig();
                Neptune.getFriendManager().saveFriends();
                Logger.getLogger().log("Config saved successfully");
            }
            case "LOAD" -> {
                Neptune.getConfigManager().loadConfig();
                Neptune.getFriendManager().loadFriends();
                Logger.getLogger().log("Config loaded successfully");
            }
        }
    }
}
