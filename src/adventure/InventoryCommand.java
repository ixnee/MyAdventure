package adventure;
// -------------------------------------------------------------------------
/**
 *  Implementation of the 'inventory' user command for adventure games.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Lisa Balogh
 *  @version Mar 29, 2017
 */
public class InventoryCommand
    extends Command
{
    @Override
    public String execute(Player playerArg)
    {
        EnhancedPlayer player = (EnhancedPlayer) playerArg;

        if (player.getInventorySize() > 0)
        {
            return player.listInventory();
        }

        return Message.inventoryEmptyMessage();
    }
}
