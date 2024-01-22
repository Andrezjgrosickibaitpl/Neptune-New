package me.neptune.api.util.block;

import me.neptune.api.interfaces.Minecraftable;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public class PlacemenetUtil implements Minecraftable {

    public static void place(BlockPos pos) {
        assert mc.player != null && mc.interactionManager != null;

        
        /*ActionResult result = mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND);
        if (result == ActionResult.SUCCESS) {
            mc.player.swingHand(Hand.MAIN_HAND);
        }*/
    }

}
