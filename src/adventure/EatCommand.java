package adventure;
// -------------------------------------------------------------------------
/**
 *  Implementation of the 'eat' user command for adventure games.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Lisa Balogh
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
            return Message.noSecondWordMessage("eat");
        }

        // access the item via the second word
        String nounString = this.getSecondWord();

        if (player.hasItem(nounString))
        {
            Item item = player.getItem(nounString);
            String objName = item.getName();
            if (item.isEdible())
            {
                if (objName.equals("peanut-butter"))
                {
                    player.setPeanutButter();
                    player.removeItem(item);
                    return Message.eatSuccessMessage(objName);
                }
                return Message.eatSuccessMessage(objName);
            }
            return Message.eatNotEdibleMessage(objName);
        }
        // item is not in the inventory
        else if (room.containsItem(nounString))
        {
            return Message.dontHaveMessage(nounString);
        }
        // item is not in the room or inventory
        return Message.cantSeeMessage(nounString);
    }
}
