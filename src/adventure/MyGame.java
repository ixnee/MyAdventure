package adventure;

// -------------------------------------------------------------------------
/**
 *  An exciting adventure game.
 *
 *  @author Lisa Balogh
 *  @version Apr 18, 2017
 */
public class MyGame extends Game {
    // ----------------------------------------------------------
    /**
     * Create a new MyGame object.
     */
    public MyGame() {
        super(new EnhancedPlayer(), new Parser());
    }

    // ----------------------------------------------------------
    /**
     * The main play method
     * @param args the arguments passed to the method
     */
    public static void main(String[] args) {
        Game game = new MyGame();
        game.play();
    }

    @Override
    public void createCommands() {
        CommandWords commands = parser().commandWords();
        commands.addCommand("help", new HelpCommand(commands));
        commands.addCommand("quit", new QuitCommand());
        commands.addCommand("examine", new ExamineCommand());
        commands.addCommand("inventory", new InventoryCommand());
        commands.addCommand("take", new TakeCommand());
        commands.addCommand("drop", new DropCommand());
        commands.addCommand("lock", new LockCommand());
        commands.addCommand("eat", new EatCommand());
        commands.addCommand("x",  new ExamineCommand());
        commands.addCommand("i", new InventoryCommand());
        commands.addCommand("q", new QuitCommand());
        commands.addCommand("h", new HelpCommand(commands));
        commands.addCommand("smell", new SmellCommand());
        commands.addCommand("go", new MovementCommand());
        commands.addCommand("north", new MovementCommand("north"));
        commands.addCommand("n", new MovementCommand("north"));
        commands.addCommand("south", new MovementCommand("south"));
        commands.addCommand("s", new MovementCommand("south"));
        commands.addCommand("east", new MovementCommand("east"));
        commands.addCommand("e", new MovementCommand("east"));
        commands.addCommand("west", new MovementCommand("west"));
        commands.addCommand("w", new MovementCommand("west"));
        commands.addCommand("up", new MovementCommand("up"));
        commands.addCommand("u", new MovementCommand("up"));
        commands.addCommand("down", new MovementCommand("down"));
        commands.addCommand("d", new MovementCommand("down"));

    }

    @Override
    public void createRooms() {
        // create the rooms
        EnhancedRoom livingRoom = new EnhancedRoom("in your living room");
        EnhancedRoom outsideApartment =
            new EnhancedRoom("outside your apartment");
        EnhancedRoom kitchen = new EnhancedRoom("in your kitchen");
        EnhancedRoom bedroom = new EnhancedRoom("in your bedroom");
        EnhancedRoom garden = new EnhancedRoom("in your garden");
        EnhancedRoom library = new EnhancedRoom("in the library");
        EnhancedRoom pawnShop = new EnhancedRoom("in the pawn shop");
        EnhancedRoom garage = new EnhancedRoom("in your garage");
        EnhancedRoom basement = new EnhancedRoom("in your basement");
        EnhancedRoom attic = new EnhancedRoom("in your attic");

        // initialize room exits
        livingRoom.setExit("south", outsideApartment);
        livingRoom.setExit("west", bedroom);
        livingRoom.setExit("east", kitchen);
        outsideApartment.setExit("north", livingRoom);
        kitchen.setExit("west", livingRoom);
        kitchen.setExit("down", basement);
        basement.setExit("up", kitchen);
        bedroom.setExit("east", livingRoom);
        bedroom.setExit("up", attic);
        attic.setExit("down", bedroom);
        outsideApartment.setExit("south", garden);
        outsideApartment.setExit("east", garage);
        garden.setExit("north", outsideApartment);
        garage.setExit("west", outsideApartment);
        outsideApartment.setExit("west", pawnShop);
        pawnShop.setExit("east", outsideApartment);
        pawnShop.setExit("west", library);
        library.setExit("east", pawnShop);

        // create the items
        Item bicycle = new Item("bicycle");
        bicycle.setDescription(Message.bicycleDescriptionMessage());
        bicycle.setWeight(30);
        bicycle.setLockable();

        Item pencil = new Item("pencil");
        pencil.setWeight(2);

        Item laptop = new Item("laptop");
        laptop.setWeight(7);
        laptop.setDescription("");

        Item peanutButter = new Item("peanut-butter");
        peanutButter.setDescription(Message.peanutButterDescriptionMessage());
        peanutButter.setWeight(4);
        peanutButter.setEdible();

        Item ruby = new Item("ruby");
        ruby.setDescription(Message.rubyDescriptionMessage());
        ruby.setWeight(1);

        Item chocolate = new Item("chocolate");
        chocolate.setWeight(1);
        chocolate.setEdible();

        Item flowers = new Item("flowers");
        flowers.setWeight(2);
        flowers.setHasSmell();

        Item cashRegister = new Item("cash-register");
        cashRegister.notPortable();
        cashRegister.setWeight(20);


        // add items to rooms
        livingRoom.addItem(bicycle);
        livingRoom.addItem(pencil);
        livingRoom.addItem(laptop);
        kitchen.addItem(peanutButter);
        kitchen.addItem(chocolate);
        bedroom.addItem(ruby);
        garden.addItem(flowers);
        pawnShop.addItem(cashRegister);

        // the player starts the game in the living room
        player().setCurrentRoom(livingRoom);
    }

    @Override
    public String welcomeMessage() {
        return
            "<p>Welcome to The Lost in Your Apartment Scenario!</p>"
            + "<p>Type 'help' if you need help.</p>"
            + "<p>Hit [return] to continue...</p>";
    }

}