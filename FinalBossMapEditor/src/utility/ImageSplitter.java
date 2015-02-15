package utility;

import java.awt.image.BufferedImage;

/*
 * Takes a BufferedImage (the sprite sheet), the sheet/tile height/width, and returns
 * a 2D array of the individual tiles.
 * 
 * @author: Hanif 
 */
public class ImageSplitter {

    private BufferedImage img;
    private int sheetHeight;
    private int sheetWidth;
    private int tileWidth;
    private int tileHeight;

    private int x; //Number of tiles horizontal
    private int y; //Number of tiles vertical

    BufferedImage[][] tiles;

    public ImageSplitter(BufferedImage i,
            int tWidth, int tHeight) {
        img = i;
        
        sheetWidth = img.getWidth();
        sheetHeight = img.getHeight();
        tileHeight = tHeight;
        tileWidth = tWidth;

        x = sheetWidth / tileWidth;
        y = sheetHeight / tileHeight;

        tiles = makeTiles();

    }

    private BufferedImage[][] makeTiles() {
        BufferedImage[][] image = new BufferedImage[x][y];
        int xPos = 0;
        int yPos = 0;
        for (int j = 0; j < y; ++j) {
            for (int i = 0; i < x; ++i) {
                image[i][j] = img.getSubimage(xPos, yPos, tileWidth, tileHeight);
                //System.out.println("i=" + i + " j=" + j + " " + xPos + " , " +  yPos);
                xPos += tileWidth;

            }
            xPos = 0;
            yPos += tileHeight;
        }

        return image;
    }

    public int getSheetHeight() {
        return sheetHeight;
    }

    public int getSheetWidth() {
        return sheetWidth;
    }

    public BufferedImage getTile(int i, int j) {
        return tiles[i][j];
    }

    //The number of tiles horizontal
    public int getX() {
        return x;
    }

    //The number of tiles vertically
    public int getY() {
        return y;
    }

    public BufferedImage[][] getTileArray() {
        return tiles;
    }

}
