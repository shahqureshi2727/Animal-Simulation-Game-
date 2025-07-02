package com.animalworld.board;

import com.badlogic.gdx.graphics.Texture;

/**
 * A proxy for the board to interact with PlantTiles.
 * Will hold out on initializing a new PlantTile until it is eaten.
 * P.S. I know this is an unholy combination of a singleton and a proxy, I think it makes more sense.
 */
public class PlantTileProxy implements TileInterface {
    static private PlantTile defaultPlantTile;
    private PlantTile realPlantTile;

    public PlantTileProxy(int nutritionalValue, int maxNutritionalValue, int regrowthTime) {
        if (defaultPlantTile == null) {
            defaultPlantTile = new PlantTile(nutritionalValue, maxNutritionalValue, regrowthTime);
            Board.getInstance().getTiles().add(defaultPlantTile);
        }
        realPlantTile = defaultPlantTile;
    }

    /**
     * Allows a tile to be eaten by an animal.
     * @return An integer representing how much nutrition was gained.
     */
    public int beEaten() {
        if (realPlantTile == defaultPlantTile) {
            realPlantTile = defaultPlantTile.clone();
            Board.getInstance().getTiles().add(realPlantTile);
        }
        return realPlantTile.beEaten();
    }

    /**
     * Allows a tile to react to board turns.
     */
    public void act() {
        if (realPlantTile.equals(defaultPlantTile)) {
            realPlantTile = defaultPlantTile;
        }
        realPlantTile.act();
    }

    public Texture getTexture() {
        return realPlantTile.getTexture();
    }
}
