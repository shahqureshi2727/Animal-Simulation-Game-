package com.animalworld.animal.traits;

import com.animalworld.animal.Animal;

/**
 * The HuntingState class represents an aggressive behavioral state where an
 * animal is actively hunting.
 * This state implements the AnimalState interface as part of the State design
 * pattern.
 *
 * <p>
 * When an animal enters the hunting state, it:
 * <ul>
 * <li>Adopts an aggressive movement behavior (FightMovement)</li>
 * <li>Sets its feeding strategy to carnivorous (Meat)</li>
 * <li>Executes these behavioral changes through the animal context</li>
 * </ul>
 *
 * @author Shah Qureshi
 * @version 1.0
 * @since 2024
 * @see AnimalState
 * @see Animal
 */
public class HuntingState implements AnimalState {

    /**
     * Executes the hunting state behavior on the given animal.
     * Sets the animal's movement to aggressive and feeding strategy to carnivorous.
     * Uses the mediator to determine if state transition is needed after each action.
     *
     * @param animal the animal context on which the hunting behavior will be
     *               performed
     */
    @Override
    public void act(Animal animal) {
        // Set aggressive movement behavior
        animal.setMovementStrategy(new FightMovement());
        // The animal will move and try to eat as it does.
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
     * @return "Hunting" as a String
     */
    @Override
    public String getStateName() {
        return "Hunting";
    }
}
