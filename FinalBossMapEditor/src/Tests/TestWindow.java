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
	String path = "/Users/hanif/Documents/school/spring15/cop4331/MapEditor/FinalBossMapEditor/src/Tests/tmw_desert_spacing.png";
	int imageWidth;
	int imageHeight;
	int tileWidth , tileHeight;
	//int width , height;
	public TestWindow(int tileWidth , int tileHeight , int w , int h){
		this.tileHeight = tileHeight;
		this.tileWidth = tileWidth;
		
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		
		this.setTitle("ImageSplitter Test");
		this.setSize(w , h);
		
		displayImage();
		
	}
	
	private void displayImage(){
		ImageSpliter spliter = new ImageSpliter(image, imageWidth, imageHeight, 
				tileWidth, tileHeight);
		BufferedImage[][] tiles = spliter.getTileArray();
		JLabel tile = new JLabel(new ImageIcon(tiles[1][0]));
		add(tile);
		
		
				
	}
	
	
	
}
