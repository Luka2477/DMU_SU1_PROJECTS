package model;

public class Player {
    private final String name;
    private int position;
    private double money = 20000;

    public Player(String name) { this.name = name; }

    public String getName() { return this.name; }

    public int getPosition() { return this.position; }

    public void setPosition(int position) { this.position = position; }

    public double getMoney() { return this.money; }

    public void addMoney(double amount) { this.money += amount; }

    public boolean takeMoney(double amount) {
        this.money -= amount;
        return this.money > 0;
    }
}
