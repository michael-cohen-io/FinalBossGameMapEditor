package main;

import java.awt.image.BufferedImage;

/*
 * Takes a BufferedImage (the sprite sheet), the sheet/tile height/width, and returns
 * a 2D array of the individual tiles.
 * 
 * @author: Hanif 
 */
public class ImageSpliter {
	private BufferedImage img;
	private int sheetHeight;
	private int sheetWidth;
	private int tileWidth;
	private int tileHeight;
	
	private int x;
	private int y;
	
	BufferedImage[][] tiles;
	
	public ImageSpliter(BufferedImage i, int shWidth, int shHeight, 
			int tWidth, int tHeight){
		img = i;
		sheetWidth = shWidth;
		sheetHeight = shHeight;
		tileHeight = tHeight;
		tileWidth = tWidth;
		
		x = sheetWidth / tileWidth;
		y = sheetHeight / tileHeight;
		
		tiles = makeTiles();
		
	}
	
	private BufferedImage[][] makeTiles(){
		BufferedImage[][] image = new BufferedImage[x][y];
		int xPos = 0;
		int yPos = 0;
		for( int i = 0; i < y; ++i){
			for( int j = 0; j < x; ++x){
				image[i][j] = img.getSubimage(xPos, yPos, tileWidth, tileHeight);
				xPos += tileWidth;

			}
			
			y += tileHeight;
		}
		
		return image;
	}
	
	public BufferedImage getTile(int i , int j){
		return tiles[i][j];
	}
	
	public BufferedImage[][] getTileArray(){
		return tiles;
	}
		
	
}

	
	
	
	
	
	
	
	
	
	