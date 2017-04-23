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
    public void testCallingMain() {
        MyGame.main(new String[0]);
    }

    @Test
    public void testWelcomeMessage() {
        assertEquals("<p>Welcome to The Lost in Your Apartment Scenario!</p>"
            + "<p>Type 'help' if you need help.</p>"
            + "<p>Hit [return] to continue...</p>", game.welcomeMessage());
    }

    @Test
    public void testInit() {
        roomDesc = game.player().getCurrentRoom().getShortDescription();
        assertEquals("in your living room", roomDesc);
    }

    @Test
    public void testExamineNoSecondWord() {
        executeMoves("examine");
        assertEquals(Message.noSecondWordMessage("examine"), message);
    }

    @Test
    public void testExamineDefaultMessage() {
        executeMoves("examine pencil");
        assertEquals(Message.examineDefaultMessage("pencil"), message);
    }

    @Test
    public void testExamineDefaultMessageEmptyString() {
        executeMoves("examine laptop");
        assertEquals(Message.examineDefaultMessage("laptop"), message);
    }

    @Test
    public void testExamineBicycle() {
        executeMoves("examine bicycle");
        assertEquals("in your living room", roomDesc);
        assertEquals(Message.bicycleDescriptionMessage(), message);
        assertTrue(longDesc.contains("bicycle"));
    }

    @Test
    public void testExamineBicycleInInventory() {
        executeMoves("take bicycle", "examine bicycle");
        assertEquals(Message.bicycleDescriptionMessage(), message);
    }

    @Test
    public void testExamineItemNotInRoomOrInventory() {
        executeMoves("examine ruby");
        assertEquals(Message.cantSeeMessage("ruby"), message);
    }

    @Test
    public void testInventoryCommand() {
        executeMoves("take pencil", "take laptop");
        executeMoves("inventory");
        assertEquals("You have the pencil and the laptop.<br>", message);
    }

    @Test
    public void testInventoryCommandNoItemsInInventory() {
        executeMoves("inventory");
        assertEquals(Message.inventoryEmptyMessage(), message);
    }

    @Test
    public void testTakeNoSecondWord() {
        executeMoves("take");
        assertEquals(Message.noSecondWordMessage("take"), message);
    }

    @Test
    public void testTakeItemNotPortable() {
        testLeaveAfterDoingAllRequiredTasks();
        executeMoves("west", "take cash-register");
        assertEquals(Message.takeFailMessage("cash-register"), message);
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
    public void testTakeTooMuchWeight() {
        executeMoves("take bicycle", "take pencil");
        assertEquals(Message.takeNotEnoughRoomMessage("pencil"), message);
    }

    @Test
    public void testTakeTooManyItems() {
        executeMoves("take pencil", "take laptop", "go east", "take peanut-butter", "go west", "go west", "take ruby");
        assertEquals(Message.takeNotEnoughRoomMessage("ruby"), message);
    }

    @Test
    public void testTakeItemIsLocked() {
        executeMoves("lock bicycle", "take bicycle");
        assertEquals(Message.takeLockedMessage("bicycle"), message);
    }

    @Test
    public void testDropNoSecondWord() {
        executeMoves("drop");
        assertEquals(Message.noSecondWordMessage("drop"), message);
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

    @Test
    public void testEatNoSecondWord() {
        executeMoves("eat");
        assertEquals(Message.noSecondWordMessage("eat"), message);
    }

    @Test
    public void testEatPeanutButter() {
        executeMoves("go east", "take peanut-butter", "eat peanut-butter");
        assertEquals(Message.eatSuccessMessage("peanut-butter"), message);
    }

    @Test
    public void testEatPeanutButterAlreadyAte() {
        executeMoves("go east", "take peanut-butter", "eat peanut-butter", "eat peanut-butter");
        assertEquals(Message.cantSeeMessage("peanut-butter"), message);
    }

    @Test
    public void testEatChocolate() {
        executeMoves("go east", "take chocolate", "eat chocolate");
        assertEquals(Message.eatSuccessMessage("chocolate"), message);
    }

    @Test
    public void testEatPeanutButterCantSee() {
        executeMoves("eat peanut-butter");
        assertEquals(Message.cantSeeMessage("peanut-butter"), message);
    }

    @Test
    public void testEatPeanutButterDontHave() {
        executeMoves("go east", "eat peanut-butter");
        assertEquals(Message.dontHaveMessage("peanut-butter"), message);
    }

    @Test
    public void testEatpencilNotEdible() {
        executeMoves("take pencil", "eat pencil");
        assertEquals(Message.eatNotEdibleMessage("pencil"), message);
    }

    @Test
    public void testSmellNoSecondWord() {
        executeMoves("smell");
        assertEquals(Message.noSecondWordMessage("smell"), message);
    }

    @Test
    public void testSmellItemHasNoSmell() {
        testLeaveAfterDoingAllRequiredTasks();
        executeMoves("smell ruby");
        assertEquals(Message.hasNoSmellMessage("ruby"), message);
    }

    @Test
    public void testSmellItemInRoomNoSmell() {
        executeMoves("smell pencil");
        assertEquals(Message.hasNoSmellMessage("pencil"), message);
    }

    @Test
    public void testSmellItemInRoom() {
        testLeaveAfterDoingAllRequiredTasks();
        executeMoves("s", "smell flowers");
        assertEquals(Message.smellSuccessMessage("flowers"), message);
    }

    @Test
    public void testSmellItemInInventory() {
        testLeaveAfterDoingAllRequiredTasks();
        executeMoves("s", "take flowers", "smell flowers");
        assertEquals(Message.smellSuccessMessage("flowers"), message);
    }

    @Test
    public void testSmellItemNotInRoomORInventory() {
        testLeaveAfterDoingAllRequiredTasks();
        executeMoves("w", "smell flowers");
        assertEquals(Message.cantSeeMessage("flowers"), message);
    }

    @Test
    public void testLockNoSecondWord() {
        executeMoves("lock");
        assertEquals(Message.noSecondWordMessage("lock"), message);
    }

    @Test
    public void testLockPlayerHasItem() {
        executeMoves("take bicycle", "lock bicycle");
        assertEquals(Message.lockPutDownMessage("bicycle"), message);
    }

    @Test
    public void testLockItemNotInRoomOrInventory() {
        executeMoves("go east", "lock bicycle");
        assertEquals(Message.cantSeeMessage("bicycle"), message);
    }

    @Test
    public void testLockInventoryItemNotLockable() {
        executeMoves("take pencil", "lock pencil");
        assertEquals(Message.lockNotPossibleMessage("pencil"), message);
    }

    @Test
    public void testLockRoomItemNotLockable() {
        executeMoves("lock pencil");
        assertEquals(Message.lockNotPossibleMessage("pencil"), message);
    }

    @Test
    public void testLockBicycle() {
        executeMoves("lock bicycle");
        assertEquals(Message.lockSuccessMessage("bicycle"), message);
    }

    @Test
    public void testLockBicycleAlreadyLocked() {
        executeMoves("lock bicycle");
        executeMoves("lock bicycle");
        assertEquals(Message.objectAlreadyLockedMessage("bicycle"), message);
    }

    @Test
    public void testExamineBicycleLockedMessage() {
        executeMoves("lock bicycle", "examine bicycle");
        assertEquals("You use your bicycle to get to work. The bicycle is locked up securely.", message);
    }

    @Test
    public void testGoNoSecondWord() {
        executeMoves("go");
        assertEquals("Go Where?", message);
    }

    @Test
    public void testGoNoExit() {
        executeMoves("north");
        assertEquals(Message.noExitInDirectionMessage(), message);
    }

    @Test
    public void testGoDirNull() {
        executeMoves("go east");
        assertEquals("in your kitchen", roomDesc);
    }

    @Test
    public void testLeaveWithoutEatingPB() {
        executeMoves("s");
        assertEquals(Message.exitWithoutEatingPBMessage(), message);
    }

    @Test
    public void testLeaveWithoutLockingBike() {
        executeMoves("e", "take peanut-butter", "eat peanut-butter", "w", "s");
        assertEquals(Message.exitWithoutLockingBikeMessage(), message);
    }

    @Test
    public void testLeaveWithoutLockingBikeInInventory() {
        executeMoves("e", "take peanut-butter", "eat peanut-butter", "w");
        executeMoves("take bicycle", "s");
        assertEquals(Message.exitWithoutLockingBikeMessage(), message);
    }

    @Test
    public void testLeaveWithoutTakingRuby() {
        executeMoves("e", "take peanut-butter", "eat peanut-butter", "w");
        executeMoves("lock bicycle", "s");
        assertEquals(Message.exitWithoutTakingRubyMessage(), message);
    }

    @Test
    public void testLeaveAfterLockingBikeTakingRubyNotEatingPB() {
        executeMoves("w", "take ruby", "e", "lock bicycle", "s");
        assertEquals(Message.exitWithoutEatingPBMessage(), message);
    }

    @Test
    public void testLeaveAfterTakingRubyNotLockingBike() {
        executeMoves("e", "take peanut-butter", "eat peanut-butter", "w");
        executeMoves("w", "take ruby", "e", "s");
        assertEquals(Message.exitWithoutLockingBikeMessage(), message);
    }

    @Test
    public void testLeaveAfterDoingAllRequiredTasks() {
        executeMoves("e", "take peanut-butter", "eat peanut-butter", "w");
        executeMoves("w", "take ruby", "e", "lock bicycle", "s");
        assertEquals("outside your apartment", roomDesc);
    }

    @Test
    public void testGoSouthNotInLivingRoom() {
        executeMoves("e", "s");
        assertEquals(Message.noExitInDirectionMessage(), message);
    }

    @Test
    public void testGoNorthFromLivingRoom() {
        executeMoves("n");
        assertEquals(Message.noExitInDirectionMessage(), message);
    }

    @Test
    public void testGoNorthFromKitchen() {
        executeMoves("go e", "go n");
        assertEquals(Message.noExitInDirectionMessage(), message);
    }

    @Test
    public void testDropFlowersInPawnShop() {
        testLeaveAfterDoingAllRequiredTasks();
        executeMoves("south", "take flowers", "north", "west", "drop flowers");
        assertEquals("in the pawn shop", roomDesc);
        assertEquals(Message.dropSuccessMessage("flowers"), message);
    }

    @Test
    public void testHasWon() {
        testLeaveAfterDoingAllRequiredTasks();
        executeMoves("west", "drop ruby");
        assertEquals("in the pawn shop", roomDesc);
        assertEquals(Message.youWinMessage(), message);
    }

}
