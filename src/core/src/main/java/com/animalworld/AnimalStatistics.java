package com.animalworld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.animalworld.animal.Animal;
import com.animalworld.animal.traits.FeedingBehavior;
import com.animalworld.board.Board;

public class AnimalStatistics {
    public static void displayAnimalStats() {
        // Get all animals from the board
        List<Animal> animals = Board.getInstance().getAnimals();

        // Create a map to store animal types and their counts
        // Key: AnimalType (custom class to hold traits), Value: Count
        Map<AnimalType, Integer> animalCounts = new HashMap<>();

        // Count animals by type
        for (Animal animal : animals) {
            AnimalType type = new AnimalType(
                animal.getName(),
                animal.getFeedingStrategy(),
                animal.getMovementStrategy().getName(),
                animal.getMovementRate(),
                animal.getSize()
            );
            animalCounts.merge(type, 1, Integer::sum);
        }

        // Sort and display results
        animalCounts.entrySet().stream()
            .sorted(Map.Entry.<AnimalType, Integer>comparingByKey()
                .thenComparing(Map.Entry.comparingByValue()))
            .forEach(entry -> System.out.printf("%s - %d creatures%n",
                entry.getKey(), entry.getValue()));
    }
}

class AnimalType implements Comparable<AnimalType> {
    private final String name;
    private final FeedingBehavior FeedingBehavior;
    private final String movementStrategy;
    private final int movementRate;
    private final int size;

    public AnimalType(String name, FeedingBehavior FeedingBehavior, String movementStrategy,
                     int movementRate, int size) {
        this.name = name;
        this.FeedingBehavior = FeedingBehavior;
        this.movementStrategy = movementStrategy;
        this.movementRate = movementRate;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %d moves, size %d",
            name, FeedingBehavior, movementStrategy, movementRate, size);
    }



    @Override
    public int compareTo(AnimalType other) {
        int result = this.name.compareTo(other.name);
        if (result != 0) return result;

        result = this.FeedingBehavior.toString().compareTo(other.FeedingBehavior.toString());
        if (result != 0) return result;

        result = this.movementStrategy.compareTo(other.movementStrategy);
        if (result != 0) return result;

        result = Integer.compare(this.movementRate, other.movementRate);
        if (result != 0) return result;

        return Integer.compare(this.size, other.size);
    }

    // Add equals() and hashCode() methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalType that = (AnimalType) o;
        return movementRate == that.movementRate &&
               size == that.size &&
               Objects.equals(FeedingBehavior, that.FeedingBehavior) &&
               Objects.equals(movementStrategy, that.movementStrategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(FeedingBehavior, movementStrategy, movementRate, size);
    }
}
