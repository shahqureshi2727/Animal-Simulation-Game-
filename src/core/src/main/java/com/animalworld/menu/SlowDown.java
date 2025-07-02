package com.animalworld.menu;

import com.animalworld.Main;

/**
 * Concrete command. Slows down the simulation.
 * @author Elaina Rivers
 */

public class SlowDown implements Command {

    @Override
    public void execute() {
        Main.speed += 20;
    }

}
