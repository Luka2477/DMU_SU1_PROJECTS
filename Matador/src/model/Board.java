package model;

import java.util.ArrayList;

public class Board {
    // Defines the size of the board. Must be according to the pattern 3, 7, 11, 15...
    private final int size;
    private final ArrayList<Land> board = new ArrayList<>();

    public Board(int size) {
        this.size = size;
        this.init();
    }

    private void init() {
        for(int i=0; i<this.size; i++)
            this.board.add(new Land(i));
    }

    public int getSize() { return this.size; }

    public Land getLand(int position) { return this.board.get(position); }

    public void setLand(int position, BuyableLand land) {
        this.board.remove(position);
        this.board.set(position, land);
    }
}
