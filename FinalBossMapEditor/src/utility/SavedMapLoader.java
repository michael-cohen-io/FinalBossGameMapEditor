package utility;

import xml.XMLReader;
import tilegrid.Tile;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import static main.Main.spritesheet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import tilegrid.TileBounds;

public class SavedMapLoader {

    // InputStream passed from JFileChooser
    public static Tile[][] getTiles(InputStream s, int w, int h) throws ParserConfigurationException, SAXException, IOException {
        Tile[][] tiles = new Tile[w][h];
        Document doc = XMLReader.parseDocument(s);
        if (doc == null) {
            System.out.println("Error Reading file");
            return null;
        } else {
            Element e = doc.getDocumentElement();
            List<Element> elements = XMLReader.getElements("tile", e);

            for (int counter = 0; counter < elements.size(); counter++) {
                String id = elements.get(counter).getAttribute("gid");
                int i = counter / w;
                int j = counter % w;
                
                System.out.println(id);
                tiles[j][i] = new Tile(id, spritesheet.getTile(id).getImage(), new TileBounds(i, j));
            }
        }
        return tiles;
    }
}
