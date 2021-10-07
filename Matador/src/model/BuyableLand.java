package model;

public class BuyableLand extends Land {
    private final int rent;
    private final int price;
    private final String name;
    private Player owner = null;
    private final boolean shipping;

    public BuyableLand(int position, int rent, int price, String name, boolean shipping) {
        super(position);
        this.rent = rent;
        this.price = price;
        this.name = name;
        this.shipping = shipping;
    }

    public boolean hasOwner() {
        return this.owner != null;
    }

    public boolean buyLand(Player player) {
        boolean bought = player.takeMoney(this.price);

        if(bought)
            this.owner = player;

        return bought;
    }

    public boolean chargeRent(Player player) { return player.takeMoney(this.rent); }

    public boolean chargeShippingRent(Player player, int roll) { return player.takeMoney(this.rent * roll); }

    public String getName() { return this.name; }

    public Player getOwner() { return this.owner; }

    public boolean isShipping() { return this.shipping; }
}
