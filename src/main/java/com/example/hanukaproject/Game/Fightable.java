package com.example.hanukaproject.Game;


import com.example.hanukaproject.Weapons.*;

public interface Fightable {

    boolean interact(Fightable opponent);

    boolean stronger(Fireball weapon);
    boolean stronger(Sword weapon);
    boolean stronger(MagicRing weapon);
}
