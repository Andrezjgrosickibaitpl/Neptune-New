package me.neptune.impl.module.misc.timestamp;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;

public class TimeStamp extends Module {
    public TimeStamp() {
        super("Timestamp", new String[]{"Timestamp"}, "add timestamp to chat", Category.OTHER);
        this.offerListeners(new ListenerReceiveMessage(this));
    }
}