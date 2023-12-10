package com.example.hanukaproject;

import com.example.hanukaproject.Entities.BaseEntity;
import com.example.hanukaproject.Entities.BaseWeapon;
import com.example.hanukaproject.Entities.Player;
import com.example.hanukaproject.Game.BoardManager;
import com.example.hanukaproject.Game.GameController;
import com.example.hanukaproject.Game.PlayerHandler;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

import static com.example.hanukaproject.Game.BoardManager.BOARD_SIZE;


public class Screen extends Application {

    public static Screen INSTANCE;

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    public static final int CANVAS_WIDTH = 800;

    private GraphicsContext gc;
    private VBox playerLabels;

    @Override
    public void start(Stage stage) {

        INSTANCE = this;

        Canvas canvas = new Canvas(CANVAS_WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        gc.setFill(ScreenColors.CANVAS.getColor());
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        HBox root = new HBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(canvas);

        playerLabels = new VBox(10);
        root.getChildren().add(playerLabels);

        stage.setScene(new Scene(root,WIDTH,HEIGHT));

        stage.setResizable(false);

        stage.setTitle("Hanukka Project");
        stage.show();

        new GameController();

    }



    public void highlightCurrentPlayer(BaseEntity currentPlayer){
        gc.setFill(ScreenColors.Highlighted.getColor());
        double cellSize = getCellSize();

        String icon = currentPlayer.getIcon();

        double fontSize = cellSize * 0.8;

        Text emojiText = new Text(icon);
        Font font = new Font("Segoe UI Emoji", fontSize);

        double emojiWidth = emojiText.getLayoutBounds().getWidth();
        double emojiHeight = emojiText.getLayoutBounds().getHeight();

        double x = currentPlayer.getPosition().getX() * cellSize - emojiWidth / 4;
        double y = currentPlayer.getPosition().getY() * cellSize + fontSize - emojiHeight / 8;

        gc.setFont(font);
        gc.fillText(icon, x, y);

    }

    public void drawGame(BaseEntity[][] entities, PlayerHandler playerHandler){

        gc.setFill(ScreenColors.CANVAS.getColor());
        gc.fillRect(0, 0, CANVAS_WIDTH, HEIGHT);

        drawObjects(entities);
        drawGrid();

        if(playerHandler.getPlayerAmount() == 1){
            gameOver(playerHandler.getCurrentPlayer());
        }else {
            updatePlayerLabels(playerHandler.getPlayers());
        }
        highlightCurrentPlayer(playerHandler.getCurrentPlayer());

    }

    private void drawObjects(BaseEntity[][] entities){
        for (int i = 0; i < entities.length; i++) {
            for (int j = 0; j < entities[i].length; j++) {
                BaseEntity entity = entities[i][j];
                drawEntity(entity);
            }
        }
    }

    private void drawEntity(BaseEntity entity) {
        gc.setFill(ScreenColors.Entity.getColor());

        double cellSize = getCellSize();

        String icon = entity.getIcon();

        double fontSize = cellSize * 0.8;

        Text emojiText = new Text(icon);
        Font font = new Font("Segoe UI Emoji", fontSize);

        double emojiWidth = emojiText.getLayoutBounds().getWidth();
        double emojiHeight = emojiText.getLayoutBounds().getHeight();

        double x = entity.getPosition().getX() * cellSize - emojiWidth / 4;
        double y = entity.getPosition().getY() * cellSize + fontSize - emojiHeight / 8;

        gc.setFont(font);
        gc.fillText(icon, x, y);
    }

    private double getCellSize(){
        return (double) CANVAS_WIDTH / BOARD_SIZE;
    }

    private void drawGrid() {

        gc.setStroke(ScreenColors.Grid.getColor());

        double cellSize = getCellSize();

        for (double x = 0; x <= CANVAS_WIDTH + 1; x += cellSize) {
            gc.strokeLine(x, 0, x, HEIGHT);
        }

        for (double y = 0; y <= HEIGHT + 1; y += cellSize) {
            gc.strokeLine(0, y, CANVAS_WIDTH, y);
        }
    }

    public void updatePlayerLabels(Player[] players){

        playerLabels.getChildren().clear();

        for(Player player : players) {

            BaseWeapon weapon = player.getWeapon();
            String text = player.getName() + " : " + player.getIcon();

            if(weapon != null){
                text += "\nWeapon : " + player.getWeapon();
            }
            Label playerLabel = new Label(text);

            Font font = new Font("Segoe UI Emoji", 30);
            playerLabel.setFont(font);
            playerLabels.getChildren().add(playerLabel);

        }
    }

    public void gameOver(Player player){
        playerLabels.getChildren().clear();
        Label playerLabel = new Label("GAME OVER!!!\n" + player.getName().toUpperCase() + " " + player.getIcon() + " WON");
        Font font = new Font("Segoe UI Emoji", 30);
        playerLabel.setFont(font);
        playerLabels.getChildren().add(playerLabel);
    }

    public static void main(String[] args) {
        launch(args);
    }
}