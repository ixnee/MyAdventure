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
public class EnhancedRoom
    extends Room
{
    private List<Item> contents;

    // ----------------------------------------------------------
    /**
     * Create a new EnhancedRoom object.
     * @param description
     */
    public EnhancedRoom(String description)
    {
        super(description);
        contents = new ArrayList<>();
    }

    public void addItem(Item item) {

        // TODO: implement this method

        contents.add(item);
    }

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

    public void removeItem(Item itemName) {
        // TODO: implement this method
    }

    @Override
    public String getLongDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getLongDescription());
        sb.append("You see ");
        String[] itemNames = new String[contents.size()];
        for (int i = 0; i < itemNames.length; i++)
        {
            itemNames[i] = "the " + contents.get(i).getName();
        }
        sb.append(Message.commaSeparatedList(itemNames));
        sb.append(".");
        sb.append("<br>");
        return sb.toString();
    }
}
