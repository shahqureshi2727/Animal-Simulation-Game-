package com.animalworld.animal.traits;

import com.animalworld.animal.Animal;

/**
 This class is an omnivore feeding behavior used by Animals for eating.
 @author Jake Simoes
 */
public class OmnivoreBehavior implements FeedingBehavior, Cloneable {
    private final CarnivoreBehavior carniStrat;
    private final HerbivoreBehavior herbiStrat;

    public OmnivoreBehavior() {
        FeedingFactory feedingFact = new FeedingFactory();
        carniStrat = (CarnivoreBehavior) feedingFact.createFeedingBehavior("Meat");
        herbiStrat = (HerbivoreBehavior) feedingFact.createFeedingBehavior("Plants");
    }

    /**
     * Attempts to eat any animal on the current tile.
     *
     * @param animal The animal object calling this method. (this)
     * @author Jake Simoes
     */
    public int eat(Animal animal) {
        int nutrition;

        // Try to eat a plant first, if that fails switch to violence.
        nutrition = herbiStrat.eat(animal);
        if (nutrition == 0) {
            nutrition = carniStrat.eat(animal);
        }

        return nutrition;
    }

    @Override
    public String toString() {
        return "Omnivore";
    }

    @Override
    public FeedingBehavior clone() {
        try {
            return (FeedingBehavior) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
