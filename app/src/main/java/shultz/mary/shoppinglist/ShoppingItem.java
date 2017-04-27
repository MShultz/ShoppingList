package shultz.mary.shoppinglist;


/**
 * Created by Mary on 4/26/2017.
 */

public class ShoppingItem {
    private String dateCreated, dateCompleted, itemName;

    public ShoppingItem(String itemName, String dateCreated, String dateCompleted) {
        this.setItemName(itemName);
        this.setDateCreated(dateCreated);
        this.setDateCompleted(dateCompleted);
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return getItemName() + "\t" + getDateCreated() + "\t" + (getDateCompleted() == null? "Not Complete" : getDateCompleted());
    }
}
