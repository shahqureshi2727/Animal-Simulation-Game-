package com.animalworld.animal.loader;

import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.animalworld.animal.Animal;
import com.badlogic.gdx.graphics.Texture;

/**
 * Singleton class to load and parse creature description files (JSON).
 * 
 * @author Jophene Campbell
 */
public class CreatureFileLoaderSingleton {

    
    // Private static instance.
    private static CreatureFileLoaderSingleton instance = null;

    /**
     * Private constructor to prevent direct instantiation.
     */
    private CreatureFileLoaderSingleton() {}

    /**
     * Public static method to get the Singleton instance.
     * 
     * @return The single instance of this class.
     */
    public static CreatureFileLoaderSingleton getInstance() {
        if (instance == null) {
            instance = new CreatureFileLoaderSingleton();
        }
        return instance;
    }

    /**
     * Method to load and parse the animal description JSON file.
     * 
     * @param filePath          Path to the JSON file.
     * @param texture           Texture to be assigned to the animal.
     * @return                  A parsed Animal object.
     */
    public Animal loadFile (String filePath, Texture texture) {
        Animal animal = null;
        
        try (FileReader reader = new FileReader(filePath)) {
            // Read the JSON file and parse it into a JSONObject.
            StringBuilder jsonData = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                jsonData.append((char) i);
            }

            JSONObject obj = new JSONObject(jsonData.toString());
            animal = parseAnimal(obj, texture);
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing JSON data: " + e.getMessage());
        }
        return animal;
    }

    /**
     * Method to parse a JSONObject into an Animal object.
     * 
     * @param obj               JSONObject containing animal data.
     * @param texture           Texture to be assigned to the animal.
     * @return                  A fully initialized Animal object.
     */
    private Animal parseAnimal(JSONObject obj, Texture texture) {
        try {
            String name = obj.getString("name");
            int movementRate = obj.getInt("movementRate");
            int visualRange = obj.getInt("visualRange");
            int size = obj.getInt("size");
            int evolutionRate = obj.getInt("evolutionRate");
            int reproductionRate = obj.getInt("reproductionRate");
            boolean isHunter = obj.getBoolean("isHunter");
            boolean isCannibal = obj.getBoolean("isCannibal");
            int nutritionalValue = obj.getInt("nutritionalValue");
            String feedingBehavior = obj.getString("feedingBehavior");

            // Create the Animal object
            Animal animal = new Animal( name, movementRate, visualRange, size, evolutionRate, reproductionRate,
                isHunter, isCannibal, nutritionalValue, texture, feedingBehavior);

            return animal;
        } catch (JSONException e) {
            System.err.println("Error creating Animal object: " + e.getMessage());
            return null;
        }
    }

    
}
