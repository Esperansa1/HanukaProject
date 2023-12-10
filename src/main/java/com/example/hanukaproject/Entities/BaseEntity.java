package com.example.hanukaproject.Entities;


import com.example.hanukaproject.Game.BoardManager;
import com.example.hanukaproject.Game.PlayerHandler;

public abstract class BaseEntity {

    private String name;
    private String icon;
    private Position position;

    public BaseEntity(String name) {
        this.name = name;
    }

    public BaseEntity(String name, String icon) {
        this.name = name;
        this.icon = icon;

    }

    public BaseEntity(Position position, String name, String icon) {
        this.name = name;
        this.icon = icon;
        this.position = position;

    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract void interact(Player player, BoardManager boardManager, PlayerHandler playerHandler);
}
