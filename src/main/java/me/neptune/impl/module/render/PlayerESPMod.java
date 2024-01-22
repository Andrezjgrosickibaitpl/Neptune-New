package me.neptune.impl.module.render;

import com.google.common.eventbus.Subscribe;
import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;

public class PlayerESPMod extends Module {
    public PlayerESPMod() {
        super("PlayerESP", new String[]{"PlayerESP"}, "Player ESP (self explanatory)", Category.RENDER);
    }

    @Subscribe
    public void onInitializeClient() {
        WorldRenderEvents.AFTER_ENTITIES.register(this::onAfterEntitiesRender);
    }

    private void onAfterEntitiesRender(WorldRenderContext context) {
        if (mc.player != null) {
            for (PlayerEntity target : mc.world.getPlayers()) {
                if (target != mc.player) {
                    renderPlayerESP(context.matrixStack(), target);
                }
            }
        }
    }

    private void renderPlayerESP(MatrixStack matrixStack, PlayerEntity player) {
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();

        matrixStack.push();
        matrixStack.translate(x, y, z);

        // Render ESP here, e.g., a bounding box or other visual indication

        matrixStack.pop();
    }
}
