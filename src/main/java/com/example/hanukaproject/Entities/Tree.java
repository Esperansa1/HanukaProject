package com.example.hanukaproject.Entities;

import com.example.hanukaproject.Game.BoardManager;
import com.example.hanukaproject.Game.PlayerHandler;
public class Tree extends BaseEntity {

    public static final String NAME = "Tree";
    private static final String ICON = "\uD83C\uDF33";


    public Tree() {
        super(NAME, ICON);
    }


    @Override
    public void interact(Player player, BoardManager boardManager, PlayerHandler playerHandler) {

    }
}
