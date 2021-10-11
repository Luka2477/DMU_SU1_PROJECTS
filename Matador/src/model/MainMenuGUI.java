package model;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MainMenuGUI {
    public static void drawMenu(GameGUI game) {
        Pane pane = game.getPane();
        Group group = game.getGroup();
        ButtonArray buttons = game.getButtons();

        double width = pane.getPrefWidth();
        double height = pane.getPrefHeight();

        double headerFontSize = width / 25;

        Text header = new Text("Welcome to Matador");
        header.setX(width / 2);
        header.setY(height / 2 - height / 20);
        header.setFont(new Font(headerFontSize));
        header.setTextAlignment(TextAlignment.CENTER);
        group.getChildren().add(header);

        Button makeBoard = new Button("Make a board");
        makeBoard.setLayoutX(width / 2 + width / 10);
        makeBoard.setLayoutY(height / 2);
        makeBoard.setPrefWidth(width / 5);
        makeBoard.setMaxHeight(height / 15);
        makeBoard.setFont(new Font(width / 50));
        makeBoard.setOnAction(actionEvent -> game.makeBoard());
        group.getChildren().add(makeBoard);
        buttons.add("makeBoard", makeBoard);

        ListView<String> boards = new ListView<>();
        try (Stream<Path> paths = Files.walk(Paths.get(System.getProperty("user.dir") + "/matador/boards/"))) {
            paths.filter(Files::isRegularFile).forEach(file -> boards.getItems().add(file.getFileName().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        boards.setLayoutX(width / 2);
        boards.setLayoutY(height / 2 + height / 20 * 2);
        boards.setPrefWidth(width / 3);
        boards.setPrefHeight(height / 5);
        group.getChildren().add(boards);

        Button loadBoard = new Button("Load a board");
        loadBoard.setLayoutX(width / 2 + width / 10);
        loadBoard.setLayoutY(height / 2 + height / 20 * 6);
        loadBoard.setPrefWidth(width / 5);
        loadBoard.setMaxHeight(height / 15);
        loadBoard.setFont(new Font(width / 50));
        loadBoard.setOnAction(actionEvent -> game.loadBoard(boards.getSelectionModel().getSelectedItem()));
        group.getChildren().add(loadBoard);
        buttons.add("makeBoard", loadBoard);
    }
}
