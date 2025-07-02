package com.animalworld.animal.traits;

import com.animalworld.animal.Animal;
import com.animalworld.board.Board;

/**
 This class is a carnivore feeding behavior used by Animals for eating.
 @author Jake Simoes
 */
public class CarnivoreBehavior implements FeedingBehavior, Cloneable {
    private Board board = Board.getInstance();
    /**
     * Attempts to eat any animal on the current tile.
     * @param animal
     * The animal object calling this method. (this)
     * @author Jake Simoes
     */
    // public int eat(Animal animal) {
    //     int eaten = 0;
    //     //If the animal is on top of another, eat it.
    //     Animal prey = board.checkForAnimal(animal.getSprite().getX(), animal.getSprite().getY(), 1, animal);
    //     if (prey != null) {
    //         if (prey.getSize() < animal.getSize()) {
    //             System.out.println("NOM");
    //             eaten = prey.beEaten();
    //         }
    //     }
    //     return eaten;
    // }

    //Modified eat() method to use the mediator
    public int eat(Animal animal) {
        Animal prey = animal.getMediator().findNearestAnimal(animal, 1);
        if (prey != null) {
            animal.getMediator().handleInteraction(animal, prey);
            return prey.getNutritionalValue();
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Carnivore";
    }

    @Override
    public FeedingBehavior clone(){
        try {
           return (FeedingBehavior) super.clone();
        } catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
    }
}
