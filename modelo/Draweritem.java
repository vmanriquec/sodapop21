package com.food.sistemas.sodapopapp.modelo;

/**
 * Created by USUARIO on 08/02/2016.
 */
public class Draweritem {
    private String name;
    private int iconId;

    public Draweritem(String name, int iconId) {
        this.name = name;
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
