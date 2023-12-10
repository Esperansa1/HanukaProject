package com.example.hanukaproject.Game;


import com.example.hanukaproject.Entities.*;
import com.example.hanukaproject.Weapons.*;

public class BoardManager {

    public static final int BOARD_SIZE = 10;
    private BaseEntity[][] board;
    private BaseEntity[] entities;

    public BoardManager(int entitiesAmount) {
        this.board = new BaseEntity[BOARD_SIZE][BOARD_SIZE];
        entities = new BaseEntity[entitiesAmount];
    }

    public void updateBoard(){

        // Fill the board with empty entities
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new EmptyEntity(new Position(i, j));
            }
        }

        // Add the entities to the board
        for (BaseEntity entity : entities) {
            if(entity.getPosition().equals(new Position(-1, -1)))
                continue;
            addEntity(entity, entity.getPosition());
        }
    }

    public BaseEntity[][] getBoard() {
        return board;
    }

    public BaseEntity[] getEntities() {
        return entities;
    }


    public BaseEntity getEntity(Position position){
        return board[position.getX()][position.getY()];
    }

    public void deleteEntity(Position position) {
        board[position.getX()][position.getY()] = new EmptyEntity(position);
        entities[findEntity(position)] = new EmptyEntity(new Position(-1, -1));
    }

    public int findEntity(Position position){
        for (int i = 0; i < getEmptyIndex(); i++) {
            if(entities[i].getPosition().equals(position)){
                return i;
            }
        }
        return -1;
    }

    public boolean isInBoard(Position position)
    {
        return position.getX() >= 0 && position.getX() < BOARD_SIZE && position.getY() >= 0 && position.getY() < BOARD_SIZE;
    }


    private boolean isEmpty(Position position) {
        return board[position.getX()][position.getY()].getName().equals("Empty");
    }

    public void addEntity(BaseEntity entity, Position position){
        entity.setPosition(position);
        board[position.getX()][position.getY()] = entity;
        if(getEmptyIndex() == entities.length) return;
        entities[getEmptyIndex()] = entity;
    }

    public void spawnEntities(PlayerHandler playerHandler, int treeAmount, int weaponAmount) {
        generatePlayers(playerHandler);
        generateTrees(treeAmount);
        generateWeapons(weaponAmount);
    }

    private void generatePlayers(PlayerHandler playerHandler){
        int y = 0;
        for (int i = 0; i < playerHandler.getPlayerAmount(); i++) {
            addEntity(playerHandler.getNextPlayer(), generatePosition());
            playerHandler.getCurrentPlayer().setPosition(generatePosition());
        }
    }

    private void generateTrees(int treeAmount){
        for (int i = 0; i < treeAmount; i++) {
            addEntity(new Tree(), generatePosition());
        }
    }

    private void generateWeapons(int weaponAmount){
        for (int i = 0; i < weaponAmount; i++) {
            addEntity(generateWeapon(), generatePosition());
        }
    }


    public boolean isPositionTaken(Position position){
        return findEntity(position) != -1; // returns true if position is taken
    }



    private Position generatePosition(){
        Position generatedPosition;
        do {
            int xPos = (int)(Math.random() * 10);
            int yPos = (int)(Math.random() * 10);

            generatedPosition = new Position(xPos, yPos);

        }while(isPositionTaken(generatedPosition));
        return generatedPosition;
    }

    private int getEmptyIndex(){
        for (int i = 0; i < entities.length; i++) {
            if(entities[i] == null) return i;
        }
        return entities.length; // End of array
    }

    private BaseWeapon generateWeapon(){
        BaseWeapon[] weaponOption = {new Fireball(), new MagicRing(), new Sword()};
        return weaponOption[(int)(Math.random() * weaponOption.length)];
    }



}
