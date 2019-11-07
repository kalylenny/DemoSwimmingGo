package com.example.swimminggo.singleton;

import com.example.swimminggo.models.Style;

public class CurrentStyle {
    private Style style;
    private static CurrentStyle ourInstance;

    public static CurrentStyle getInstance() {
        if (ourInstance == null)
            ourInstance = new CurrentStyle();
        return ourInstance;
    }

    private CurrentStyle() {
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
