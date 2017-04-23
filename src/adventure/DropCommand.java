package adventure;
// -------------------------------------------------------------------------
/**
 *  Implementation of the 'drop' user command for adventure games.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Lisa Balogh
 *  @version Apr 11, 2017
 */
public class DropCommand
    extends Command
{
    @Override
    public String execute(Player playerArg)
    {
        EnhancedPlayer player = (EnhancedPlayer) playerArg;
        EnhancedRoom room = (EnhancedRoom) playerArg.getCurrentRoom();

        if (!this.hasSecondWord()) {
            return Message.noSecondWordMessage("drop");
        }

        // access the item via the second word
        String nounString = this.getSecondWord();

        if (player.hasItem(nounString))
        {
            Item item = player.getItem(nounString);
            String roomName = room.getShortDescription();
            String objName = item.getName();
            if (objName.equals("ruby") &&
                roomName.equals("in the pawn shop"))
            {
                player.sethasWon();
                return Message.youWinMessage();
            }
            player.removeItem(item);
            room.addItem(item);
            return Message.dropSuccessMessage(objName);
        }

        // item is not in the inventory
        return Message.dropDontHaveMessage(nounString);
    }
}
