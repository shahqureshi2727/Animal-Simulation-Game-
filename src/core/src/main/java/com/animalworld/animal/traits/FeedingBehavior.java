package com.animalworld.animal.traits;

import com.animalworld.animal.Animal;

public interface FeedingBehavior extends Cloneable{
    public int eat(Animal animal);

    FeedingBehavior clone ();
}
