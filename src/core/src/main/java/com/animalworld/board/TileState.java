package com.animalworld.board;

public interface TileState {
    public void act();
    public int beEaten();
    public TileState clone(TileInterface targetTile);
}
