package com.animalworld.menu;

/**
 * Concrete state of StartStop button when simulation is playing.
 * @author Elaina Rivers
 */

public class StartedState implements ButtonState {

    // Text of button is changed to "Stop" since there are things to stop.
    @Override
    public String changeText() {
        return "Stop";
    }

}
