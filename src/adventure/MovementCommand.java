package adventure;
// -------------------------------------------------------------------------
/**
 *  Implementation of the 'go' user command for adventure games.
 *
 *  @author Lisa Balogh
 *  @version Apr 11, 2017
 */
public class MovementCommand
    extends Command
{
    private final String direction;

    // ----------------------------------------------------------
    /**
     * Create a new MovementCommand object.
     */
    public MovementCommand()
    {
        direction = null;
    }

    // ----------------------------------------------------------
    /**
     * Create a new MovementCommand object.
     * @param d the direction to move.
     */
    public MovementCommand(String d)
    {
        direction = d;
    }

    @Override
    public String execute(Player playerArg)
    {
        EnhancedPlayer player = (EnhancedPlayer) playerArg;
        EnhancedRoom room = (EnhancedRoom) playerArg.getCurrentRoom();

        String dir = direction;
        EnhancedRoom newRoom = null;
        if (dir == null)
        {
            if (!this.hasSecondWord())
            {
                return ("Go Where?");
            }
            dir = getSecondWord();
        }
        if (room.getExitDirections().contains(dir))
        {
            if (dir.equals("south") &&
                room.getShortDescription().equals("in your living room"))
            {
                if (!player.hasEatenPeanutButter())
                {
                    return Message.exitWithoutEatingPBMessage();
                }
                if (player.hasItem("bicycle"))
                {
                    return Message.exitWithoutLockingBikeMessage();
                }
                Item item = room.getItem("bicycle");
                if (!item.isLocked())
                {
                    return Message.exitWithoutLockingBikeMessage();
                }
                if (!player.listInventory().contains("ruby"))
                {
                    return Message.exitWithoutTakingRubyMessage();
                }
            }
            newRoom = (EnhancedRoom) room.getExit(dir);
            player.setCurrentRoom(newRoom);
            return "";
        }
        return Message.noExitInDirectionMessage();
    }
}