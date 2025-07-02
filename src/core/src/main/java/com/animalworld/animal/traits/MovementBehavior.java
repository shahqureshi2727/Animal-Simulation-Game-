package com.animalworld.animal.traits;

import com.animalworld.animal.Animal;

/**
 * Interface that outlines the MovementBehavior strategy of the 'move()' method.
 * 
 * @author Shah Qureshi & Jake Simoes
 * @version 1.0
 * @since 2024
 */
public interface MovementBehavior extends Cloneable{
    /**
     * The hook method that updates the movment of an animal with a specific
     * strategy.
     * 
     * @param animal The animal with the updated movement.
     */
    public void move(Animal animal);

    public String getName();

    MovementBehavior clone();
}
