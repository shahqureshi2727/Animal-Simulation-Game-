package com.animalworld.graphics;

import com.badlogic.gdx.graphics.Texture;
import java.io.File;
import java.util.HashMap;

/**
 * A singleton object that allows access to all the textures.
 * @author Jake Simoes
 */
public class TextureSingleton {
    private static TextureSingleton theInstance = null;
    private HashMap<String, Texture> textures = new HashMap<>();

    /**
     * Returns the sole instance of this class.
     * @return
     * A TextureSingleton object.
     */
    public static TextureSingleton getInstance() {
        if (theInstance == null) {
            theInstance = new TextureSingleton();
        }
        return theInstance;
    }

    /**
     * Our private constructor.
     * Loads every .png file in the assets folder into a list.
     */
    private TextureSingleton() {
        File folder = new File(System.getProperty("user.dir")+"/src/assets");
        //Creating a File object for directory
        String contents[] = folder.list();

        for (int i = 0; i < contents.length; i++) {
            if (contents[i].contains(".png")) {
                System.out.println("Loaded: " + contents[i]);
                addTexture(contents[i]);
            }
        }
    }

    /**
     * Adds a new texture to the list.
     * @param textureName
     * An image filename
     */
    private void addTexture(String textureName) {
        textures.put(textureName, new Texture(textureName));
    }

    /**
     * Allows access to Textures.
     * @param textureName
     * An image filename
     * @return
     * The corresponding Texture
     */
    public Texture getTexture(String textureName) {
        return textures.get(textureName);
    }
}
