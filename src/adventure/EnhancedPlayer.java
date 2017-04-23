package adventure;

import java.util.List;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 *  An enhanced player class to support extra methods.
 *
 *  @author Lisa Balogh
 *  @version Mar 30, 2017
 */
public class EnhancedPlayer
    extends Player
{
    private boolean hasEatenPeanutButter;
    private boolean hasWon;

    private List<Item> inventory;

    // ----------------------------------------------------------
    /**
     * Create a new EnhancedPlayer object.
     */
    public EnhancedPlayer()
    {
        super();
        inventory = new ArrayList<>();
        hasEatenPeanutButter = false;
        hasWon = false;
    }

    // ----------------------------------------------------------
    /**
     * Adds an item to the inventory.
     * @param item the item to be added.
     */
    public void addItem(Item item)
    {
        {
            inventory.add(item);
        }
    }

    // ----------------------------------------------------------
    /**
     * Determines whether the player has an item.
     * @param itemName the item
     * @return whether the player has the item.
     */
    public boolean hasItem(String itemName)
    {
        for (Item item : inventory)
        {
            if (itemName.equals(item.getName()))
            {
                return true;
            }
        }
        return false;
    }

    // ----------------------------------------------------------
    /**
     * A getter for an item in the inventory.
     * @param itemName the item to retrieve.
     * @return the item.
     */
    public Item getItem(String itemName)
    {
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

    // ----------------------------------------------------------
    /**
     * A getter for the current size of the inventory.
     * @return the current size of the inventory.
     */
    public int getInventorySize()
    {
        int inventorySize = inventory.size();
        return inventorySize;
    }

    // ----------------------------------------------------------
    /**
     * A getter for the current inventory weight.
     * @return the current inventory weight.
     */
    public int getInventoryWeight()
    {
        int inventoryWeight = 0;
        for (Item item : inventory)
        {
            inventoryWeight = inventoryWeight + item.getWeight();
        }
        return inventoryWeight;
    }

    // ----------------------------------------------------------
    /**
     * A method to remove an item from the inventory.
     * @param item the item to be removed.
     */
    public void removeItem(Item item)
    {
        inventory.remove(item);
    }

    // ----------------------------------------------------------
    /**
     * A method to determine whether the peanut-butter has been eaten.
     * @return whether the peanut-butter has been eaten.
     */
    public boolean hasEatenPeanutButter()
    {
        return hasEatenPeanutButter;
    }

    // ----------------------------------------------------------
    /**
     * A setter to indicate that the peanut-butter has been eaten.
     */
    public void setPeanutButter()
    {
        hasEatenPeanutButter = true;
    }

    // ----------------------------------------------------------
    /**
     * A method to determine whether the player has won.
     * @return whether the player has won.
     */
    public boolean hasWon()
    {
        return hasWon;
    }

    // ----------------------------------------------------------
    /**
     * A setter to indicate that the player has won.
     */
    public void sethasWon()
    {
        hasWon = true;
    }

    // ----------------------------------------------------------
    /**
     * A method to print out a list of the entire inventory.
     * @return a list of the current inventory
     */
    public String listInventory()
    {
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
