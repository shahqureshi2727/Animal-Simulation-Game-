package com.animalworld.board;

import com.animalworld.animal.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * The Board class. Uses the singleton pattern. Conceptually is a 2D array of Tile objects.
 * @author Elaina Rivers
 * @author Jake Simoes
 */

public class Board {
    // Our singleton instance.
    private static Board instance;
    // Dimensions of the board.
    private int width;
    private int length;
    // Our actual board is just a 2D array of Tile objects.
    private TileInterface[][] boardMatrix;
    // While we have a board of tiles for figuring out which are where, we also don't want to call act upon
    // any that are not unique, so we have an array list of all unique tiles.
    private List<TileInterface> uniqueTileList;
    // The list of all animals.
    private List<Animal> animalList;

    /**
     * Board constructor that takes a width and a length value.
     */
    private Board(int width, int length){
        this.width = width;
        this.length = length;
        boardMatrix = new PlantTileProxy[width][length];
        animalList = new ArrayList<>();
        uniqueTileList = new ArrayList<>();
    }

    /**
     * @return The matrix of tiles. Not sure if we need this or not.
     */
    public TileInterface[][] getBoard() {
        return boardMatrix;
    }

    /**
     * Returns the tile at the specified location
     * @param x The x coordinate
     * @param y The y coordinate
     * @return A tile object.
     */
    public TileInterface getTile(int x, int y) {
        return boardMatrix[x][y];
    }

    /**
     * @return An array list of all animal objects.
     */
    public List<Animal> getAnimals() {
        return animalList;
    }

    /**
     * @return An array list of all tile objects.
     */
    public List<TileInterface> getTiles() {
        return uniqueTileList;
    }

    /**
     * Checks to see if there is an animal at the specified coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param range The range around the coordinate to explore.
     * @param callee The calling animal so we don't accidentally find it.
     * @return Returns an Animal object is one is found, Null otherwise.
     */
    public Animal checkForAnimal(float x, float y, int range, Animal callee) {
        // This is O(N) which is fairly terrible. Pretend you didn't see this.
        float currentX = 0;
        float currentY = 0;
        // Check each animal for their location.
        for (Animal animal : animalList) {
            currentX = animal.getSprite().getX();
            currentY = animal.getSprite().getY();

            // Is the animal within our range?
            if ((currentX > (x - range)) && (currentX < (x + range))) {
                if ((currentY > (y - range)) && (currentY < (y + range))) {
                    // The animal is within our range, but is it the animal who called?
                    if (animal != callee) {
                        // Nope. So return it!
                        return animal;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @return The instance of the board if there is one. If not, make one.
     * @param width
     * The width of the new board.
     * @param length
     * The length of the new board.
     */
    public static Board getInstance(int width, int length){
        if (instance == null) {
            instance = new Board(width, length);
        }
        return instance;
    }

    /**
     * @return The instance of the board.
     */
    public static Board getInstance(){
        if (instance != null) {
            return instance;
        } else {
            return null;
        }
    }

    /**
     * Fills up the board with plant tiles.
     */
    public void initializeBoardWithPlantTiles() {
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < length; row++){
                boardMatrix[row][col] = new PlantTileProxy(1, 5, 50);
            }
        }
    }

    /**
     * @return The board's width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The board's length.
     */
    public int getLength() {
        return length;
    }

    public void setBoard(TileInterface[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
    }

    public void setTileList(List<TileInterface> uniqueTileList) {
        this.uniqueTileList = uniqueTileList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }
}
