package com.animalworld.animal.mutations;

import com.animalworld.animal.Animal;

/**
 * Decorator to increase an animal's visual range.
 * 
 * @author Jophene Campbell
 */

public class EnhancedVisionDecorator extends AnimalDecorator {
    private int additionalVisualRange = 2;  //number that the animal's vision will be increased by

    /**
     * Enhanced Vision constructor that takes in the decorated animal object and the additional vision range
     * 
     * @param decoratedAnimal
     * @param additionalVisualRange
     */
    public EnhancedVisionDecorator(Animal decoratedAnimal, int additionalVisualRange) {
        super(decoratedAnimal);
        this.additionalVisualRange = additionalVisualRange;
    }

    /**
     * Overidden method that increases the vision range the animal has
     * 
     * @return the new vision range
     */
    @Override
    public int getVisualRange() {
        return decoratedAnimal.getVisualRange() + additionalVisualRange;
    }
}