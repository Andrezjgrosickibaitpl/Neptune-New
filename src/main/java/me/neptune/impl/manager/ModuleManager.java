package me.neptune.impl.manager;

import me.neptune.api.event.bus.Listener;
import me.neptune.api.event.bus.SubscriberImpl;
import me.neptune.api.interfaces.Minecraftable;
import me.neptune.api.module.Module;
import me.neptune.api.util.keyboard.KeyPressAction;
import me.neptune.impl.events.KeyPressEvent;
import me.neptune.impl.module.combat.AutoTotem.AutoTotem;
import me.neptune.impl.module.combat.Hitbox;
import me.neptune.impl.module.combat.Quiver.Quiver;
import me.neptune.impl.module.combat.Reach;
import me.neptune.impl.module.exploit.raytracebypass.RaytraceBypass;
import me.neptune.impl.module.exploit.xcarry.XCarry;
import me.neptune.impl.module.misc.AutoLog;
import me.neptune.impl.module.misc.FastXP.FastXP;
import me.neptune.impl.module.movement.FastFall;
import me.neptune.impl.module.movement.speed.Speed;
import me.neptune.impl.module.movement.sprint.Sprint;
import me.neptune.impl.module.movement.step.Step;
import me.neptune.impl.module.other.UnfocusedFPS;
import me.neptune.impl.module.other.clickgui.ClickGUI;
import me.neptune.impl.module.other.colours.Colours;
import me.neptune.impl.module.other.hud.HUD;
import me.neptune.impl.module.misc.timestamp.TimeStamp;
import me.neptune.impl.module.player.Velocity;
import me.neptune.impl.module.player.autorespawn.AutoRespawn;
import me.neptune.impl.module.player.Timer;
import me.neptune.impl.module.render.FullBright;
import me.neptune.impl.module.render.NoRender;
import me.neptune.impl.module.render.PlayerESPMod;
import me.neptune.impl.module.render.customfov.CustomFov;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModuleManager extends SubscriberImpl implements Minecraftable {
    private final Map<Class<? extends Module>, Module> modules = new LinkedHashMap<>();

    @SuppressWarnings("DanglingJavadoc")
    public ModuleManager() {
           this.listeners.add(new Listener<>(KeyPressEvent.class) {
               @Override
               public void call(KeyPressEvent event) {
                   if (event.getKey() > 0 && mc.currentScreen == null) {
                       for (Module module : getModules()) {
                           if (event.getAction() == KeyPressAction.PRESS && event.getKey() == module.getBind()) {
                               module.toggle();
                               event.setCanceled(true);
                           }
                       }
                   }
               }
           });

        /**
         *      Combat
         */

        register(new Hitbox());
        register(new Reach());
        register(new Quiver());
        register(new AutoTotem());

        /**
         *      Misc
         */

        register(new AutoLog());
        register(new FastXP());

        /**
         *      Render
         */

        register(new PlayerESPMod());
        register(new NoRender());
        register(new CustomFov());
        register(new FullBright());

        /**
         *      Movement
         */

        register(new FastFall());
        register(new Sprint());
        register(new Step());
        register(new Speed());


        /**
         *      Player
         */
        register(new AutoRespawn());
        register(new Velocity());
        register(new Timer());


        /**
         *      Exploit
         */

        register(new RaytraceBypass());
        register(new XCarry());

        /**
         *      Other
         */

        register(new ClickGUI());
        register(new Colours());
        register(new HUD());
        register(new TimeStamp());
        register(new UnfocusedFPS());


    }

    private void register(Module module) {
        modules.put(module.getClass(), module);
    }

    public Collection<Module> getModules() {
        return modules.values();
    }

    @SuppressWarnings("unchecked")
    public <T extends Module> T get(Class<T> clazz) {
        return (T) modules.get(clazz);
    }

    public Module getModuleByAlias(String alias) {
        for (Module module : modules.values()) {
            for (String aliases : module.getAliases()) {
                if (aliases.equalsIgnoreCase(alias)) {
                    return module;
                }
            }
        }
        return null;
    }
}
