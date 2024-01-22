package me.neptune.impl.events;

import me.neptune.api.event.events.Event;
import net.minecraft.client.gui.screen.Screen;

public class ScreenEvent extends Event {
    private final Screen screen;

    public ScreenEvent(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }
}
