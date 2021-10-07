package model;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.ArrayList;

public class MakeBoardGUI {
    public static void drawBoard(GameGUI game, String headerText) {
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

        double headerFontSize = width / 25;
        Text header = new Text(headerText);
        header.setX(width / 2 - headerFontSize * 5);
        header.setY(height / 2 - height / 5);
        header.setFont(new Font(headerFontSize));
        header.setTextAlignment(TextAlignment.CENTER);
        group.getChildren().add(header);

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
            button.setOnAction(actionEvent -> {
                game.makeBoard(String.format("You are changing land %s", text));
                MakeBoardGUI.handleClickedLand(game, finalI);
            });
            group.getChildren().add(button);
            buttons.add(text, button);
        }

        Button saveBoard = new Button("Save board");
        saveBoard.setLayoutX(landSize + 10);
        saveBoard.setLayoutY(height - landSize - height / 15 - 10);
        saveBoard.setPrefWidth(width / 5);
        saveBoard.setPrefHeight(height / 15);
        saveBoard.setFont(new Font(width / 50));
        saveBoard.setOnAction(actionEvent -> {
            game.makeBoard("Save board");
            MakeBoardGUI.handleSaveBoard(game);
        });
        group.getChildren().add(saveBoard);
        buttons.add("saveBoard", saveBoard);

        Button resetBoard = new Button("Reset board");
        resetBoard.setLayoutX(width - landSize - width / 5 - 10);
        resetBoard.setLayoutY(height - landSize - height / 15 - 10);
        resetBoard.setPrefWidth(width / 5);
        resetBoard.setPrefHeight(height / 15);
        resetBoard.setFont(new Font(width / 50));
        resetBoard.setOnAction(actionEvent -> {
            game.resetBoard();
            game.makeBoard();
        });
        group.getChildren().add(resetBoard);
        buttons.add("resetBoard", resetBoard);
    }

    public static void handleClickedLand(GameGUI game, int position) {
        Pane pane = game.getPane();
        Group group = game.getGroup();
        ButtonArray buttons = game.getButtons();

        double width = pane.getPrefWidth();
        double height = pane.getPrefHeight();

        double headerY = height / 2 - height / 5;
        double offsetY = height / 20;
        double nameY = headerY + offsetY;
        double priceY = headerY + offsetY * 2;
        double rentY = headerY + offsetY * 3;

        Label labelName = new Label("Name: ");
        labelName.setLayoutX(width / 2 - width / 10);
        labelName.setLayoutY(nameY);
        labelName.setPrefWidth(width / 5);
        labelName.setFont(new Font(width / 50));
        labelName.setTextAlignment(TextAlignment.RIGHT);
        group.getChildren().add(labelName);

        TextField name = new TextField();
        name.setLayoutX(width / 2);
        name.setLayoutY(nameY);
        name.setPrefWidth(width / 5);
        group.getChildren().add(name);

        Label labelPrice = new Label("Price: ");
        labelPrice.setLayoutX(width / 2 - width / 10);
        labelPrice.setLayoutY(priceY);
        labelPrice.setPrefWidth(width / 5);
        labelPrice.setFont(new Font(width / 50));
        labelPrice.setTextAlignment(TextAlignment.RIGHT);
        group.getChildren().add(labelPrice);

        NumericTextField price = new NumericTextField();
        price.setLayoutX(width / 2);
        price.setLayoutY(priceY);
        price.setPrefWidth(width / 5);
        group.getChildren().add(price);

        Label labelRent = new Label("Rent: ");
        labelRent.setLayoutX(width / 2 - width / 10);
        labelRent.setLayoutY(rentY);
        labelRent.setPrefWidth(width / 5);
        labelRent.setFont(new Font(width / 50));
        labelRent.setTextAlignment(TextAlignment.RIGHT);
        group.getChildren().add(labelRent);

        NumericTextField rent = new NumericTextField();
        rent.setLayoutX(width / 2);
        rent.setLayoutY(rentY);
        rent.setPrefWidth(width / 5);
        group.getChildren().add(rent);

        CheckBox isShipping = new CheckBox("Should the land be shipping land?");
        isShipping.setLayoutX(width / 2 - width / 10);
        isShipping.setLayoutY(headerY + offsetY * 4);
        isShipping.setFont(new Font(width / 60));
        group.getChildren().add(isShipping);

        Button save = new Button("Save changes");
        save.setLayoutX(width / 2);
        save.setLayoutY(headerY + offsetY * 5);
        save.setPrefWidth(width / 5);
        save.setPrefHeight(height / 15);
        save.setFont(new Font(width / 50));
        save.setOnAction(actionEvent -> MakeBoardGUI.handleSaveChanges(
                game,
                position,
                name.getText(),
                Integer.parseInt(price.getText()),
                Integer.parseInt(rent.getText()),
                isShipping.isSelected()
        ));
        group.getChildren().add(save);
        buttons.add("saveChanges", save);
    }

    public static void handleSaveChanges(GameGUI game, int position, String name, int price, int rent, boolean isShipping) {
        Board board = game.getBoard();

        BuyableLand buyableLand = new BuyableLand(position, rent, price, name, isShipping);
        board.setLand(position, buyableLand);

        game.makeBoard(String.format("Saved changes to %s", name));
    }

    public static void handleSaveBoard(GameGUI game) {
        Pane pane = game.getPane();
        Group group = game.getGroup();
        ButtonArray buttons = game.getButtons();

        double width = pane.getPrefWidth();
        double height = pane.getPrefHeight();

        double headerY = height / 2 - height / 5;
        double offsetY = height / 20;

        Label labelName = new Label("Board name: ");
        labelName.setLayoutX(width / 2 - width / 8);
        labelName.setLayoutY(headerY + offsetY);
        labelName.setPrefWidth(width / 5);
        labelName.setFont(new Font(width / 50));
        labelName.setTextAlignment(TextAlignment.RIGHT);
        group.getChildren().add(labelName);

        TextField name = new TextField();
        name.setLayoutX(width / 2);
        name.setLayoutY(headerY + offsetY);
        name.setPrefWidth(width / 5);
        group.getChildren().add(name);

        Button save = new Button("Confirm");
        save.setLayoutX(width / 2);
        save.setLayoutY(headerY + offsetY * 2);
        save.setPrefWidth(width / 5);
        save.setPrefHeight(height / 15);
        save.setFont(new Font(width / 50));
        save.setOnAction(actionEvent -> {
            game.clearPane();
            SaveBoard.saveBoard(game, name.getText());
            MainMenuGUI.drawMenu(game);
        });
        group.getChildren().add(save);
        buttons.add("saveChanges", save);
    }
}
