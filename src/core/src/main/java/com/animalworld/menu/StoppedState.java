package com.animalworld.menu;

/**
 * Concrete state of StartStop button when simulation is stopped.
 * @author Elaina Rivers
 */

public class StoppedState implements ButtonState {

    // Text of button is changed to "Start" since everything is already stopped.
    @Override
    public String changeText() {
        return "Start";
    }

}
