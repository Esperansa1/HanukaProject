package com.example.hanukaproject;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

import java.util.List;
import java.util.Optional;

public class Controller {


    @FXML
    public static int askForPlayerAmount() {
        List<String> choices = List.of("2 Players", "3 Players", "4 Players", "5 Players");

        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Select Number of Players");
        dialog.setHeaderText(null);
        dialog.setContentText("Choose the number of players:");

        Optional<String> result = dialog.showAndWait();
        return result.map(choices::indexOf).orElse(0) + 2;
    }

    public static String askForPlayerName(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Player name");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter the player's name:");

        Optional<String> result = dialog.showAndWait();
        return result.orElse("");
    }

    public static char askForPlayerMovement(){

        List<String> choices = List.of("Left", "Right", "Up", "Down");

        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Choose directions");
        dialog.setHeaderText(null);
        dialog.setContentText("Choose direction to move:");

        dialog.setX(50);
        dialog.setY(50);
        Optional<String> result = dialog.showAndWait();

        return result.orElse(" ").charAt(0);

    }


}

