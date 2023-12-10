package com.example.hanukaproject.Game;

import com.example.hanukaproject.Controller;
import com.example.hanukaproject.Entities.Player;
import com.example.hanukaproject.Screen;

public class PlayerHandler {

    private final String[] icons = {
            "😀", "😁", "😂", "🤣", "😃", "😄", "😅", "😆", "😇", "😉",
            "😊", "😋", "😍", "😘", "😗", "😙", "😚", "☺️", "🙂", "🤗",
            "🤔", "😐", "😑", "😶", "🙄", "😏", "😣", "😥", "😮", "🤐",
            "😯", "😪", "😫", "😴", "😌", "😛", "😜", "😝", "🤤", "😒",
            "😓", "😔", "😕", "🙃", "🤑", "😲", "😷", "🤒", "🤕", "🤢",
            "🤧", "😇", "🥴", "🥵", "🥶", "🥳", "🤩", "🤯",
            "🧐", "🤨", "🤬", "🤭"
    };

    private Player[] players;
    private int currentPlayerIndex;

    public PlayerHandler() { }

    public void initialize(){
        int playerAmount = Controller.askForPlayerAmount();
        initializePlayersArray(playerAmount);
    }

    public int getEmptyPlayerPosition(){
        for (int i = 0; i < players.length; i++) {
            if(players[i] == null){
                return i;
            }
        }
        return -1;
    }

    public void addPlayer(Player player) {
        int emptyPosition = getEmptyPlayerPosition();
        if(emptyPosition != -1) {
            players[emptyPosition] = player;
        }
    }

    public void addPlayer(String name, String icon) {
        Player player = new Player(name, icon);
        addPlayer(player);
    }

    public Player getNextPlayer(){
        if(this.players.length == 0) return null;
        if(currentPlayerIndex == this.players.length - 1) {
            currentPlayerIndex = 0;
            return players[currentPlayerIndex]; // returns the first player in the array
        } // Reset the circle
        currentPlayerIndex++;
        return this.players[currentPlayerIndex];
    }

    public Player getCurrentPlayer(){
        return this.players[currentPlayerIndex];
    }

    public void removePlayer(Player unwantedPlayer) {

        if(this.players.length == 0) return;
        Player[] playerCopy = new Player[this.players.length - 1];

        int k = 0;
        for (Player player : players) {
            if (!player.equals(unwantedPlayer)) {
                playerCopy[k] = player;
                k++;
            }
        }
        players = playerCopy;

    }

    public int getPlayerAmount(){
        return this.players.length;
    }


    public void initializePlayersArray(int playerAmount) {
        players = new Player[playerAmount];
        for (int i = 0; i < playerAmount; i++) {
            String playerName = Controller.askForPlayerName();

            Player newPlayer = new Player(playerName, getRandomIcon());
            addPlayer(newPlayer);
        }
    }

    public boolean isGameOver(){
        return getPlayerAmount() <= 1;
    }

    private String getRandomIcon(){
        String randomIcon;
        do{
            randomIcon = icons[(int)(Math.random() * icons.length)];

        }while(isIconTaken(randomIcon));
        return randomIcon;
    }

    private boolean isIconTaken(String icon){

        for(Player player : players){
            if(player != null && player.getIcon().equals(icon))
                return true;
        }
        return false;
    }

    public void updateCurrentPlayerIndex(Player neededPlayer){
        for (int i = 0; i < players.length; i++) {
            if(players[i].equals(neededPlayer)){
                currentPlayerIndex = i;
            }
        }

        currentPlayerIndex--;

    }

    public Player[] getPlayers() {
        return players;
    }



}
