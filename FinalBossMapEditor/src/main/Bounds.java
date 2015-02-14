package main;

import java.awt.Point;

/**
 *
 * @author ChrisMoscoso
 */
public class Bounds {
    
    private int x, y, width, height;

    public Bounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public Boolean containtsPoint(Point p){
        if(p.getX() < x || p.getX() > x + width ){
            return false;
        }
        if(p.getY() < y || p.getY() > y + height){
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
}
