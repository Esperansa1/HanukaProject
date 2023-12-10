package com.example.hanukaproject.Entities;

import com.example.hanukaproject.Game.BoardManager;
import com.example.hanukaproject.Game.PlayerHandler;

public class EmptyEntity extends BaseEntity {

    public static final String NAME = "";
    private static final String ICON = "";


    public EmptyEntity(Position position) {
        super(position, NAME, ICON);
    }


    @Override
    public void interact(Player player, BoardManager boardManager, PlayerHandler playerHandler) {
        player.setPosition(getPosition());
    }
}
