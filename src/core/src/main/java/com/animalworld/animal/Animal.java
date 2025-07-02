package com.animalworld.animal;

import static java.lang.String.format;
import java.util.Objects;
import java.util.Random;

import com.animalworld.animal.interactions.AnimalMediator;
import com.animalworld.animal.traits.AnimalState;
import com.animalworld.animal.traits.FeedingBehavior;
import com.animalworld.animal.traits.FeedingFactory;
import com.animalworld.animal.traits.ForagingState;
import com.animalworld.animal.traits.MovementBehavior;
import com.animalworld.animal.traits.StrategyFactory;
import com.animalworld.board.Board;
import com.animalworld.board.Edible;
import com.badlogic.gdx.graphics.Texture;
import com.animalworld.animal.interactions.ConcreteAnimalMediator;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * This class provides an object for animals.
 *
 * @author Jake Simoes
 */
public class Animal implements Cloneable, Edible {
    // To be used for RNG
    Random rand = new Random();

    // Our animals name
    private String name;
    // How many actions will the animal perform?
    private int movementRate;
    // How many blocks can the animal scan around to see?
    private int visualRange;
    // How big is this animal (ranging from size 1-3)
    private int size;
    // How often will this animal mutate?
    private int evolutionRate;
    // How often will this animal reproduce?
    private int reproductionRate;
    // Can this animal take down animals larger than itself?
    private boolean isHunter;
    // Will this animal eat its own kind?
    private boolean isCannibal;

    // These are strategies that define different behaviors.
    private FeedingBehavior feedingStrategy;
    private MovementBehavior movementStrategy;

    // We need to store a sprite object that will store information for the
    // renderer.
    private Sprite animalSprite;

    // The current state of the animal
    private AnimalState currentState;

    // How many grams of protein
    private int nutritionalValue;

    // Hunger
    private int energy;

    // Mediator
    private AnimalMediator mediator;

    //feedingBehavior
    private String feedingBehavior;

    /**
     * A toString implementation.
     *
     * @return
     *         Returns a string containing information on the Animal and its
     *         attributes.
     */
    @Override
    public String toString() {
        return format("Hello! I am a %s!\n" +
                "Here's some cool information about me!\n" +
                "My movement rate is: %s\n" +
                "My visual range is: %s\n" +
                "My size is: %s\n" +
                "My evolution rate is: %s\n" +
                "My reproduction rate is: %s\n" +
                "I am%s a hunter\n" +
                "I am%s a cannibal\n" +
                "I am a %s \n",
                name, movementRate, visualRange, size, evolutionRate, reproductionRate,
                (isHunter) ? "" : " not", (isCannibal) ? "" : " not", feedingStrategy);
        // feedingStrategy.getName(), movementStrategy.getName());
    }

    /**
     * An equals implementation.
     *
     * @return
     *         Returns true if the passed object shares all its attributes with this
     *         one. Returns false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Animal animal = (Animal) o;
        return movementRate == animal.movementRate && visualRange == animal.visualRange && size == animal.size
                && evolutionRate == animal.evolutionRate && reproductionRate == animal.reproductionRate
                && isHunter == animal.isHunter && isCannibal == animal.isCannibal && Objects.equals(name, animal.name)
                && Objects.equals(feedingStrategy, animal.feedingStrategy)
                && Objects.equals(movementStrategy, animal.movementStrategy)
                && Objects.equals(animalSprite, animal.animalSprite);
    }

    /**
     * A hashCode implementation.
     *
     * @return
     *         Returns a hash code generated using all attributes.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, movementRate, visualRange, size, evolutionRate, reproductionRate,
                isHunter, isCannibal, feedingStrategy, movementStrategy, animalSprite);
    }

    /**
     * Constructor for an animal.
     *
     * @param movementRate
     *                         How many actions will the animal perform?
     * @param visualRange
     *                         How many blocks can the animal scan around to see?
     * @param size
     *                         How big is this animal (ranging from size 1-3)
     * @param evolutionRate
     *                         How often will this animal mutate?
     * @param reproductionRate
     *                         How often will this animal reproduce?
     * @param isHunter
     *                         Can this animal take down animals larger than itself?
     * @param isCannibal
     *                         Will this animal eat its own kind?
     */
    public Animal(String name, int movementRate, int visualRange, int size, int evolutionRate, int reproductionRate,
            boolean isHunter, boolean isCannibal, int nutritionalValue, Texture texture, String feedingBehavior) {
        Board.getInstance().getAnimals().add(this);
        this.name = name;
        this.movementRate = movementRate;
        this.visualRange = visualRange;
        this.size = size;
        this.evolutionRate = evolutionRate;
        this.reproductionRate = reproductionRate;
        this.isHunter = isHunter;
        this.isCannibal = isCannibal;
        this.nutritionalValue = nutritionalValue;
        this.energy = 15;
        StrategyFactory sf = new FeedingFactory();
        this.feedingStrategy = sf.createFeedingBehavior(feedingBehavior);

        animalSprite = new Sprite(texture);
        animalSprite.setSize(1, 1);

        // Set the initial state to Foraging; this will could changed later
        this.currentState = new ForagingState();

        // Initialize mediator using singleton
        this.mediator = ConcreteAnimalMediator.getInstance();
    }

