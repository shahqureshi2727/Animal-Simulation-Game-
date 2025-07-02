package com.animalworld.menu;

/**
 * Invoker of Command pattern. Initiates requests.
 * Will likely take the form of a UI/menu in the future.
 * @author Elaina Rivers
 */
public class Invoker {
    private Command command;

    /**
    * Sets the concrete command to be carried out.
    */
    public void setCommand(Command thisCommand) {
        this.command = thisCommand;
    }

    /**
    * Executes the command.
    */
    public void executeCommand() {
        command.execute();
    }
}
