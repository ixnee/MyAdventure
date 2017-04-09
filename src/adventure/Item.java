package adventure;
// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author iXNÈE
 *  @version Mar 29, 2017
 */
public class Item
{
    private final String name;
    private String description;
    private int weight;

    // ----------------------------------------------------------
    /**
     * Create a new item object.
     * @param name the name of the item.
     */
    public Item(String name)
    {
        this.name = name;
    }

    // ----------------------------------------------------------
    /**
     * Get the current value of name.
     * @return The value of name for this object.
     */
    public String getName()
    {
        return name;
    }

    // ----------------------------------------------------------
    /**
     * Get the current value of description.
     * @return The value of description for this object.
     */
    public String getDescription()
    {
        return description;
    }

    // ----------------------------------------------------------
    /**
     * Set the value of description for this object.
     * @param description The new value for description.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    // ----------------------------------------------------------
    /**
     * Get the current value of weight.
     * @return The value of weight for this object.
     */
    public int getWeight()
    {
        return weight;
    }

    // ----------------------------------------------------------
    /**
     * Set the value of weight for this object.
     * @param weight The new value for weight.
     */
    public void setWeight(int weight)
    {
        this.weight = weight;
    }


}
