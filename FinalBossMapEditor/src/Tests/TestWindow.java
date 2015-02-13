package Tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import main.ImageSpliter;
/*
 * @author: hanif
 */
public class TestWindow extends JFrame{
	BufferedImage image;
	String path = "tmw_desert_spacing.png";
	int imageWidth;
	int imageHeight;
	int tileWidth , tileHeight;
	ImageSpliter splitter;
	int x , y;
	//int width , height;
	public TestWindow(int tileWidth , int tileHeight , int w , int h){
		
		
		
		this.tileHeight = tileHeight;
		this.tileWidth = tileWidth;
		x = imageWidth / tileWidth;
		y = imageHeight / tileHeight;
		try {
			//image = ImageIO.read(new File(path));
			image = ImageIO.read(getClass().getResource("/resources/tiles-map.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		
		this.setTitle("ImageSplitter Test");
		this.setSize(w , h);
		
		splitter =  new ImageSpliter(image, imageWidth, imageHeight, 
				tileWidth, tileHeight);
		
		/*
		for(int i = 0; i < x; ++i){
			for(int j = 0; j < y; ++j){
				displayImage(i , j);
				System.out.println("Printing (" + i+ "," + j + ")");
			}
		}
		*/
		
		displayImage(0 , 0);
		displayImage(0,1);
		
	}
	
	private void displayImage(int x , int y){
		
		//BufferedImage[][] tiles = splitter.getTileArray();
		JLabel tile = new JLabel(new ImageIcon(splitter.getTile(x, y)));
		add(tile);
		
		
				
	}
	
	
	
}
