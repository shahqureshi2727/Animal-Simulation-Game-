package com.animalworld.SimulationStates;

import java.util.ArrayList;
import java.util.List;

/**
 * Caretaker for simulation memento. Holds a list of mementos that can be restored.
 *
 * @author James Marshall
 */
public class SimulationCaretaker {
    
    private List<SimulationMemento> history = new ArrayList<>();

    /**
     * Add new memento to list of saved mementos
     *
     * @param memento A new memento being added to the list.
     */
    public void addMemento(SimulationMemento memento) {
        history.add(memento);
    }

    /**
     * Uses last saved memento in list for restoring simulation state
     *
     * @return Last memento saved in list, or NULL if list is empty.
     */
    public SimulationMemento getMemento() {
        int indexOfLast;
        if (! (history.isEmpty()) ) {
            indexOfLast = history.size() -1;

            SimulationMemento last = history.get(indexOfLast);
            history.remove(indexOfLast);

            return last;
        } else {
            return null;
        }
    }
}
