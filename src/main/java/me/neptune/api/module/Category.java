package me.neptune.api.module;

public enum Category {
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    RENDER("Render"),
    PLAYER("Player"),
    MISC("Misc"),
    EXPLOIT("Exploit"),
    OTHER("Other");

    final String category;

    Category(String category) {
        this.category = category;
    }

    public String getLabel() {
        return category;
    }
}
