package tilegrid;

import java.awt.Point;

/**
 *
 * @author ChrisMoscoso
 */
public class TileBounds {

    private int x, y;

    public TileBounds(int x, int y) {
        this.x = x * TileGrid.getTileWidth();
        this.y = y * TileGrid.getTileHeight();
    }

    public Boolean containtsPoint(Point p) {
        if (p.getX() < x || p.getX() > x + TileGrid.getTileWidth()) {
            return false;
        }
        if (p.getY() < y || p.getY() > y + TileGrid.getTileHeight()) {
            return false;
        }
        return true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public String toString(){
        return "X: " + x + " to " + (x +TileGrid.getTileWidth()) + " Y: " + y + " to " + (y +TileGrid.getTileHeight());
    }
}
