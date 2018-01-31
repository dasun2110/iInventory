package ithub.iinventory;

/**
 * Created by Akila Devinda on 1/31/2018.
 */

public class ExmapleItem {
    private String itemName ;
    private String itemPrice;

    public ExmapleItem(String itemName, String itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }
}
