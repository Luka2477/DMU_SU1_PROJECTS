package model;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class MakeBoardGUI {
    public static void drawBoard(GameGUI game) {
        Pane pane = game.getPane();
        Group group = game.getGroup();
        ButtonArray buttons = game.getButtons();
        Board board = game.getBoard();

        double width = pane.getPrefWidth();
        double height = pane.getPrefHeight();

        int boardSize = board.getSize();
        double tilesPerSide = boardSize / 4.0;
        double landSize = width / tilesPerSide;

        ArrayList<Double> buttonX = new ArrayList<>();
        ArrayList<Double> buttonY = new ArrayList<>();
        for(int j=0; j<tilesPerSide; j++) {
            buttonX.add(landSize * j);
            buttonY.add(height - landSize);
        }
        for(int j=2; j<tilesPerSide; j++) {
            buttonX.add(width - landSize);
            buttonY.add(height - landSize * j);
        }
        for(int j=1; j<=tilesPerSide; j++) {
            buttonX.add(width - landSize * j);
            buttonY.add(0.0);
        }
        for(int j=1; j<tilesPerSide-1; j++) {
            buttonX.add(0.0);
            buttonY.add(landSize * j);
        }

        for(int i=0; i<boardSize-4; i++) {
            String text;
            int finalI = i;
            Button button = new Button();

            if(i == 0) {
                text = "Start";
                button.setDisable(true);
            } else if(board.getLand(i) instanceof BuyableLand)
                text = ((BuyableLand) board.getLand(i)).getName();
            else
                text = Integer.toString(i);

            button.setText(text);
            button.setLayoutX(buttonX.get(i));
            button.setLayoutY(buttonY.get(i));
            button.setPrefSize(landSize, landSize);
            button.setFont(new Font(width / 50));
            button.setWrapText(true);
            button.setOnAction(actionEvent -> MakeBoardGUI.handleClickedLand(game, finalI));
            group.getChildren().add(button);
            buttons.add(text, button);
        }
    }

    public static void handleClickedLand(GameGUI game, int position) {
        Pane pane = game.getPane();
        Group group = game.getGroup();
        ButtonArray buttons = game.getButtons();
        Board board = game.getBoard();

        double width = pane.getPrefWidth();
        double height = pane.getPrefHeight();


    }
}
