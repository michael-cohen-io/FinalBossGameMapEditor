package tilegrid;

import java.awt.image.BufferedImage;
import utility.MapIDTranslator;

/**
 *
 * @author ChrisMoscoso
 */
public class Tile {

    private String id;
    private BufferedImage image;
    private final TileBounds bounds;

    public Tile(String id, BufferedImage image, int x, int y) {
        this.id = id;
        this.image = image;
        this.bounds = new TileBounds(x, y);
    }
    
    public Tile(String id, BufferedImage image, TileBounds bounds) {
        this.id = id;
        this.image = image;
        this.bounds = bounds;
    }
    

    
    //this constructor is called during loading
    /*public Tile(String id, TileBounds b) {
        this.id = id;
        this.bounds = b;
        MapIDTranslator translator = new MapIDTranslator();
        this.image = translator.getTile(id);

    }*/

    public TileBounds getBounds() {
        return bounds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
