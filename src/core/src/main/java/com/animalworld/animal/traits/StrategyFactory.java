package com.animalworld.animal.traits;

public interface StrategyFactory {
    
    public FeedingBehavior createFeedingBehavior(String feedingBehavior);
}
