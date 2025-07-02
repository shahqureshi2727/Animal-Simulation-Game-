package com.animalworld.board;

import java.util.Objects;

/**
 * A state for a plant tile.
 * @author Jake Simoes
 */
public class GrassState implements TileState {
    private final PlantTile tile;

    public GrassState(PlantTile tile) {
        this.tile = tile;
        // Our default nutritional value.
        tile.nutritionalValue = 1;
        tile.setTexture("grass.png");
    }

    @Override
    public void act() {
        if (tile.nutritionalValue < tile.maxNutritionalValue) {
            tile.nutritionalValue++;
        }
    }

    @Override
    public int beEaten() {
        int nutritionalValue = tile.nutritionalValue;
        tile.changeState(new DirtState(tile));
        return nutritionalValue;
    }

    public GrassState(GrassState target, TileInterface targetTile) {
        if (target != null) {
            this.tile = (PlantTile) targetTile;
        } else {
            this.tile = null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrassState that = (GrassState) o;
        return Objects.equals(tile, that.tile);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tile);
    }

    @Override
    public GrassState clone(TileInterface targetTile) {
        return new GrassState(this, targetTile);
    }
}
