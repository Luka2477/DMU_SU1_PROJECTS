package model;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class GameGUI extends Application {
    private Pane pane;
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
        this.pane = new Pane();
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

    private void clearPane() {
        this.group.getChildren().remove(0, this.group.getChildren().toArray().length);
        this.buttons.removeAll();
    }

    private void initGame() {
        this.board = new Board();

        MainMenuGUI.drawMenu(this);
    }

    public void makeBoard() {
        this.clearPane();

        MakeBoardGUI.drawBoard(this);
    }

    public void loadBoard() {
        this.clearPane();
    }
}