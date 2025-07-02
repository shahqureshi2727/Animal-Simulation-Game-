package com.animalworld.board;

import com.animalworld.animal.Animal;
import com.animalworld.graphics.TextureSingleton;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Objects;

/**
 * A plant tile.
 * @author Jake Simoes
 */
public class PlantTile implements TileInterface, Edible, Cloneable {
    private Texture currentTexture;
    // The tiles internal state
    private TileState state;
    // When eaten, how nutritious is this tile?
    protected int nutritionalValue;
    // If it grows, how big can it grow to?
    protected int maxNutritionalValue;
    // If it is eaten, how long until it regrows?
    protected int regrowthTime;


    public PlantTile(int nutritionalValue, int maxNutritionalValue, int regrowthTime) {
        this.nutritionalValue = nutritionalValue;
        this.maxNutritionalValue = maxNutritionalValue;
        this.regrowthTime = regrowthTime;
        this.state = new GrassState(this);
    }

    /**
     * Changes the internal state of the tile
     * @param state The new state.
     */
    public void changeState(TileState state) {
        this.state = state;
    }

    /**
     * Allows a tile to be eaten by an animal.
     * @return An integer representing how much nutrition was gained.
     */
    @Override
    public int beEaten() {
        return state.beEaten();
    }

    /**
     * Allows a tile to react to board turns.
     */
    @Override
    public void act() {
        state.act();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlantTile plantTile = (PlantTile) o;
        return nutritionalValue == plantTile.nutritionalValue && maxNutritionalValue == plantTile.maxNutritionalValue && Objects.equals(state, plantTile.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, nutritionalValue, maxNutritionalValue);
    }

    public Texture getTexture() {
        return currentTexture;
    }

    public void setTexture(String textureName) {
        this.currentTexture = TextureSingleton.getInstance().getTexture(textureName);
    }

    /**
     * A copy method used for implementing cloning.
     * @param target The PlantTile to be cloned.
     */
    public PlantTile(PlantTile target) {
        if (target != null) {
            this.currentTexture = target.currentTexture;
            this.state = target.state.clone(this);
            this.nutritionalValue = target.nutritionalValue;
            this.maxNutritionalValue = target.maxNutritionalValue;
            this.regrowthTime = target.regrowthTime;
        }
    }

    @Override
    public PlantTile clone() {
        return new PlantTile(this);
    }
}
