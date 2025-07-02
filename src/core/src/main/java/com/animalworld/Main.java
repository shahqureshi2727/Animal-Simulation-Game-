package com.animalworld;

import java.io.File;

import com.animalworld.SimulationStates.SimulationCaretaker;
import com.animalworld.animal.Animal;
import com.animalworld.animal.loader.CreatureFileLoaderSingleton;
import com.animalworld.animal.traits.FlightMovement;
import com.animalworld.animal.traits.ForagingState;
import com.animalworld.board.Board;
import com.animalworld.graphics.TextureSingleton;
import com.animalworld.menu.AddAnimal;
import com.animalworld.menu.ButtonContext;
import com.animalworld.menu.ButtonState;
import com.animalworld.menu.Command;
import com.animalworld.menu.Invoker;
import com.animalworld.menu.SlowDown;
import com.animalworld.menu.SpeedUp;
import com.animalworld.menu.StartStop;
import com.animalworld.menu.StartedState;
import com.animalworld.menu.StoppedState;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/*
 * This file drives the game engine we use.
 * The methods present in Main are required by the library.
 * If anyone has trouble understanding libGDX ask me or check out this tutorial: https://libgdx.com/wiki/start/a-simple-game
 */
public class Main implements ApplicationListener {
    /*
     * This graphics library requires us to instantiate any objects we're going to
     * use.
     * Here's a simple description of what each type is.
     * Texture - An image we can draw to the buffer.
     * Sprite - A texture but with added functionality, such as storing its own
     * location and translation (movement).
     * SpriteBatch - For efficiency, we group all our draws into one spriteBatch.
     * FitViewport - How we control the size and scale of our display.
     * paused - Whether the simulation is paused or not. true = paused, false = playing.
     * speed - How fast the simulation is going. lower = faster
     * stage - Needed for placement of menu.
     * invoker - Needed to invoke menu commands.
     * mainButtonContext - Context for Button state.
     * startedState - State for StartStop button if simulation is playing.
     * stoppedState - State for StartStop button if simulation is paused.
     */

    /*
     * Below are our parameters. We couldn't figure out how to make a jar with gradle because
     * we were low on neurons. It's last minute, I know this is bad practice.
     */
    final String directory = "";
    public static final int mutationRate = 3;
    public static final int outputFrequency = 30;
    public static final int boardSize = 50;
    public static final int iterations = -1;

    /*
     * The Board is a singleton and needs to be accessed globally.
     */
    Board board = Board.getInstance(boardSize, boardSize);
    CreatureFileLoaderSingleton fileLoader = CreatureFileLoaderSingleton.getInstance();

    private TextureSingleton textures;
    Animal cow;
    Animal cow2;
    SpriteBatch spriteBatch;
    FitViewport viewport;
    public static boolean paused;
    public static int speed = 200;
    private Stage stage;
    Invoker invoker = new Invoker();
    ButtonContext mainButtonContext = new ButtonContext();
    ButtonState startedState = new StartedState();
    ButtonState stoppedState = new StoppedState();
    // Create caretaker for simulation state save and restore.
    SimulationCaretaker simCaretaker = new SimulationCaretaker();

    /*
     * This method will run only once. It allows us to instantiate anything we need.
     */
    @Override
    public void create() {
        // SFX set up.
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("src/assets/menu_choose.mp3"));

        // Load the skin for UI.
        Skin menuSkin = new Skin(Gdx.files.internal("src/assets/ui_skin.json"));

        // Create new Stage for UI.
        stage = new Stage(new FitViewport(600, 600));
        Gdx.input.setInputProcessor(stage);

        // Table creation.
        Table table = new Table();
        table.setPosition(0, 0);
        table.setSize(Gdx.graphics.getWidth(), 200);
        table.align(Align.bottom);
        stage.addActor(table);

        // Speed Up button.
        TextButton speedUpButton = new TextButton("Speed Up", menuSkin);
        table.add(speedUpButton).expand().center().width(170).height(75);
        speedUpButton.addListener(new ClickListener() {
            @Override
        public void clicked(InputEvent event, float x, float y) {
            sound.play((float) 1.0);
            Command speedUpCommand = new SpeedUp();
            invoker.setCommand(speedUpCommand);
            invoker.executeCommand();
        }
        });

        // Slow Down button.
        TextButton slowDownButton = new TextButton("Slow Down", menuSkin);
        table.add(slowDownButton).expand().center().width(170).height(75);
        slowDownButton.addListener(new ClickListener() {
            @Override
        public void clicked(InputEvent event, float x, float y) {
            sound.play((float) 1.0);
            Command slowDownCommand = new SlowDown();
            invoker.setCommand(slowDownCommand);
            invoker.executeCommand();
        }
        });

        // Add another row to table so that HUD is 2x2.
        table.row();

        // Add Animal Button.
        TextButton addAnimalButton = new TextButton("Add Animal", menuSkin);
        table.add(addAnimalButton).expand().center().width(170).height(75);
        addAnimalButton.addListener(new ClickListener() {
            @Override
        public void clicked(InputEvent event, float x, float y) {
            sound.play((float) 1.0);
            Command addAnimalCommand = new AddAnimal();
            invoker.setCommand(addAnimalCommand);
            invoker.executeCommand();
        }
        });

        // Set Button State to StartedState to begin with.
        mainButtonContext.setButtonState(startedState);

