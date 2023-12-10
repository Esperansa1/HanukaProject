package com.example.hanukaproject.Weapons;


import com.example.hanukaproject.Entities.BaseWeapon;
import com.example.hanukaproject.Game.Fightable;

public class Fireball extends BaseWeapon {

    public static final String NAME = "Fireball";
    private static final String ICON = "\uD83D\uDD25";

    public Fireball() {
        super(NAME, ICON);
    }


    @Override
    public boolean interact(Fightable opponent) {
        return opponent.stronger(this);
    }

    @Override
    public boolean stronger(Fireball weapon) {
        return Math.random() > 0.5; // Same weapon so random winner
    }

    public boolean stronger(Sword weapon) {
        return true;
    }

    public boolean stronger(MagicRing weapon) {
        return false;
    }

}
