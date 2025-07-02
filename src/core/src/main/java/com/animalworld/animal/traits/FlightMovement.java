package com.animalworld.animal.traits;

import com.animalworld.animal.Animal;
import com.animalworld.board.Board;
import java.util.Random;

/**
 * The concrete behavior of Flight Movement. One of the strategies for
 * MovementBehavior.
 *
 * @author Shah Qureshi
 * @author Jake Simoes
 * @version 1.0
 * @since 2024
 */
public class FlightMovement implements MovementBehavior {
    /**
     * The concrete implementation of the hook method 'move()'. The animal with move
     * away from other animals. How far they move si still being developed.
     *
     * @param animal The animal with the new position based upon the strategy of
     *               random movement.
     */
    @Override
    public void move(Animal animal) {
        Board board = Board.getInstance();
        float currentX = animal.getSprite().getX();
        float currentY = animal.getSprite().getY();

        // Variables to store closest prey position
        float closestPredatorX = -1;
        float closestPredatorY = -1;
        float deltaX;
        float deltaY;
        Animal predator = board.checkForAnimal(currentX, currentY, animal.getVisualRange(), animal);

        if (predator != null) {
            // If a predator is found, move away from it
            closestPredatorX = predator.getSprite().getX();
            closestPredatorY = predator.getSprite().getY();

            // Could happen during the beginning of the simulation.
            if ((closestPredatorX == currentX) && (closestPredatorY == currentY)) {
                deltaX = ((currentX + 1) < board.getWidth()) ? 1 : -1;
                deltaY = ((currentY + 1) < board.getLength()) ? 1 : -1;
            } else {
                // Calculate direction to move (-1, 0, or 1 for each axis)
                // Note the reversed direction (moving away instead of towards)
                deltaX = -Math.signum(closestPredatorX - currentX);
                deltaY = -Math.signum(closestPredatorY - currentY);
            }


            // Update position with boundary checks
            float newX = Math.max(0, Math.min(currentX + deltaX, board.getWidth()-1));
            float newY = Math.max(0, Math.min(currentY + deltaY, board.getLength()-1));

            animal.getSprite().setPosition(newX, newY);
        }


    }

    @Override
    public String getName() {
        return "Flight";
    }

    @Override
    public MovementBehavior clone(){
        try {
            return (MovementBehavior) super.clone();
        } catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
    }

}
