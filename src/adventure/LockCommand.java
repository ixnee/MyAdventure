package adventure;
// -------------------------------------------------------------------------
/**
 *  Implementation of the 'lock' user command for adventure games.
 *
 *  @author Lisa Balogh
 *  @version Apr 11, 2017
 */
public class LockCommand
    extends Command
{
    @Override
    public String execute(Player playerArg)
    {
        EnhancedPlayer player = (EnhancedPlayer) playerArg;
        EnhancedRoom room = (EnhancedRoom) playerArg.getCurrentRoom();

        if (!this.hasSecondWord()) {
            return Message.noSecondWordMessage("lock");
        }

        // access the item via the second word
        String nounString = this.getSecondWord();

        if (room.containsItem(nounString))
        {
            Item item = room.getItem(nounString);
            String objName = item.getName();
            if (item.isLockable())
            {
                if (item.isLocked())
                {
                    return Message.objectAlreadyLockedMessage(objName);
                }
                item.setisLocked();
                return Message.lockSuccessMessage(objName);
            }
            return Message.lockNotPossibleMessage(objName);
        }
        else if (player.hasItem(nounString))
        {
            Item item = player.getItem(nounString);
            String objName = item.getName();
            if (item.isLockable())
            {
                return Message.lockPutDownMessage(objName);
            }
            return Message.lockNotPossibleMessage(objName);
        }
        // item can be in the room that the player is in

        return Message.cantSeeMessage(nounString);
    }
}
