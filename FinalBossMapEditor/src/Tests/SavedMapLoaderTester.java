package Tests;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import main.Tile;
import main.SavedMapLoader;
public class SavedMapLoaderTester {
	public static void main(String [] args){
		
		InputStream is = null;
	
		
		is  = SavedMapLoader.class.getResourceAsStream("/resources/anotherSave2.xml");
		

		Tile[][] tiles = new Tile[5][5];
		
		int width = 5;
		int height = 5;
		
	
		try {
			tiles = SavedMapLoader.getTiles(is, width, height);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(tiles == null){
			System.out.println("Something went wrong");
			System.exit(-1);
		}
		
		
		for(int i = 0; i < height; ++i ){
			for(int j = 0; j < width; ++j){
				System.out.print(tiles[i][j].getId());
			}
			System.out.println();
		}
		
	}
}
