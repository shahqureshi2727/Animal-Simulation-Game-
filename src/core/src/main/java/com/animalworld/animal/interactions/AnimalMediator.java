package com.animalworld.animal.interactions;

import com.animalworld.animal.Animal;
import com.animalworld.animal.traits.AnimalState;
import com.animalworld.board.Board;

/**
 * Interface defining the contract for mediating interactions between animals in the simulation.
 * This interface is part of the Mediator pattern implementation, centralizing all animal-to-animal
 * interactions and reducing direct coupling between animals.
 *
 * @author Shah Qureshi
 * @version 1.0
 * @since 2024
 */
public interface AnimalMediator {
    /**
     * Determines if two animals can interact based on their current state and position.
     *
     * @param source The animal initiating the interaction
     * @param target The animal being interacted with
     * @return true if the animals can interact, false otherwise
     */
    boolean canInteract(Animal source, Animal target);

    /**
     * Handles the interaction between two animals according to the simulation rules.
     *
     * @param source The animal initiating the interaction
     * @param target The animal being interacted with
     */
    void handleInteraction(Animal source, Animal target);

    /**
     * Finds the nearest animal to the source within a specified range.
     *
     * @param source The animal searching for nearby animals
     * @param range The maximum distance to search for other animals
     * @return The nearest animal within range, or null if no animal is found
     */
    Animal findNearestAnimal(Animal source, int range);

    /**
     * Determines if an animal is in immediate danger from nearby predators.
     *
     * @param animal The animal to check for danger
     * @return true if a larger predator is within visual range, false otherwise
     */
    boolean isInDanger(Animal animal);

    /**
     * Determines if an animal can hunt any nearby prey.
     *
     * @param animal The potential hunter to check
     * @return true if suitable prey is within visual range, false otherwise
     */
    boolean canHunt(Animal animal);

    /**
     * Determines the next appropriate state for an animal based on its environment.
     * This method centralizes state transition logic by considering:
     * - Nearby predators (triggers Fleeing state)
     * - Available prey (triggers Hunting state)
     * - No immediate threats or opportunities (triggers Foraging state)
     *
     * @param animal The animal whose state needs to be determined
     * @return The next appropriate AnimalState
     */
    AnimalState determineNextState(Animal animal);

}
