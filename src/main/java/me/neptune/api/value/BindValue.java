package me.neptune.api.value;

import me.neptune.api.value.util.KeyBind;

public class BindValue extends Value<KeyBind> {
    public BindValue(String[] aliases, String description, KeyBind value) {
        super(aliases, description, value);
    }
}
