package com.animalworld.menu;

/**
 * Context for Button State.
 * @author Elaina Rivers
 */

public class ButtonContext implements ButtonState {

    private ButtonState buttonState;

    public void setButtonState(ButtonState thisButtonState) {
        this.buttonState = thisButtonState;
    }

    public ButtonState getButtonState() {
        return this.buttonState;
    }

    @Override
    public String changeText() {
        return this.buttonState.changeText();
    }

}
