package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
 * Writes an XML file after saving a Map
 * 
 * @author: Hanif
 */
public class XMLWriter {
	
	private String fileName;
	PrintWriter out = null;
	File saveFile;
	Tile [][] tilesToSave;
	
	int width , height;
	
	public XMLWriter(Tile[][] tiles , File f , int mapWidth, int mapHeight){
		tilesToSave = tiles;
		saveFile = f;
		width = mapWidth;
		height = mapHeight;
		System.out.println(width);
		System.out.println(height);
		try {
			out = new PrintWriter(saveFile);
			save();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}
	
	public void save(){
		
		writeHeaders();
		//if(Tile[i][j] == null , write <tile gid="0">)
		String tileTag = "<tile gid=";
		String tileCloseTag = "/>";
		
		
		//doStuff
		
		for(int j = 0; j < height; ++j){
			for(int i = 0; i < width; ++i){
				if(tilesToSave[i][j] == null){
					out.println(tileTag + "\"0\"" + tileCloseTag);
				}
			}
		}
		
		writeClosers();
	}
	
	
	private void writeHeaders(){
		String version = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		String mapTag = "<map width=" + "\"" + width + "\"" + " height=" + "\"" 
		+ height + "\"" + ">";
		
		String layerTag = "<layer>"; // for compatibility purposes		
		String dataTag = "<data>";
		
		
		
		
		out.println( version );
		out.println( mapTag );
		out.println( layerTag );
		out.println( dataTag );			
	}
	
	private void writeClosers(){
		String mapClose = "</map>";
		String layerCloseTag = "</layer>";
		String dataCloseTag = "</data>";
		
		out.println( dataCloseTag );
		out.println( layerCloseTag );
		out.println( mapClose );
		
		
		
		
	}
}

















