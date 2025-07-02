package com.animalworld.animal.traits;

import com.animalworld.animal.Animal;
import com.animalworld.board.Board;

import java.util.Random;

/**
 * The concrete behavior of RandomMovement. One of the strategies for
 * MovementBehavior.
 *
 * @author Shah Qureshi
 * @version 1.0
 * @since 2024
 */
public class RandomMovement implements MovementBehavior {

    /** Variable to determine random position. */
    private final Random random = new Random();

    /**
     * The concrete implementation of the hook method 'move()'. The animal with move
     * one space, in any direction, so long they don't go outside the border.
     *
     * @param animal The animal with the new position based upon the strategy of
     *               random movement.
     */
    @Override
    public void move(Animal animal) {
        // Current position from the animal's sprite
        float x = animal.getSprite().getX();
        float y = animal.getSprite().getY();

        // Randomly decide new direction: -1, 0, or 1 for x and y axis
        float deltaX = random.nextInt(3) - 1;
        float deltaY = random.nextInt(3) - 1;

        // Calculate new position with boundary checks
        float newX = Math.max(0, Math.min(x + deltaX, Board.getInstance().getWidth() - 1));
        float newY = Math.max(0, Math.min(y + deltaY, Board.getInstance().getLength() - 1));

        // Update the position
        animal.getSprite().setPosition(newX, newY);
    }

    @Override
    public String getName() {
        return "Random Movement";
    }

    public MovementBehavior clone(){
        try {
            return (MovementBehavior) super.clone();
        } catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
    }

}
