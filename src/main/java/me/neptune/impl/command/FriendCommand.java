package me.neptune.impl.command;

import me.neptune.Neptune;
import me.neptune.api.command.Command;
import me.neptune.api.util.logging.Logger;
import me.neptune.api.util.text.TextColor;
import net.minecraft.util.Formatting;

public class FriendCommand extends Command {
    public FriendCommand() {
        super(new String[]{"Friend", "f", "frien", "amigo"});
    }

    @Override
    public void run(String[] args) {
        if (args.length < 3) {
            Logger.getLogger().log("Command usage: add/del, name");
            return;
        }

        final String argument = args[1];
        final String name = args[2];

        switch (argument.toUpperCase()) {
            case ("ADD") -> {
                if (Neptune.getFriendManager().isFriend(name)) {
                    Logger.getLogger().log(String.format("%s is already a friend", name));
                    return;
                }
                Neptune.getFriendManager().addFriend(name);
                Logger.getLogger().log(String.format("Added %s%s%s as a friend", Formatting.BLUE, name, Formatting.LIGHT_PURPLE));
            }
            case ("DEL"), ("DELETE"), ("REM"), ("REMOVE") -> {
                if (!Neptune.getFriendManager().isFriend(name)) {
                    Logger.getLogger().log("That user is not a friend");
                    return;
                }
                Neptune.getFriendManager().removeFriend(name);
                Logger.getLogger().log(String.format("Removed %s%s%s as a friend", TextColor.RED, name, TextColor.LIGHT_PURPLE));
            }
        }
    }
}
