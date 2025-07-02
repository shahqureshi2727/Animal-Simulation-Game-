package com.animalworld.animal.traits;

import com.animalworld.animal.Animal;

/**
 * The ForagingState class represents a passive behavioral state where an animal is foraging for food.
 * This state implements the AnimalState interface as part of the State design pattern.
 *
 * <p>When an animal enters the foraging state, it:
 * <ul>
 *     <li>Adopts a random movement pattern to search for food</li>
 *     <li>Sets its feeding strategy to herbivorous (Plants)</li>
 *     <li>Actively searches and consumes plant-based resources</li>
 * </ul>
 *
 * @author Shah Qureshi
 * @version 1.0
 * @since 2024
 * @see AnimalState
 * @see Animal
 */
public class ForagingState implements AnimalState {

    /**
     * Executes the foraging state behavior on the given animal.
     * Sets the animal's movement to random and feeding strategy to herbivorous.
     * Uses the mediator to determine if state transition is needed after each action.
     *
     * @param animal the animal context on which the foraging behavior will be performed
     */
    @Override
    public void act(Animal animal) {
        // Set random movement behavior
        animal.setMovementStrategy(new RandomMovement());
        // The animal will move and eat as it does.
        for (int i = 0; i < animal.getMovementRate(); i++) {
            animal.move();
            animal.eat();
        }
        // Check for state change using mediator
        AnimalState nextState = animal.getMediator().determineNextState(animal);
        if (!nextState.getStateName().equals(getStateName())) {
            animal.setState(nextState);
        }
    }

    /**
     * Returns the name of this state.
     *
     * @return "Foraging" as a String
     */
    @Override
    public String getStateName() {
        return "Foraging";
    }

}
