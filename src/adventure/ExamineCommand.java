package adventure;
// -------------------------------------------------------------------------
/**
 *  Implementation of the 'examine' user command for adventure games.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Lisa Balogh
 *  @version Mar 29, 2017
 */
public class ExamineCommand
    extends Command
{
    @Override
    public String execute(Player playerArg)
    {
        EnhancedPlayer player = (EnhancedPlayer) playerArg;
        EnhancedRoom room = (EnhancedRoom) playerArg.getCurrentRoom();
        Item item = null;

        if (!this.hasSecondWord())
        {
            return "The examine command requires an object to examine.";
        }

        String nounString = this.getSecondWord();

        if (nounString.equals("inventory"))
        {

            return player.listInventory();
        }

        if (room.containsItem(nounString) || player.hasItem(nounString))
        {
            if (room.containsItem(nounString))
            {
                item = room.getItem(nounString);
            }
            else
            {
                item = player.getItem(nounString);
            }
            if (item.getDescription() == null || item.getDescription().equals(""))
            {
                return Message.examineDefaultMessage(nounString);
            }
            return item.getDescription();
        }

        return Message.cantSeeMessage(nounString);
    }
}
