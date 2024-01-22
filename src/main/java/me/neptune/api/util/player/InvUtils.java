package me.neptune.api.util.player;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static me.neptune.api.interfaces.Minecraftable.mc;

public class InvUtils {
    // 4l04 helped me, thanks!

    public static int findItem(Item input) {
        for (int i = 0; i < 9; ++i) {
            Item item = mc.player.getInventory().getStack(i).getItem();
            if (Item.getRawId(item) != Item.getRawId(input)) continue;
            return i;
        }
        return -1;
    }

    public static int findInventory(Item item) {
        AtomicInteger slot = new AtomicInteger();
        slot.set(-1);
        for (Map.Entry<Integer, ItemStack> entry : getSlots().entrySet()) {
            if (entry.getValue().getItem() != item) continue;
            slot.set(entry.getKey());
            return slot.get();
        }
        return slot.get();
    }

    public static Map<Integer, ItemStack> getSlots() {
        HashMap<Integer, ItemStack> fullInventorySlots = new HashMap<>();

        for (int current = 0; current <= 44; ++current) {
            fullInventorySlots.put(current, mc.player.getInventory().getStack(current));
        }

        return fullInventorySlots;
    }
}