        // Add Start/Stop Button.
        TextButton startStopButton = new TextButton(mainButtonContext.changeText(), menuSkin);
        table.add(startStopButton).expand().center().width(170).height(75);
        startStopButton.addListener(new ClickListener() {
            @Override
        public void clicked(InputEvent event, float x, float y) {
            sound.play((float) 1.0);
            Command startStopCommand = new StartStop();
            invoker.setCommand(startStopCommand);
            invoker.executeCommand();
            // Change ButtonState depending on whether the simulation is paused or not.
            if (paused == true) {
                mainButtonContext.setButtonState(stoppedState);
            } else {
                mainButtonContext.setButtonState(startedState);
            }
            startStopButton.getLabel().setText(mainButtonContext.changeText());
        }
        });

        // Get our texture provider
        textures = TextureSingleton.getInstance();
        // Initializes the board.
        board.initializeBoardWithPlantTiles();

        spriteBatch = new SpriteBatch();
        // Controls our grid size.
        viewport = new FitViewport(board.getWidth(), board.getLength());
        // Creating a test animal object.
        cow = new Animal("Cow", 1, board.getLength(), 1, 1, 1, true, true, 1,
                textures.getTexture("cow.png"), "Plants");
        // Letting it be a herbivore.
        // HerbivoreBehavior plants = new HerbivoreBehavior();
        cow.setFeedingStrategy("Plants");
        // Letting it move closer to other animals.
        FlightMovement rand = new FlightMovement();
        cow.setMovementStrategy(rand);
        // Testing the toString implementation.
        cow.setState(new ForagingState());
        System.out.println(cow);


        // cow2 = cow.clone();
        // FightMovement rand2 = new FightMovement();
        // cow2.setSize(5);
        // cow2.setMovementRate(1);
        // cow2.setMovementStrategy(rand2);
        // cow2.setFeedingStrategy("Meat");
        // cow2.setState(new ForagingState());
        // cow2.getSprite().setPosition(10,10);
        // System.out.println(cow2);

        // The following code can be used once the Movement Strategy is fleshed out
        // cow2.setState(new HuntingState());
        loadAnimals();
    }

    // Assuming this is called whenever we resize the window.
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        input();
        // Check if game is paused.
        if (paused == false) {
            logic();
        }
        draw();
        try {
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // UI/Menu drawing.
        stage.draw();
    }

    // If we want to take input, we'd do it here. If we're going to make a
    // simulation, ignore this.
    private void input() {

        // For save(K) and load(L) of the states with Memento. Could probably be done in a better way.
        // (Commented to make sure nothing breaks.)
        // if (Gdx.input.isKeyPressed(Input.Keys.K)) {
        //     SimulationOriginator simOriginator = new SimulationOriginator(board.getBoard(), board.getTiles(), board.getAnimals());
        //     simCaretaker.addMemento(simOriginator.createMemento());
        // }
        // if (Gdx.input.isKeyPressed(Input.Keys.L)) {
        //     SimulationOriginator simOriginator = new SimulationOriginator(null, null, null);
        //     simOriginator.restoreFromMemento(simCaretaker.getMemento());

        //     board.setBoard(simOriginator.getBoard());
        //     board.setTileList(simOriginator.getTiles());
        //     board.setAnimalList(simOriginator.getAnimalList());
        // }
    }

    int iterationCounter = 0;
    // Our actual game logic should execute inside here.
    private void logic() {
        iterationCounter++;
        // Calling on each of our animals...
        for (int i = 0; i < board.getAnimals().size(); i++) {
            if (board.getAnimals().get(i) != null) {
                board.getAnimals().get(i).act();
            }
        }

        // Calling on each of our tiles...
        for (int i = 0; i < board.getTiles().size(); i++) {
            if (board.getTiles().get(i) != null) {
                board.getTiles().get(i).act();
            }
        }
        if (iterationCounter % outputFrequency == 0) {
//            AnimalStatistics.displayAnimalStats();
            System.out.println("Animals: " + board.getAnimals().size());
        }

        if (iterationCounter == iterations) {
            System.exit(-1);
        }

    }

    /*
     * This method will handle drawing to the screen.
     * I've started with some rudimentary code.
     */
    private void draw() {
        // Basic things we need to do...
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

        // All of our drawing should take place between spriteBatch begin and end.
        // #############################################################################################################
        spriteBatch.begin();

        // Drawing out our grid...
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getLength(); j++) {
                spriteBatch.draw(board.getBoard()[i][j].getTexture(), i, j, 1, 1);
            }
        }

        // Drawing our animals...
        for (Animal animal : board.getAnimals()) {
            animal.getSprite().draw(spriteBatch);
        }

        spriteBatch.end();
        // #############################################################################################################
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
    
    /*
     * Code courtesy of Mike Samuel
     * Original code:https://stackoverflow.com/a/4917347
     */
    public void loadAnimals() {
        //Creating a File object for directory
        final Texture thisTexture = textures.getTexture("cow.png");

        File dir = new File(System.getProperty("user.dir")+"/src/assets");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
          for (File child : directoryListing) {
            Animal newAnimal = fileLoader.loadFile(child.toString(), thisTexture);
          }
        } else {
          // Handle the case where dir is not really a directory.
          // Checking dir.isDirectory() above would not be sufficient
          // to avoid race conditions with another process that deletes
          // directories.
        }
    }

}
