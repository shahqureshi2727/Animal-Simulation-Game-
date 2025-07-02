package com.animalworld.animal.mutations;

import com.animalworld.animal.Animal;

/**
 * Base decorator for modifying animal traits dynamically as they evolve.
 * This allows the addition or removal of abilities and traits.
 *
 * @author Jophene Campbell
 */

public abstract class AnimalDecorator extends Animal {
    protected Animal decoratedAnimal;   //animal object that will receive the modifications

    /**
     * Animal decorator constructor that takes in the decorated animal object
     *
     * @param decoratedAnimal   //animal object to be modified
     */
    public AnimalDecorator(Animal decoratedAnimal) {
        super(decoratedAnimal.getName(), decoratedAnimal.getMovementRate(), decoratedAnimal.getVisualRange(),
                decoratedAnimal.getSize(), decoratedAnimal.getEvolutionRate(), decoratedAnimal.getReproductionRate(),
                decoratedAnimal.isHunter(), decoratedAnimal.isCannibal(), decoratedAnimal.getNutritionalValue(), decoratedAnimal.getSprite().getTexture(), decoratedAnimal.getFeedingBehavior());
        this.decoratedAnimal = decoratedAnimal;
    }

    /**
     * Method that overrides the act method from the base animal class
     */
    @Override
    public void act() {
        decoratedAnimal.act();
    }

    public void move() {
        decoratedAnimal.move();
    }
}
