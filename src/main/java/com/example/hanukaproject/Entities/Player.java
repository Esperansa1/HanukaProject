package com.example.hanukaproject.Entities;

import com.example.hanukaproject.Game.BoardManager;
import com.example.hanukaproject.Game.PlayerHandler;

import java.util.LinkedList;
import java.util.Queue;

public class Player extends BaseEntity {


    private final Queue<BaseWeapon> weaponQueue = new LinkedList<>() ;


    public Player(String name) {
        super(name);
    }
    public Player(String name, String icon) {
        super(name, icon);
    }

    @Override
    public String toString() {
        return "Player "+ super.getName() + " Icon: " + getIcon();
    }

    public void addWeapon(BaseWeapon weapon){
        weaponQueue.add(weapon);
    }

    public BaseWeapon getWeapon(){
        if(weaponQueue.isEmpty()) return null;
        return weaponQueue.element();
    }

    public void attemptMove(BoardManager boardManager, PlayerHandler playerHandler, char wantedMovement){
        int x = 0;
        int y = 0;
        switch (Character.toUpperCase(wantedMovement)) {
            case 'U' -> y = -1;
            case 'L' -> x = -1;
            case 'D' -> y = 1;
            case 'R' -> x = 1;
        };
        Position currentPosition = getPosition();
        Position newPosition = new Position(currentPosition.getX() + x, currentPosition.getY() + y);
        attemptMove(boardManager, playerHandler, newPosition);

    }


    private void attemptMove(BoardManager boardManager,PlayerHandler playerHandler, Position newPosition){
        if(!boardManager.isInBoard(newPosition)) return;
        boardManager.getEntity(newPosition).interact(this, boardManager, playerHandler);
    }

    @Override
    public void interact(Player other, BoardManager boardManager, PlayerHandler playerHandler) {
        BaseWeapon myWeapon = getWeapon();
        BaseWeapon otherWeapon= other.getWeapon();
        if(myWeapon == null && otherWeapon == null) {
            return;
        }
        else if(otherWeapon == null){
            thisPlayerWins(other, boardManager, playerHandler);
        }
        else if(myWeapon == null) {
            thisPlayerLose(other, boardManager, playerHandler);

        }
        else if(myWeapon.interact(otherWeapon)) {
            thisPlayerLose(other, boardManager, playerHandler);
        }
        else {
            thisPlayerWins(other, boardManager, playerHandler);
        }
    }

    private void thisPlayerWins(Player other, BoardManager boardManager, PlayerHandler playerHandler){

        Player supposedNextPlayer = playerHandler.getNextPlayer();

        weaponQueue.remove();
        if(other.getWeapon()!= null)
            addWeapon(other.getWeapon());
        playerHandler.removePlayer(other);

        playerHandler.updateCurrentPlayerIndex(supposedNextPlayer);

        boardManager.deleteEntity(other.getPosition());
        other.setPosition(getPosition());

    }

    private void thisPlayerLose(Player other, BoardManager boardManager, PlayerHandler playerHandler){
        Player supposedNextPlayer = playerHandler.getNextPlayer();

        other.weaponQueue.remove();
        if(getWeapon() != null)
            other.addWeapon(getWeapon());
        playerHandler.removePlayer(this);
        playerHandler.updateCurrentPlayerIndex(supposedNextPlayer);

        boardManager.deleteEntity(getPosition());
        other.setPosition(getPosition());


    }


}
