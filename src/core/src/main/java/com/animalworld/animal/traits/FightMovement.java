package com.animalworld.animal.traits;

import com.animalworld.animal.Animal;
import com.animalworld.board.Board;
import java.util.Random;

/**
 * The concrete behavior of Fight Movement. One of the strategies for
 * MovementBehavior.
 *
 * @author Shah Qureshi
 * @author Jake Simoes
 * @version 1.0
 * @since 2024
 */
public class FightMovement implements MovementBehavior {

    /**
     * The concrete implementation of the hook method 'move()'. The animal with move
     * closer to other animals. How close they move and the proximity of the other
     * animals is still being developed
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
        float closestPreyX = -1;
        float closestPreyY = -1;

        //To use mediator, replace following line with:
        Animal prey = animal.getMediator().findNearestAnimal(animal, animal.getVisualRange());
        //Animal prey = board.checkForAnimal(currentX, currentY, animal.getVisualRange(), animal);


        /// If prey found, move towards it
        if (prey != null) {
            closestPreyX = prey.getSprite().getX();
            closestPreyY = prey.getSprite().getY();

            float deltaX = Math.signum(closestPreyX - currentX);
            float deltaY = Math.signum(closestPreyY - currentY);

            // Update position with boundary checks
            float newX = Math.max(0, Math.min(currentX + deltaX, board.getWidth()-1));
            float newY = Math.max(0, Math.min(currentY + deltaY, board.getLength()-1));

            animal.getSprite().setPosition(newX, newY);
        } else {
            // If no huntable prey is found, delegate to random movement
            RandomMovement randomMove = new RandomMovement();
            randomMove.move(animal);
        }
    }


    @Override
    public String getName() {
        return "Fight";
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
