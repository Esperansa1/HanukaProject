package com.example.hanukaproject.Game;


import com.example.hanukaproject.Controller;
import com.example.hanukaproject.Entities.Player;
import com.example.hanukaproject.Entities.Position;
import com.example.hanukaproject.Screen;

public class GameController {

    private final PlayerHandler playerHandler;
    private final BoardManager boardManager;

    final int TREE_AMOUNT = 3;
    final int WEAPON_AMOUNT = 6;

    public GameController() {

        // Initializations
        playerHandler = new PlayerHandler();
        playerHandler.initialize();

        int entitiesAmount = playerHandler.getPlayerAmount() + TREE_AMOUNT + WEAPON_AMOUNT;
        this.boardManager = new BoardManager(entitiesAmount);
        this.boardManager.spawnEntities(playerHandler, TREE_AMOUNT, WEAPON_AMOUNT);
        this.boardManager.updateBoard();

        Screen.INSTANCE.drawGame(boardManager.getBoard(), playerHandler);
        runGame();
    }

    private void runGame() {
        while (!playerHandler.isGameOver()) {
            moveCurrentPlayer();
            playerHandler.getNextPlayer();
            boardManager.updateBoard();
            Screen.INSTANCE.drawGame(boardManager.getBoard(), playerHandler);
        }

        Screen.INSTANCE.gameOver(playerHandler.getCurrentPlayer());
        boardManager.updateBoard();
        Screen.INSTANCE.drawGame(boardManager.getBoard(), playerHandler);
    }

    private void moveCurrentPlayer(){
        Player currentPlayer = playerHandler.getCurrentPlayer();
        Position previousPosition = currentPlayer.getPosition();
        do {
            char wantedMovement = Controller.askForPlayerMovement();
            if(wantedMovement == ' '){
                return;
            }
            currentPlayer.attemptMove(boardManager, playerHandler, wantedMovement);
        }while(currentPlayer.getPosition().equals(previousPosition));
    }




    public BoardManager getBoardManager() {
        return this.boardManager;
    }



}