    /**
     * Implements the clone method for the Prototype pattern
     *
     * @return A copy of the animal object
     */
    @Override
    public Animal clone() {
        try {
            Animal animalCopy = (Animal) super.clone();

            // clone objects
            animalCopy.animalSprite = new Sprite(this.animalSprite);

            // Assuming feedingBehavior and movementBehavior implement Cloneable
            animalCopy.feedingStrategy = this.feedingStrategy.clone();
            animalCopy.movementStrategy = this.movementStrategy.clone();

            // Use the singleton mediator instance for the clone
            animalCopy.mediator = ConcreteAnimalMediator.getInstance();
            // Reset our energy
            animalCopy.energy = 15;

            Board.getInstance().getAnimals().add(animalCopy);
//            switch (rand.nextInt(Main.mutationRate*100)) {
//                case 0:
//                    animalCopy = new EnhancedSpeedDecorator(animalCopy);
//                    break;
//                case 1:
//                    animalCopy = new EnhancedVisionDecorator(animalCopy, 2);
//                    break;
//                case 2:
//                    animalCopy = new StrengthBoostDecorator(animalCopy);
//                    break;
//            }
            return animalCopy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }

    @Override
    public int beEaten() {
        Board.getInstance().getAnimals().remove(this);
        return nutritionalValue;
    }

    /**
     * Calls the movement strategy.
     */
    public void move() {
        movementStrategy.move(this);
    }

    /**
     * Calls the feeding strategy.
     */
    public void eat() {
        energy += feedingStrategy.eat(this);
    }

    /**
     * Randomly changes some one of an animals attributes.
     */
    private void mutate() {
        // #TODO: Decide how we want mutations to work.
    }

    /**
     * Induces an animal to move and eat.
     * To be called every board iteration.
     */
    public void act() {

        if ((rand.nextInt(100) == 0) && (energy > 50)) {
            this.clone();
        }
        if (energy < 0) {
            this.beEaten();
        }
        energy--;
        currentState.act(this);
    }

    // New helper method for states to use
    public void performActions() {

    }

    public AnimalState getState() {
        return currentState;
    }

    public String getName() {
        return name;
    }

    public int getMovementRate() {
        return movementRate;
    }

    public int getVisualRange() {
        return visualRange;
    }

    public int getSize() {
        return size;
    }

    public int getEvolutionRate() {
        return evolutionRate;
    }

    public int getReproductionRate() {
        return reproductionRate;
    }

    public boolean isHunter() {
        return isHunter;
    }

    public boolean isCannibal() {
        return isCannibal;
    }

    public Sprite getSprite() {
        return animalSprite;
    }

    public String getFeedingBehavior() {
        return feedingBehavior;
    }

    public FeedingBehavior getFeedingStrategy() {
        return feedingStrategy;
    }

    public MovementBehavior getMovementStrategy() {
        return movementStrategy;
    }

    public AnimalMediator getMediator() {
        return mediator;
    }

    public void setState(AnimalState newState) {
        this.currentState = newState;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMovementRate(int movementRate) {
        this.movementRate = movementRate;
    }

    public void setVisualRange(int visualRange) {
        this.visualRange = visualRange;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setEvolutionRate(int evolutionRate) {
        this.evolutionRate = evolutionRate;
    }

    public void setReproductionRate(int reproductionRate) {
        this.reproductionRate = reproductionRate;
    }

    public void setHunter(boolean hunter) {
        isHunter = hunter;
    }

    public void setCannibal(boolean cannibal) {
        isCannibal = cannibal;
    }

    public void setFeedingStrategy(String feedingStrategy) {
        StrategyFactory sf = new FeedingFactory();
        this.feedingStrategy = sf.createFeedingBehavior(feedingStrategy);
    }

    public void setMovementStrategy(MovementBehavior movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public void setAnimalSprite(Sprite animalSprite) {
        this.animalSprite = animalSprite;
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(int nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

}
