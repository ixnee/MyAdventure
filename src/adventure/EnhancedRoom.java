package adventure;

import java.util.List;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 *  An enhanced room class to support extra methods.
 *
 *  @author Lisa Balogh
 *  @version Mar 30, 2017
 */
public class EnhancedRoom
    extends Room
{
    private List<Item> contents;

    // ----------------------------------------------------------
    /**
     * Create a new EnhancedRoom object.
     * @param description the description of the item.
     */
    public EnhancedRoom(String description)
    {
        super(description);
        contents = new ArrayList<>();
    }

    // ----------------------------------------------------------
    /**
     * Adds a new item to the room.
     * @param item the item to be added.
     */
    public void addItem(Item item) {
        contents.add(item);
    }

    // ----------------------------------------------------------
    /**
     * A method that determines whether a room contains an item.
     * @param itemName the name of the item.
     * @return whether the room contains the item.
     */
    public boolean containsItem(String itemName) {
        for (Item item: contents)
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
     * A getter method for an item in the room.
     * @param itemName the name of the item.
     * @return the item.
     */
    public Item getItem(String itemName) {
        for (Item item : contents)
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
     * A method to remove an item from the room.
     * @param item the item to be removed.
     */
    public void removeItem(Item item) {
        contents.remove(item);
    }

    @Override
    public String getLongDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getLongDescription());
        sb.append("You see ");
        String[] itemNames = new String[contents.size()];
        if (itemNames.length > 0)
        {
            for (int i = 0; i < itemNames.length; i++)
            {
                itemNames[i] = "the " + contents.get(i).getName();
            }
            sb.append(Message.commaSeparatedList(itemNames));
        }
        else
        {
            sb.append("nothing in the room");
        }
        sb.append(".");
        sb.append("<br>");
        return sb.toString();
    }
}
