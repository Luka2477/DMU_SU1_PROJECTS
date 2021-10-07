package model;

import java.util.ArrayList;

public class Board {
    // Defines the size of the board. Must be a multiple of 4...
    private final int size = 44;
    private final ArrayList<Land> board = new ArrayList<>();

    public Board() {
        this.init();
    }

    public void init() {
        for(int i=0; i<this.size-4; i++)
            this.board.add(new Land(i));
    }

    public int getSize() { return this.size; }

    public ArrayList<Land> getBoard() { return this.board; }

    public Land getLand(int position) { return this.board.get(position); }

    public void setLand(int position, BuyableLand land) { this.board.set(position, land); }
}
