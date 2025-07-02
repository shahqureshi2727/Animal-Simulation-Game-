package com.animalworld.board;

import java.util.ArrayList;
import java.util.List;

/**
 * A simulation Turn subject for that can be observed.
 * 
 * @author James Marshall
*/
public abstract class TurnSubject {
    private List<BoardObserver> observers;

    /**
     * Adds a new observer
     * 
     * @param observer New observer
    */
    public void attach(BoardObserver observer) {
        observers = new ArrayList<>();
    }

    /**
     * Remover observer from the list of observers
     * 
     * @param observer Observer to be removed
    */
    public void detach(BoardObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notify all attahed observers
     * 
    */
    public void notifyObservers(Board board) {
        for(BoardObserver observer : observers) {
            observer.update(board);
        }
    }
}