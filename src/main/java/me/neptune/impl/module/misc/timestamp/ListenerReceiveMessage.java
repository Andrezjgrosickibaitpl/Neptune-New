package me.neptune.impl.module.misc.timestamp;

import me.neptune.api.event.listener.ModuleListener;
import me.neptune.impl.events.ReceiveMessageEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ListenerReceiveMessage extends ModuleListener<TimeStamp, ReceiveMessageEvent> {
    public ListenerReceiveMessage(TimeStamp module) {
        super(module, ReceiveMessageEvent.class);
    }

    @Override
    public void call(ReceiveMessageEvent event) {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String currentTimeStr = sdf.format(currentTime);

        event.setMessage(Text.of("[" + Formatting.GRAY + currentTimeStr + Formatting.RESET + "] ").copy().append(event.getMessage()));
    }
}
