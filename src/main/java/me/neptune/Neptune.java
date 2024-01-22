package me.neptune;

import me.neptune.api.event.bus.SimpleBus;
import me.neptune.api.event.bus.api.EventBus;
import me.neptune.api.util.logging.Logger;
import me.neptune.impl.manager.*;
import org.apache.logging.log4j.Level;

import java.io.File;

public class Neptune {
	public static String NAME = "Neptune";
	public static final String VERSION = "1.0.0";
	public static final File DIRECTORY = new File(System.getProperty("user.home"), "Neptune");
	private static final EventBus EVENT_BUS = new SimpleBus();

	private static final CommandManager COMMAND_MANAGER = new CommandManager();
	private static final ModuleManager MODULE_MANAGER = new ModuleManager();
	private static final ConfigManager CONFIG_MANAGER = new ConfigManager();
	private static final TimerManager TIMER_MANAGER = new TimerManager();
	private static final FriendManager FRIEND_MANAGER = new FriendManager();

	public static void init() {
		EVENT_BUS.subscribe(MODULE_MANAGER);
		EVENT_BUS.subscribe(COMMAND_MANAGER);

		CONFIG_MANAGER.init();
		FRIEND_MANAGER.init();

		if (!DIRECTORY.exists()) {
			Logger.getLogger().log(Level.INFO, String.format("%s client directory.", DIRECTORY.mkdir() ? "Created" : "Failed to create"));
		}

		Logger.getLogger().log(Level.INFO, String.format("%s - %s loaded successfully%n", NAME, VERSION));
	}

	public static ModuleManager getModuleManager() {
		return MODULE_MANAGER;
	}

	public static CommandManager getCommandManager() {
		return COMMAND_MANAGER;
	}


	public static ConfigManager getConfigManager() {
		return CONFIG_MANAGER;
	}

	public static FriendManager getFriendManager() {
		return FRIEND_MANAGER;
	}

	public static EventBus getEventBus() {
		return EVENT_BUS;
	}

	public static TimerManager getTimerManager() {
		return TIMER_MANAGER;
	}
}
