package com.animalworld.board;

import java.util.Objects;

/**
 * A state for a plant tile.
 * @author Jake Simoes
 */
public class DirtState implements TileState {
    private final PlantTile tile;

    public DirtState(PlantTile tile) {
        this.tile = tile;
        // We can use our nutritionalValue as a counter;
        tile.nutritionalValue = 0;
        tile.setTexture("dirt.png");
    }

    @Override
    public void act() {
        if (tile.nutritionalValue < tile.regrowthTime) {
            tile.nutritionalValue++;
        } else {
            tile.changeState(new GrassState(tile));
        }
    }

    @Override
    public int beEaten() {
        return 0;
    }

    public DirtState(DirtState target, TileInterface targetTile) {
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
        DirtState dirtState = (DirtState) o;
        return Objects.equals(tile, dirtState.tile);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tile);
    }

    @Override
    public DirtState clone(TileInterface targetTile) {
        return new DirtState(this, targetTile);
    }
}
