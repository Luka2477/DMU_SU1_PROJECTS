package model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class Game extends Application {
    private GraphicsContext gc;

    private Board board;
    private ArrayList<Player> players = new ArrayList<>();
    private Die die = new Die();
    private Scanner scanner;

    @Override
    public void start(Stage stage) {
        GridPane root = this.initContent();
        Scene scene = new Scene(root);

        stage.setTitle("Make Board");
        stage.setScene(scene);
        stage.show();
    }

    private GridPane initContent() {
        GridPane pane = new GridPane();
        Canvas canvas = new Canvas(800, 800);
        this.gc = canvas.getGraphicsContext2D();
        pane.add(canvas, 0, 0);
        this.initGame();
        return pane;
    }

    // ------------------------------------------------------------------------

    private void initGame() {

    }
}