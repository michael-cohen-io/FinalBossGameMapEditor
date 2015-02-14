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
    private MapIDTranslator translator = new MapIDTranslator();;
    
    public Tile(int id, BufferedImage image, Bounds bounds){
        this.id = id;
        this.image = image;
        this.bounds = bounds;
    }
    public Tile(int id, BufferedImage image){
        this(id, image, null);
    }
    
    /*
    public Tile(int id){
    	this.id = id;
    	MapIDTranslator translator = new MapIDTranslator();
    	this.image = translator.getTile(id);
    	
    
    }
    */
    
    //this constructor is called during loading
    public Tile(int id, Bounds b){
    	this.id = id;
    	this.bounds = b;
    	this.image = translator.getTile(id);
    	
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
