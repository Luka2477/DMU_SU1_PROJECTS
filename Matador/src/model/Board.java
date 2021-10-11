package model;

import java.util.ArrayList;
import java.util.Comparator;

public class Board {
    // Defines the size of the board. Must be a multiple of 4...
    private final int size = 44;
    private final ArrayList<Land> lands = new ArrayList<>();
    private final ArrayList<BuyableLand> buyableLands = new ArrayList<>();
    private ArrayList<Land> board;

    public Board() {
        this.init();
        this.convertBoard();
    }

    public void convertBoard() {
        this.board = new ArrayList<>(this.lands);
        this.board.addAll(this.buyableLands);
        this.board.sort(Comparator.comparing(Land::getPosition));
    }

    public void init() {
        for(int i=0; i<this.size-4; i++)
            this.lands.add(new Land(i));
    }

    public int getSize() { return this.size; }

    public ArrayList<Land> getBoard() { return this.board; }

    public Land getLand(int position) { return this.board.get(position); }

    public void setLand(int position, BuyableLand land) {
        this.lands.removeIf(element -> (element.getPosition() == position));
        this.buyableLands.add(land);
        this.convertBoard();
    }
}
