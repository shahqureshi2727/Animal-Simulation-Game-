package com.animalworld.animal.traits;

import com.animalworld.animal.Animal;

/**
 * The FleeingState class represents a defensive behavioral state where an animal is escaping from danger.
 * This state implements the AnimalState interface as part of the State design pattern.
 *
 * <p>When an animal enters the fleeing state, it:
 * <ul>
 *     <li>Adopts a flight movement behavior for rapid escape</li>
 *     <li>Suspends feeding behavior while fleeing</li>
 *     <li>Prioritizes escape over other activities</li>
 * </ul>
 *
 * @author Shah Qureshi
 * @version 1.0
 * @since 2024
 * @see AnimalState
 * @see Animal
 */
public class FleeingState implements AnimalState {

    /**
     * Executes the fleeing state behavior on the given animal.
     * Sets the animal's movement to fleeing and feeding strategy to none.
     * Uses the mediator to determine if state transition is needed after movement.
     *
     * @param animal the animal context on which the fleeing behavior will be performed
     */
    @Override
    public void act(Animal animal) {
        // Set fleeing movement behavior
        animal.setMovementStrategy(new FlightMovement());
        // The animal will move without trying to eat.
        for (int i = 0; i < animal.getMovementRate(); i++) {
            animal.move();
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
     * @return "Fleeing" as a String
     */
    @Override
    public String getStateName() {
        return "Fleeing";
    }

}