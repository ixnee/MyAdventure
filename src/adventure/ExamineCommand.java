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
public class ExamineCommand
    extends Command
{
    @Override
    public String execute(Player playerArg)
    {
        // EnhancedPlayer player = (EnhancedPlayer) playerArg;
        EnhancedRoom room = (EnhancedRoom) playerArg.getCurrentRoom();

        if (!this.hasSecondWord()) {
            return "The examine command requires an object to examine.";
        }

        String nounString = this.getSecondWord();

        if (room.containsItem(nounString)) {
            Item item = room.getItem(nounString);
            if (item.getDescription() == null || item.getDescription().equals(""))
            {
                return Message.examineDefaultMessage(nounString);
            }
            return item.getDescription();
        }

       return Message.cantSeeMessage(nounString);

       //  else if (playerArg.containsItem(nounString)) {
       //    Item item = playerArg.getItem(nounString);
       //  return item.getDescription();
       // }
       // else {
       //     return Message.cantSeeMessage(nounString);
       // }



        // access the item via the second word

        // item can be in the room that the player is in (create enhanced room)

        // item can be in the player's inventory (create enhanced player)

    }

}
