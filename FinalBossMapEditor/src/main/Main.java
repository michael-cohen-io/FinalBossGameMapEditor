package main;

import tilegrid.Spritesheet;
import tilegrid.Tile;
import tilegrid.TileGrid;

/**
 *
 * @author ChrisMoscoso
 */
public class Main {

    //Initializer values
    public static final int WIDTH = 720, HEIGHT = 600;
    public static final int INIT_MAP_WIDTH = 10, INIT_MAP_HEIGHT = 10,
            INIT_TILE_WIDTH = 32, INIT_TILE_HEIGHT = 32;

    //Singleton Classes
    public static TileGrid map;
    public static Spritesheet spritesheet;

    //Global variables
    public static Tile selectedTile;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        map = new TileGrid(INIT_MAP_WIDTH, INIT_MAP_HEIGHT);
        spritesheet = new Spritesheet(null);
        new MapEditorWindow();
    }

}
