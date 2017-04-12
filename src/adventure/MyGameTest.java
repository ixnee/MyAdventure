package adventure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

//CHECKSTYLE:OFF
@SuppressWarnings("javadoc")
public class MyGameTest {

    private String message;
    private String roomDesc;
    private String longDesc;
    private Game game;

    public MyGameTest() {
    }

    @Before
    public void setUp() {
        game = new MyGame();
        game.createCommands();
        game.createRooms();
    }

    private void executeMoves(String... inputs) {
        for (String input : inputs) {
            Command command = game.parser().getCommand(input);
            message = command.execute(game.player());
        }
        roomDesc = game.player().getCurrentRoom().getShortDescription();
        longDesc = game.player().getCurrentRoom().getLongDescription();
    }

    @Test
    public void testInit() {
        roomDesc = game.player().getCurrentRoom().getShortDescription();
        assertEquals("in your living room", roomDesc);
    }

    @Test
    public void testExamineBicycle() {
        executeMoves("examine bicycle");
        assertEquals("in your living room", roomDesc);
        assertEquals(Message.bicycleDescriptionMessage(), message);
        assertTrue(longDesc.contains("bicycle"));
    }

    @Test
    public void testExamineInventory() {
        executeMoves("take key", "take lock");
        executeMoves("examine inventory");
        assertEquals("You have the key and the lock.<br>", message);
    }

    @Test
    public void testExamineInventoryNoItemsIninventory() {
        executeMoves("examine inventory");
        assertEquals(Message.inventoryEmptyMessage(), message);
    }

    @Test
    public void testTakeNoSecondWord() {
        executeMoves("take");
        assertEquals("What do you want to take?", message);
    }

    @Test
    public void testTakeRubyNotInRoom() {
        executeMoves("take ruby");
        assertEquals("in your living room", roomDesc);
        assertEquals(Message.cantSeeMessage("ruby"), message);
    }

    @Test
    public void testTakeRuby() {
        executeMoves("go west", "take ruby");
        assertEquals("in your bedroom", roomDesc);
        assertEquals(Message.takeSuccessMessage("ruby"), message);
    }

    @Test
    public void testTakeRubyAlreadyHave() {
        executeMoves("go west", "take ruby");
        executeMoves("take ruby");
        assertEquals(Message.takeAlreadyHaveMessage("ruby"), message);
    }

    @Test
    public void testDropNoSecondWord() {
        executeMoves("drop");
        assertEquals("What do you want to drop?", message);
    }

    @Test
    public void testDropRubyFromInventory() {
        executeMoves("go west", "take ruby");
        executeMoves("take ruby");
        executeMoves("drop ruby");
        assertEquals(Message.dropSuccessMessage("ruby"), message);
    }

    @Test
    public void testDropRubyDontHaveRuby() {
        executeMoves("drop ruby");
        assertEquals(Message.dropDontHaveMessage("ruby"), message);
    }

}
