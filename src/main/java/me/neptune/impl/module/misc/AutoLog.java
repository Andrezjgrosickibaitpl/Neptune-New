package me.neptune.impl.module.misc;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;

public class AutoLog extends Module {
    public AutoLog() {
        super("AutoLog", new String[]{"AutoLog"}, "Automatically logs when you have a certain amount of totems", Category.MISC);
    }
}
