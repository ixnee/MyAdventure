package adventure;

import java.util.List;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author iXNÈE
 *  @version Mar 30, 2017
 */
public class EnhancedPlayer
    extends Player
{
    private List<Item> inventory;

    // ----------------------------------------------------------
    /**
     * Create a new EnhancedPlayer object.
     */
    public EnhancedPlayer()
    {
        super();
        inventory = new ArrayList<>();
    }

    public void addItem(Item item) {

        inventory.add(item);
    }

    public boolean hasItem(String itemName) {
        for (Item item: inventory)
        {
            if (itemName.equals(item.getName()))
            {
                return true;
            }
        }
        return false;
    }

    public Item getItem(String itemName) {
        for (Item item : inventory)
        {
            // assert containsItem(itemName)
            if (itemName.equals(item.getName()))
            {
                return item;
            }
        }
        // hopefully we will never get here
        return null;
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public String listInventory() {
        StringBuilder sb = new StringBuilder();
        sb.append("You have ");
        String[] itemNames = new String[inventory.size()];
        if (itemNames.length > 0)
        {
            for (int i = 0; i < itemNames.length; i++)
            {
                itemNames[i] = "the " + inventory.get(i).getName();
            }
            sb.append(Message.commaSeparatedList(itemNames));
            sb.append(".");
            sb.append("<br>");
            return sb.toString();
        }
        return Message.inventoryEmptyMessage();
    }
}
