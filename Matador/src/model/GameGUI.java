package model;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class GameGUI extends Application {
    private StackPane pane;
    private Group group;

    private Board board;
    private ArrayList<Player> players = new ArrayList<>();
    private Die die = new Die();
    private Scanner scanner;
    private ButtonArray buttons = new ButtonArray();

    @Override
    public void start(Stage stage) {
        Pane root = this.initContent();
        Scene scene = new Scene(root);

        stage.setTitle("Matador");
        stage.setScene(scene);
        stage.show();
    }

    private Pane initContent() {
        this.pane = new StackPane();
        this.pane.setPrefSize(800, 800);
        this.group = new Group();
        this.pane.getChildren().add(this.group);
        this.initGame();
        return this.pane;
    }

    // ------------------------------------------------------------------------

    public Pane getPane() { return this.pane; }
    public Group getGroup() { return this.group; }
    public ButtonArray getButtons() { return this.buttons; }
    public Board getBoard() { return this.board; }

    public void clearPane() {
        this.group.getChildren().remove(0, this.group.getChildren().toArray().length);
        this.buttons.removeAll();
    }

    private void initGame() {
        this.board = new Board();

        MainMenuGUI.drawMenu(this);
    }

    public void resetBoard() { this.board.init(); }

    public void makeBoard() { this.makeBoard("Click any tile to change it");}

    public void makeBoard(String text) {
        this.clearPane();

        MakeBoardGUI.drawBoard(this, text);
    }

    public void loadBoard(String selectedItem) {
        this.clearPane();


    }
}