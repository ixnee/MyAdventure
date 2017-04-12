package adventure;

public class MyGame extends Game {
    public MyGame() {
        super(new EnhancedPlayer(), new Parser());
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
        commands.addCommand("take", new TakeCommand());
        commands.addCommand("drop", new DropCommand());
        commands.addCommand("x",  new ExamineCommand());
        commands.addCommand("s", new GoCommand());
        commands.addCommand("q", new QuitCommand());
        commands.addCommand("h", new HelpCommand(commands));
    }

    @Override
    public void createRooms() {
        // create the rooms
        EnhancedRoom livingRoom = new EnhancedRoom("in your living room");
        EnhancedRoom outsideApartment = new EnhancedRoom("outside your apartment");
        EnhancedRoom kitchen = new EnhancedRoom("in your kitchen");
        EnhancedRoom bedroom = new EnhancedRoom("in your bedroom");

        // initialize room exits
        livingRoom.setExit("south", outsideApartment);
        livingRoom.setExit("west", bedroom);
        livingRoom.setExit("east", kitchen);
        outsideApartment.setExit("north", livingRoom);
        kitchen.setExit("west", livingRoom);
        bedroom.setExit("east", livingRoom);

        // create the items
        Item bicycle = new Item("bicycle");
        bicycle.setDescription(Message.bicycleDescriptionMessage());
        bicycle.setWeight(30);

        Item key = new Item("key");
        key.setWeight(1);

        Item lock = new Item("lock");
        lock.setWeight(3);

        Item peanutButter = new Item("peanut-butter");
        peanutButter.setDescription(Message.peanutButterDescriptionMessage());
        peanutButter.setWeight(4);

        Item ruby = new Item("ruby");
        ruby.setDescription(Message.rubyDescriptionMessage());
        ruby.setWeight(1);

        // add items to rooms
        livingRoom.addItem(bicycle);
        livingRoom.addItem(key);
        livingRoom.addItem(lock);
        kitchen.addItem(peanutButter);
        bedroom.addItem(ruby);

        // the player starts the game in the living room
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