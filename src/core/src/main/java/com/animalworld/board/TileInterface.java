package com.animalworld.board;

import com.badlogic.gdx.graphics.Texture;

/**
 * This interface serves to define what methods are necessary for a proxy with the Board.
 */
public interface TileInterface {
    public int beEaten();
    public void act();
    public Texture getTexture();
}
