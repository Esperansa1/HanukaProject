package com.example.hanukaproject.Weapons;


import com.example.hanukaproject.Entities.BaseWeapon;
import com.example.hanukaproject.Game.Fightable;

public class Sword extends BaseWeapon {
    public static final String NAME = "Sword";
    private static final String ICON = "⚔";


    public Sword() {
        super(NAME, ICON);
    }

    @Override
    public boolean interact(Fightable opponent) {
        return opponent.stronger(this);
    }

    @Override
    public boolean stronger(Fireball weapon) {
        return false;
    }

    @Override
    public boolean stronger(Sword weapon) {
        return Math.random() > 0.5;
    }

    @Override
    public boolean stronger(MagicRing weapon) {
        return true;
    }


}
