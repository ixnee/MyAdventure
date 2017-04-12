package adventure;
// -------------------------------------------------------------------------
/**
 *  Implementation of the 'eat' user command for adventure games.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author iXNÈE
 *  @version Apr 11, 2017
 */
public class EatCommand
    extends Command
{
    @Override
    public String execute(Player playerArg)
    {
        EnhancedPlayer player = (EnhancedPlayer) playerArg;
        EnhancedRoom room = (EnhancedRoom) playerArg.getCurrentRoom();

        if (!this.hasSecondWord()) {
            return "What do you want to eat?";
        }

        // access the item via the second word
        String nounString = this.getSecondWord();

        if (player.hasItem(nounString))
        {
            Item item = player.getItem(nounString);
            String objName = item.getName();
            player.removeItem(item);
            room.addItem(item);
            return Message.dropSuccessMessage(objName);
        }

        // item is not in the inventory
        return Message.dropDontHaveMessage(nounString);
    }
}
