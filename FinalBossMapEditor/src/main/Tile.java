package main;

import java.awt.image.BufferedImage;

/**
 *
 * @author ChrisMoscoso
 */
public class Tile {
    private int id;
    
    
    public Tile(){
        
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
    
    private BufferedImage image;
}
