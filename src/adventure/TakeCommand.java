package adventure;
// -------------------------------------------------------------------------
/**
 *  Implementation of the 'take' user command for adventure games.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Lisa Balogh
 *  @version Mar 29, 2017
 */
public class TakeCommand
    extends Command
{
    @Override
    public String execute(Player playerArg)
    {
        EnhancedPlayer player = (EnhancedPlayer) playerArg;
        EnhancedRoom room = (EnhancedRoom) playerArg.getCurrentRoom();

        if (!this.hasSecondWord()) {
            return "What do you want to take?";
        }

        // access the item via the second word
        String nounString = this.getSecondWord();

        // item can be in the room that the player is in
        if (room.containsItem(nounString)) {
            Item item = room.getItem(nounString);
            String objName = item.getName();
            player.addItem(item);
            room.removeItem(item);
            return Message.takeSuccessMessage(objName);
        }

        // item can be in the player's inventory (create enhanced player)
        else if (player.hasItem(nounString)) {
            Item item = player.getItem(nounString);
            String objName = item.getName();
            return Message.takeAlreadyHaveMessage(objName);
        }

        // item is not in the room or the inventory
        return Message.cantSeeMessage(nounString);
    }

}
