package com.animalworld.animal.mutations;

import com.animalworld.animal.Animal;

/**
 * Decorator to enable an animal to hunt larger creatures by increasing their strength.
 * 
 * @author Jophene Campbell
 */

public class StrengthBoostDecorator extends AnimalDecorator {

    /**
     * Strength boost constructor that takes in the decorated animal object
     * 
     * @param decoratedAnimal
     */
    public StrengthBoostDecorator(Animal decoratedAnimal) {
        super(decoratedAnimal);
    }

    /**
     * Method that allows the animal to become a hunter
     * 
     * @return true to allow the animal to be identified as a hunter
     */
    @Override
    public boolean isHunter() {
        return true;
    }

    /**
     * Overidden method that specifies the way a hunter acts
     */
    @Override
    public void act() {
        // Add custom hunting behavior if desired.
        decoratedAnimal.act();
    }
}