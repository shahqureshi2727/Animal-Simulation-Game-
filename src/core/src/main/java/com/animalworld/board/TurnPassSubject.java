package com.animalworld.board;

/**
 * Concreate subject for when a turn passes in the simulation
 * 
 * @author James Marshall
*/
public class TurnPassSubject extends TurnSubject{
    
    /**
     * Is called or calls the next turn, which notifies all of the observers in the list.
     * 
    */
    public void turnPassed(Board board) {
        notifyObservers(board);
    }
}