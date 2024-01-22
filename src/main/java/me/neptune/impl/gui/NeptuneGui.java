package me.neptune.impl.gui;

import me.neptune.Neptune;
import me.neptune.api.interfaces.Minecraftable;
import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.util.render.RenderMethods;
import me.neptune.impl.gui.button.ModuleButton;
import me.neptune.impl.gui.item.Item;
import me.neptune.impl.module.other.clickgui.ClickGUI;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Comparator;

public class NeptuneGui extends Screen implements Minecraftable {
    private static NeptuneGui INSTANCE;
    private final ArrayList<Panel> panels;

    public NeptuneGui() {
        super(Text.empty());
        panels = new ArrayList<>();
        load();
    }

    public static NeptuneGui getClickGui() {
        return (INSTANCE == null) ? (INSTANCE = new NeptuneGui()) : INSTANCE;
    }

    private void load() {
        int x = -100;
        for (Category category : Category.values()) {
            panels.add(new Panel(category.getLabel(), x += 102, 4, true) {
                @Override
                public void setupItems() {
                    for (Module modules : Neptune.getModuleManager().getModules()) {
                        if (!modules.getCategory().equals(category)) {
                            continue;
                        }
                        addButton(new ModuleButton(modules));
                    }
                }
            });
        }
        panels.forEach(panel -> panel.getItems().sort(Comparator.comparing(Item::getLabel)));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (ClickGUI.get().background()) {
            RenderMethods.drawRect(
                    context,
                    0,
                    0,
                    context.getScaledWindowWidth(),
                    context.getScaledWindowHeight(),
                    -1879048192
            );
        }
        //context.getMatrices().scale(ClickGUI.get().scale(), ClickGUI.get().scale(), ClickGUI.get().scale());
        panels.forEach(panel -> panel.render(context, mouseX, mouseY, delta));
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        panels.forEach(panel -> panel.mouseClicked(mouseX, mouseY, button));
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int releaseButton) {
        panels.forEach(panel -> panel.mouseReleased(mouseX, mouseY, releaseButton));
        return super.mouseReleased(mouseX, mouseY, releaseButton);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256 && this.shouldCloseOnEsc()) {
            ClickGUI.get().disable();
            this.close();
            return true;
        }

        panels.forEach(panel -> panel.onKeyTyped(keyCode, scanCode, modifiers));
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    public ArrayList<Panel> getPanels() {
        return panels;
    }
}