package com.animalworld.SimulationStates;

import java.util.List;

import com.animalworld.animal.Animal;
import com.animalworld.board.TileInterface;

/**
 * Memento for saving and restoring state of simulation.
 *
 * @author James Marshall
 */
public class SimulationMemento {
    private TileInterface[][] boardMatrix;
    private List<TileInterface> uniqueTileList;
    private List<Animal> animalList;

    /**
     * Creates memento instance with the data of the current state of the simulation
     *
     * @param boardMatrix 2D Array of board
     * @param uniqueTileList List of tiles
     * @param animalList ArrayList of animals on board
     */
    public SimulationMemento(TileInterface[][] boardMatrix, List<TileInterface> uniqueTileList, List<Animal> animalList) {
        this.boardMatrix = boardMatrix;
        this.uniqueTileList = uniqueTileList;
        this.animalList = animalList;
    }

    public TileInterface[][] getBoard() {
        return boardMatrix;
    }

    public List<TileInterface> getTiles() {
        return uniqueTileList;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }
}
