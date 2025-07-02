package com.animalworld.menu;

import com.animalworld.Main;

/**
 * Concrete command. Pauses/plays the simulation.
 * @author Elaina Rivers
 */

public class StartStop implements Command {

    /**
     * Sets Main's paused value to the opposite of what it is.
    */
    @Override
    public void execute() {
        Main.paused = !Main.paused;
    }

}
