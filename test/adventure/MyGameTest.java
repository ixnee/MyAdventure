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
    public void testTakeRuby() {
        executeMoves("west", "take ruby");
        assertEquals("in your bedroom", roomDesc);
        assertEquals(Message.takeSuccessMessage("ruby"), message);
    }

    private void executeMoves(String... inputs) {
        for (String input : inputs) {
            Command command = game.parser().getCommand(input);
            message = command.execute(game.player());
        }
        roomDesc = game.player().getCurrentRoom().getShortDescription();
        longDesc = game.player().getCurrentRoom().getLongDescription();
    }
}
