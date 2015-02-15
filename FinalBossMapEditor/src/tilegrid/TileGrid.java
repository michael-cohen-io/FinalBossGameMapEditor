package tilegrid;

import java.util.Observable;
import main.Main;

/**
 *
 * @author ChrisMoscoso
 */
public class TileGrid extends Observable {

    private static int tileWidth = Main.INIT_TILE_WIDTH, tileHeight = Main.INIT_TILE_HEIGHT;
    protected int gridWidth, gridHeight;
    protected Tile[][] tiles;
    protected static Boolean showGrid = true;
    protected static Boolean showTileIDs = false;

    public TileGrid(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;

        tiles = new Tile[gridWidth][gridHeight];
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTile(int x, int y) {
        Tile t = null;
        if (x < getGridWidth() && y < getGridHeight()) {
            t = tiles[x][y];
        }
        return t;
    }
    
    public Tile getTile(String id){
        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles[0].length; y++){
                if(tiles[x][y].getId().equals(id)){
                    return tiles[x][y];
                }
            }
        }
        return null;
    }

    public void setTile(int x, int y, Tile t) {
        if (x < getGridWidth() && y < getGridHeight()) {
            tiles[x][y] = t;
        }
        this.setChanged();
        this.notifyObservers();
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
        this.setChanged();
        this.notifyObservers();
    }

    public static int getTileWidth() {
        return tileWidth;
    }

    public static void setTileWidth(int tileWidth) {
        TileGrid.tileWidth = tileWidth;
    }

    public static int getTileHeight() {
        return tileHeight;
    }

    public static void setTileHeight(int tileHeight) {
        TileGrid.tileHeight = tileHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
        Tile[][] temp = tiles.clone();
        tiles = new Tile[gridWidth][gridHeight];
        //Transfer temp tiles to new tiles
        for(int i = 0; i < Math.min(temp.length, tiles.length); i++){
            for(int j = 0; j < temp[0].length; j++){
                tiles[i][j] = temp[i][j];
            }
        }
        
        this.setChanged();
        this.notifyObservers();
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
        Tile[][] temp = tiles.clone();
        tiles = new Tile[gridWidth][gridHeight];
        //Transfer temp tiles to new tiles
        for(int i = 0; i < temp.length; i++){
            for(int j = 0; j < Math.min(temp[0].length, tiles[0].length); j++){
                tiles[i][j] = temp[i][j];
            }
        }
        
        this.setChanged();
        this.notifyObservers();
    }

    public int getGridWidthInPixels() {
        return tileWidth * gridWidth;
    }

    public int getGridHeightInPixels() {
        return tileHeight * gridHeight;
    }

    public boolean isShowingGrid() {
        return showGrid;

    }

    public void shouldShowGrid(boolean state) {
        showGrid = state;
        this.setChanged();
        this.notifyObservers();
    }
    
    public boolean isShowingIDs() {
        return showTileIDs;

    }

    public void shouldShowIDs(boolean state) {
        showTileIDs = state;
        this.setChanged();
        this.notifyObservers();
    }

}
