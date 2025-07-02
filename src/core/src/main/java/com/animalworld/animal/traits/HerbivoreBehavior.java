package com.animalworld.animal.traits;

import com.animalworld.animal.Animal;
import com.animalworld.board.Board;
import com.animalworld.board.TileInterface;

/**
 This class is a herbivore feeding behavior used by Animals for eating.
 @author Jake Simoes
 */
public class HerbivoreBehavior implements FeedingBehavior, Cloneable{
    private Board board = Board.getInstance();
    /**
     * Eats any plant life on the current tile.
     * @param animal
     * The animal object calling this method. (this)
     * @author Jake Simoes
     */
    public int eat(Animal animal) {
        //If the animal is on top of a grass tile, eat it.
        TileInterface spot = board.getTile((int) animal.getSprite().getX(), (int) animal.getSprite().getY());
        return spot.beEaten();
    }

    @Override
    public String toString() {
        return "Herbivore";
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
