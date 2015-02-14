package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class SavedMapLoader {

	// InputStream passed from JFileChooser
	public static Tile[][] getTiles(InputStream s , int w , int h) throws ParserConfigurationException, SAXException, IOException{
		Tile[][] tiles;
		Document doc = XMLReader.parseDocument(s);
		if(doc == null){
			System.out.println("Error Reading file");
			//now load ActionListener must check that getTiles does not return null
			return null;
		}
		
		tiles = getTiles(doc , w , h);
		return tiles; 
	}
	
	private static Tile[][] getTiles(Document doc , int w , int h){
		Tile[][] tiles = new Tile[w][h];
		Element e = doc.getDocumentElement();
		List<Element> elements = XMLReader.getElements("tile", e);
		int i = 0; 
		int j = 0;
		
		for(Element el : elements){
			//System.out.println("i: " + i + " j: " + j);
			//System.out.println(el.getAttribute("tid"));
			tiles[i][j] = new Tile(Integer.parseInt(el.getAttribute("gid")));
			++j;
			
			if(j >= w ){
				j = 0;
				++i;
			}
			if(i >= h )
				break;
		}
		
		return tiles;
	}
	
	
}
