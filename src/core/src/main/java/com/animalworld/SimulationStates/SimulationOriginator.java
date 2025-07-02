package com.animalworld.SimulationStates;

import java.util.List;

import com.animalworld.animal.Animal;
import com.animalworld.board.TileInterface;

/**
 * Orginator for saving and restoring state of simulation.
 *
 * @author James Marshall
 */
public class SimulationOriginator {
    private TileInterface[][] boardMatrix;
    private List<TileInterface> uniqueTileList;
    private List<Animal> animalList;

    /**
     * Originator constructor. Takes in data to be saved to memento
     *
     * @param
     */
    public SimulationOriginator(TileInterface[][] boardMatrix, List<TileInterface> uniqueTileList, List<Animal> animalList) {
        this.boardMatrix = boardMatrix;
        this.uniqueTileList = uniqueTileList;
        this.animalList = animalList;
    }

    /**
     * Creates the memento with the data that will be saved to the state.
     *
     * @return New memento with data saved from the current state of the simulation.
     */
    public SimulationMemento createMemento() {
        return new SimulationMemento(this.boardMatrix, this.uniqueTileList, this.animalList);
    }

    /**
     * Restores the state of the simulation using a previously created memento.
     *
     * @param memento Memento previously created. Data from here is used to restore the state of the simulation
     */
    public void restoreFromMemento(SimulationMemento memento) {
        this.boardMatrix = memento.getBoard();
        this.uniqueTileList = memento.getTiles();
        this.animalList = memento.getAnimalList();
    }

    public TileInterface[][] getBoard() {
        return this.boardMatrix;
    }

    public List<TileInterface> getTiles() {
        return this.uniqueTileList;
    }

    public List<Animal> getAnimalList() {
        return this.animalList;
    }
}
