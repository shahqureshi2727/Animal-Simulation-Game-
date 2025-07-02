package com.animalworld.menu;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.animalworld.animal.Animal;
import com.animalworld.animal.loader.CreatureFileLoaderSingleton;
import com.animalworld.animal.traits.FlightMovement;
import com.animalworld.animal.traits.ForagingState;
import com.animalworld.graphics.TextureSingleton;
import com.badlogic.gdx.graphics.Texture;
/**
 * Concrete command. Adds animal to the simulation.
 * @author Elaina Rivers
 */
public class AddAnimal implements Command {

    /**
     * fileLoader - Accessing the file reader singleton.
     * textures - Accessing the texture singleton.
     * thisTexture - Texture within singleton.
     * chooser - JFileChooser that enables user to import file.
     * filter - Filter so only JSON files show up in directory.
     * thisPath - So file reader singleton knows where to go.
     */
    private final CreatureFileLoaderSingleton fileLoader = CreatureFileLoaderSingleton.getInstance();
    private final TextureSingleton textures = TextureSingleton.getInstance();
    private final Texture thisTexture = textures.getTexture("cow.png");
    private final JFileChooser chooser = new JFileChooser();
    private final FileNameExtensionFilter filter = new FileNameExtensionFilter("Animal JSON Files", "json");
    private String thisPath;
    
    /**
     *  Apply filter, let user choose file, if successful, add animal.
     * Uses dummy data for now.
     */
    @Override
    public void execute() {
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            thisPath = chooser.getSelectedFile().getAbsolutePath();
            Animal newAnimal = fileLoader.loadFile(thisPath, thisTexture);
            newAnimal.setFeedingStrategy("Meat");
            FlightMovement rand = new FlightMovement();
            newAnimal.setMovementStrategy(rand);
            newAnimal.setState(new ForagingState());
        } else {
            System.err.println("There was an error reading the Animal JSON file!");
        }
    }
}
