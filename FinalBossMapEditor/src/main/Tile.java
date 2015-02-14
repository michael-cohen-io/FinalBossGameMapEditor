package main;

import java.awt.image.BufferedImage;
/**
 *
 * @author ChrisMoscoso
 */
public class Tile {
    private int id;
    private BufferedImage image;
    private Bounds bounds;
    
    public Tile(int id, BufferedImage image, Bounds bounds){
        this.id = id;
        this.image = image;
        this.bounds = bounds;
    }
    
    public Bounds getBounds(){
        return bounds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
}
