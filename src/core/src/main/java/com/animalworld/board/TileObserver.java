package com.animalworld.board;

/**
 * Observer that will update tile counters for tile events.
 * (Tile events such as grass growing.)
 *
 * @author James Marshall
 */
public class TileObserver implements BoardObserver{
    private int tileCol;
    private int tileRow;

    /**
     * Creates an observer that will be tied to a tile.
     *
     * @param observerName Based on the position of the tile on the board.
     */
    public TileObserver(int col, int row) {
        this.tileCol = col;
        this.tileRow = row;
    }

    @Override
    public void update(Board board) {
//        // TODO Implementation to update tiles.
//        // Dirt changes into Grass after a certain number of turns. Nothing happens if already Grass.
//        Tile[][] tileMatrix = board.getBoard();
//        if (tileMatrix[tileCol][tileRow].isDirt()) {
//            // Update Turn counter/change state/some update to make grass grow at some point
//        }
    }

    public String getObserverIdentity() {
        return "Tile " + tileCol + "-" + tileRow;
    }

}
