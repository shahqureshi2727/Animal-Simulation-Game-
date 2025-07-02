package com.animalworld.menu;

import com.animalworld.Main;

/**
 * Concrete command. Speeds up the simulation.
 * @author Elaina Rivers
 */

public class SpeedUp implements Command {

    @Override
    public void execute() {
        if (Main.speed - 20 < 0) {
            Main.speed = 0;
        } else {
        Main.speed -= 20;
        }
    }

}
