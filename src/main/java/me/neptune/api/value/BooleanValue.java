package me.neptune.api.value;

public class BooleanValue extends Value<Boolean> {
    public BooleanValue(String name, boolean value) {
        super(name, value);
    }

    public BooleanValue(String[] aliases, boolean value) {
        super(aliases, value);
    }
}
