package com.animalworld.animal.interactions;

import com.animalworld.animal.Animal;
import com.animalworld.board.Board;
import com.animalworld.animal.traits.AnimalState;
import com.animalworld.animal.traits.FleeingState;
import com.animalworld.animal.traits.HuntingState;
import com.animalworld.animal.traits.ForagingState;

/**
 * Concrete implementation of the AnimalMediator interface that manages all animal-to-animal interactions.
 * This class centralizes interaction logic and reduces coupling between animals and the board.
 *
 * @author Shah Qureshi
 * @version 1.0
 * @since 2024
 */
public class ConcreteAnimalMediator implements AnimalMediator {

    private static ConcreteAnimalMediator instance;
    private final Board board;

    private ConcreteAnimalMediator() {
        this.board = Board.getInstance();
    }

    public static ConcreteAnimalMediator getInstance() {
        if (instance == null) {
            instance = new ConcreteAnimalMediator();
        }
        return instance;
    }

    /**
     * Determines if two animals can interact based on their positions and visual range.
     *
     * @param source The animal initiating the interaction
     * @param target The animal being interacted with
     * @return true if the animals can interact, false otherwise
     */
    @Override
    public boolean canInteract(Animal source, Animal target) {
        if (source == null || target == null) return false;
        
        float distance = calculateDistance(source, target);
        return distance <= source.getVisualRange();
    }

    /**
     * Handles the interaction between two animals, such as predator-prey relationships.
     * This method is called when one animal attempts to interact with another.
     *
     * @param source The animal initiating the interaction
     * @param target The animal being interacted with
     */
    @Override
    public void handleInteraction(Animal source, Animal target) {
        if (!canInteract(source, target)) return;

        // Handle predator-prey interaction
        if (source.isHunter() && source.getSize() > target.getSize()) {
            source.setEnergy(source.getEnergy() + target.beEaten());
        }
    }

    /**
     * Finds the nearest animal to the source animal within a specified range.
     *
     * @param source The animal searching for nearby animals
     * @param range The maximum distance to search for other animals
     * @return The nearest animal within range, or null if no animal is found
     */
    @Override
    public Animal findNearestAnimal(Animal source, int range) {
        return board.checkForAnimal(
            source.getSprite().getX(),
            source.getSprite().getY(),
            range,
            source
        );
    }

    /**
     * Calculates the Euclidean distance between two animals on the board.
     *
     * @param a1 The first animal
     * @param a2 The second animal
     * @return The distance between the two animals
     */
    private float calculateDistance(Animal a1, Animal a2) {
        float x1 = a1.getSprite().getX();
        float y1 = a1.getSprite().getY();
        float x2 = a2.getSprite().getX();
        float y2 = a2.getSprite().getY();
        
        return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /**
     * Determines if an animal is in immediate danger by checking for nearby predators.
     * An animal is considered in danger if there is a larger hunter within its visual range.
     *
     * @param animal The animal to check for danger
     * @return true if a threatening predator is nearby, false otherwise
     */
    @Override
    public boolean isInDanger(Animal animal) {
        Animal nearestAnimal = findNearestAnimal(animal, animal.getVisualRange());
        if (nearestAnimal == null) return false;
        
        return nearestAnimal.isHunter() && 
               nearestAnimal.getSize() > animal.getSize();
    }
    
    /**
     * Determines if an animal can successfully hunt nearby prey.
     * Hunting is possible if the animal is a hunter and there is smaller prey within visual range.
     *
     * @param animal The potential hunter to check
     * @return true if suitable prey is available, false otherwise
     */
    @Override
    public boolean canHunt(Animal animal) {
        if (!animal.isHunter()) return false;
        
        Animal prey = findNearestAnimal(animal, animal.getVisualRange());
        return prey != null && animal.getSize() > prey.getSize();
    }
    
    /**
     * Evaluates the animal's situation and determines the most appropriate state.
     * Priority order:
     * 1. Fleeing - if in danger from predators
     * 2. Hunting - if prey is available and animal can hunt
     * 3. Foraging - default state when no immediate threats or opportunities exist
     *
     * @param animal The animal whose state needs to be determined
     * @return The next appropriate AnimalState based on environmental conditions
     */
    @Override
    public AnimalState determineNextState(Animal animal) {
        if (isInDanger(animal)) {
            return new FleeingState();
        } else if (canHunt(animal)) {
            return new HuntingState();
        } else {
            return new ForagingState();
        }
    }
}
