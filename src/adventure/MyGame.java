package adventure;

public class MyGame extends Game {
    public MyGame() {
        super(new Player(), new Parser());
    }

    public static void main(String[] args) {
        Game game = new MyGame();
        game.play();
    }

    @Override
    public void createCommands() {
        CommandWords commands = parser().commandWords();
        commands.addCommand("go",   new GoCommand());
        commands.addCommand("help", new HelpCommand(commands));
        commands.addCommand("quit", new QuitCommand());
        commands.addCommand("examine", new ExamineCommand());
        commands.addCommand("x",  new ExamineCommand());
    }

    @Override
    public void createRooms() {
        // create the rooms
        EnhancedRoom livingRoom = new EnhancedRoom("in your living room");
        EnhancedRoom outsideApartment = new EnhancedRoom("in a computing lab");

        // initialize room exits
        livingRoom.setExit("south", outsideApartment);
        outsideApartment.setExit("north", livingRoom);

        // create the items
        Item bicycle = new Item("bicycle");
        bicycle.setDescription(Message.bicycleDescriptionMessage());

        Item key = new Item("key");

        Item lock = new Item("lock");

        livingRoom.addItem(bicycle);
        livingRoom.addItem(key);
        livingRoom.addItem(lock);

        // the player starts the game outside
        player().setCurrentRoom(livingRoom);
    }


    @Override
    public String welcomeMessage() {
        return
            "<p>Welcome to The World of Simplicity!</p>"
            + "<p>Type 'help' if you need help.</p>"
            + "<p>Hit [return] to continue...</p>";
    }
}