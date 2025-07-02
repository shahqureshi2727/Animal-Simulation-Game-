package com.animalworld.animal.mutations;

import com.animalworld.animal.Animal;

/**
 * Decorator to enhance an animal's speed, increasing its movement rate.
 * 
 * @author Jophene Campbell
 */

public class EnhancedSpeedDecorator extends AnimalDecorator {
    
    private static final int SPEED_BOOST = 2; // Configurable speed multiplier
    
    /**
     * Enhanced speed constrcutor that takes the decorated animal and additional speed
     * 
     * @param decoratedAnimal   //animal object to be modified 
     */
    public EnhancedSpeedDecorator(Animal decoratedAnimal) {
        super(decoratedAnimal);
    }

    /**
     * Overidden method that gets the new movement rate of the animal
     * 
     * @return the new movement rate
     */
    @Override
    public int getMovementRate() {
        return decoratedAnimal.getMovementRate() * SPEED_BOOST;
    }

    /**
     * Overidden act method that increases the speed of how the animal moves
     */
    @Override
    public void act() {
        decoratedAnimal.act();
    }
}