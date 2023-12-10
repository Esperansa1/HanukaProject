package com.example.hanukaproject;

import javafx.scene.paint.Color;

public enum ScreenColors {
    Grid(Color.GRAY),
    CANVAS(Color.GHOSTWHITE),
    Entity(Color.BLACK),
    Highlighted(Color.ORANGERED);

    ScreenColors(Color color) {
        this.color = color;
    }

    private Color color;

    public Color getColor(){
        return color;
    }


}
