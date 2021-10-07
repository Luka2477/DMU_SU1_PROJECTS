package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class SaveBoard {
    public static void saveBoard(GameGUI game, String name) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(String.format("%s/matador/boards/%s.json", System.getProperty("user.dir"), name))) {
            gson.toJson(game.getBoard(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
