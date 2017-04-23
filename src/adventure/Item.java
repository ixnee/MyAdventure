package adventure;
// -------------------------------------------------------------------------
/**
 *  A class for creating items and storing information about them.
 *
 *  @author Lisa Balogh
 *  @version Mar 29, 2017
 */
public class Item
{
    private final String name;
    private String description;
    private int weight;
    private boolean portable;
    private boolean edible;
    private boolean hasSmell;
    private boolean lockable;
    private boolean isLocked;

    // ----------------------------------------------------------
    /**
     * Create a new item object.
     * @param name the name of the item.
     */
    public Item(String name)
    {
        this.name = name;
        description = null;
        portable = true;
        edible = false;
        hasSmell = false;
        lockable = false;
        isLocked = false;
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

 // ----------------------------------------------------------
    /**
     * Get the current value of portable.
     * @return true if item is portable, false if not.
     */
    public boolean isPortable()
    {
        return portable;
    }

    // ----------------------------------------------------------
    /**
     * Makes this object non-portable.
     */
    public void notPortable()
    {
        this.portable = false;
    }

    // ----------------------------------------------------------
    /**
     * Get the current value of edible.
     * @return true if item is edible, false if not.
     */
    public boolean isEdible()
    {
        return edible;
    }

    // ----------------------------------------------------------
    /**
     * Makes this object edible.
     */
    public void setEdible()
    {
        this.edible = true;
    }

 // ----------------------------------------------------------
    /**
     * Get the current value of edible.
     * @return true if item is edible, false if not.
     */
    public boolean hasSmell()
    {
        return hasSmell;
    }

    // ----------------------------------------------------------
    /**
     * Makes this object edible.
     */
    public void setHasSmell()
    {
        this.hasSmell = true;
    }

    // ----------------------------------------------------------
    /**
     * Get the current value of lockable.
     * @return true if item is edible, false if not.
     */
    public boolean isLockable()
    {
        return lockable;
    }

    // ----------------------------------------------------------
    /**
     * Makes this object lockable.
     */
    public void setLockable()
    {
        this.lockable = true;
    }

    // ----------------------------------------------------------
    /**
     * Get the current value of isLocked.
     * @return true if item is locked, false if not.
     */
    public boolean isLocked()
    {
        return isLocked;
    }

    // ----------------------------------------------------------
    /**
     * Makes this object lockable.
     */
    public void setisLocked()
    {
        this.isLocked = true;
    }


}
