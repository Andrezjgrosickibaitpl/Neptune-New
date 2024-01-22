package me.neptune.api.util.friend;

import me.neptune.api.interfaces.Labeled;

public class Friend implements Labeled {
    private final String label;

    public Friend(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}