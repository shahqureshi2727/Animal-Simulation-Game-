package com.animalworld.animal.traits;

public class FeedingFactory implements StrategyFactory{

    @Override
    public FeedingBehavior createFeedingBehavior(String feedingBehavior) {
        FeedingBehavior fb;
        if (feedingBehavior == "Plants") {
            fb = new HerbivoreBehavior();

        } else if (feedingBehavior == "Meat"){
            fb = new CarnivoreBehavior();

        } else if (feedingBehavior == "Both"){
            fb = new OmnivoreBehavior();

        } else {
            // Change when behavior is implemented.
            fb = null;
        }

        return fb;
    }


}
