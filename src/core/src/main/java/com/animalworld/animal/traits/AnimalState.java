package com.animalworld.animal.traits;

import com.animalworld.animal.Animal;

/**
 * The AnimalState interface represents a state in the State design pattern for managing animal behaviors.
 * Each concrete state implementation defines how an animal behaves in that particular state
 * (e.g., Sleeping, Hunting, Eating, etc.).
 * 
 * <p>This interface is part of the State pattern implementation where:
 * <ul>
 *     <li>Context: The {@link Animal} class that maintains a reference to the current state</li>
 *     <li>State: This {@code AnimalState} interface defining the state behavior contract</li>
 *     <li>Concrete States: Various implementations of this interface representing different animal states</li>
 * </ul>
 * 
 * @author Shah Qureshi
 * @version 1.0
 * @since 2024
 */
public interface AnimalState {
    /**
     * Defines the behavior of an animal when it is in this state.
     * This method is called to execute state-specific behavior.
     *
     * @param animal the animal context on which the state behavior will be performed
     */
    void act(Animal animal);

    /**
     * Returns the name of the current state.
     * Useful for debugging and logging purposes.
     *
     * @return a String representing the name of the state
     */
    String getStateName();
}