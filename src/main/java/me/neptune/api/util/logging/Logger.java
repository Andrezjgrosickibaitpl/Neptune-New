package me.neptune.api.util.logging;

import me.neptune.Neptune;
import me.neptune.api.interfaces.Loggable;
import me.neptune.api.interfaces.Minecraftable;
import me.neptune.api.util.text.TextColor;
import me.neptune.asm.ducks.IChatHud;
import net.minecraft.text.Text;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class Logger implements Loggable, Minecraftable {
    private static Logger INSTANCE = new Logger();
    private static final int MESSAGE_ID = -2147442069;
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Neptune.NAME);

    private static final String ALERT =
            TextColor.DARK_BLUE + "["
            + TextColor.BLUE + "Neptune"
            + TextColor.DARK_BLUE + "]"
            + TextColor.RESET + " ";

    @Override
    public void log(String text) {
        if (mc.inGameHud.getChatHud() != null) {
            log(text, MESSAGE_ID);
        }
    }

    @Override
    public void log(String text, int id) {
        if (mc.inGameHud.getChatHud() != null) {
            ((IChatHud) mc.inGameHud.getChatHud()).neptuneMessage(Text.literal("%s%s".formatted(ALERT, text)), id);
        }
    }

    @Override
    public void log(String text, boolean delete) {
        if (mc.inGameHud.getChatHud() != null) {
            if (delete) {
                log(text);
                return;
            }

            log(text, 0);
        }
    }

    @Override
    public void log(Level level, String text) {
        LOGGER.log(level, text);
    }

    public static Logger getLogger() {
        return INSTANCE == null ? (INSTANCE = new Logger()) : INSTANCE;
    }
}
