package com.example.hanukaproject.Entities;

import com.example.hanukaproject.Game.*;
import com.example.hanukaproject.Screen;
import com.example.hanukaproject.Weapons.*;

public class BaseWeapon extends BaseEntity implements Fightable {

    public BaseWeapon(String name, String icon) {
        super(name, icon);
    }


    @Override
    public void interact(Player player, BoardManager boardManager, PlayerHandler playerHandler) {
        boardManager.deleteEntity(getPosition());
        // Order matters here because deleteEntity is working based off the position of the entity!

        player.setPosition(getPosition());
        player.addWeapon(this);
    }

    @Override
    public boolean interact(Fightable opponent) {
        return false;
    }

    @Override
    public boolean stronger(Fireball weapon) {
        return false;
    }

    @Override
    public boolean stronger(Sword weapon) {
        return false;
    }

    @Override
    public boolean stronger(MagicRing weapon) {
        return false;
    }
}
