package me.neptune.api.module;

import me.neptune.Neptune;
import me.neptune.api.event.bus.Listener;
import me.neptune.api.event.bus.api.Subscriber;
import me.neptune.api.interfaces.Labeled;
import me.neptune.api.interfaces.Minecraftable;
import me.neptune.api.util.logging.Logger;
import me.neptune.api.value.BindValue;
import me.neptune.api.value.Value;
import me.neptune.api.value.util.KeyBind;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.Formatting;

import java.util.*;

public class Module implements Subscriber, Minecraftable, Labeled {
    private final String name;
    private final String description;
    private final Category category;
    private final ArrayList<Listener<?>> listeners = new ArrayList<>();
    private final ArrayList<Value<?>> values = new ArrayList<>();
    private final BindValue bind = register(new BindValue(new String[]{"Keybind", "bind", "b"}, "Current keybind of the module", new KeyBind(-1)));
    private final String[] aliases;
    private boolean enabled;

    public Module(String name, String[] aliases, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.aliases = aliases;
        this.enabled = false;
    }

    public Module(String name, Category category) {
        this(name, new String[]{ name }, "", category);
    }

    protected <T extends Value<?>> T register(T value) {
        values.add(value);

        return value;
    }

    public void offerValues(Value<?>... properties) {
        values.addAll(Arrays.asList(properties));
    }

    public void offerListeners(Listener<?>... listeners) {
        this.listeners.addAll(Arrays.asList(listeners));
    }

    @SuppressWarnings("rawtypes")
    public Value getValue(String alias) {
        for (Value<?> value : values) {
            for (String aliases : value.getAliases()) {
                if (!alias.equalsIgnoreCase(aliases)) continue;
                return value;
            }
        }
        return null;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void setEnabled(boolean in) {
        enabled = in;
        if (enabled) {
            enable();
        } else if (!(this instanceof PersistentModule)) {
            disable();
        }
    }

    public void toggle() {
        setEnabled(!isEnabled());
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void enable() {
        enabled = true;
        Logger.getLogger().log(Formatting.BLUE + this.getLabel()  + Formatting.DARK_GREEN + " [+]");
        if (!Neptune.getEventBus().isSubscribed(this)) {
            Neptune.getEventBus().subscribe(this);
        }
        onEnable();
    }

    public static void send(Packet<?> packet) {
        ClientPlayNetworkHandler connection = mc.getNetworkHandler();
        if (connection != null) {
            connection.sendPacket(packet);
        }
    }
    
    public void disable() {
        enabled = false;
        Logger.getLogger().log(Formatting.BLUE + this.getLabel() + Formatting.DARK_RED + " [-]");
        onDisable();
        Neptune.getEventBus().unsubscribe(this);
    }

    public int getBind() {
        return bind.getValue().getKey();
    }

    public void setBind(int bind) {
        this.bind.setValue(new KeyBind(bind));
    }

    public List<Value<?>> getValues() {
        return values;
    }

    public String getDescription() {
        return this.description;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getInfo() {
        return null;
    }

    public String[] getAliases() {
        return aliases;
    }

    @Override
    public String getLabel() {
        return name;
    }

    /**
     * null convenience
     * @return if null is present
     */
    public static boolean nullCheck()
    {
        return mc.player == null || mc.world == null;
    }

    @Override
    public Collection<Listener<?>> getListeners() {
        return listeners;
    }
}
