package xml;

import tilegrid.Tile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import tilegrid.TileGrid;

/*
 * Writes an XML file after saving a Map
 * 
 * @author: Hanif
 */
public class XMLWriter {

    public XMLWriter() {

    }

    public static void writeMaptoFile(TileGrid map, File saveFile) {
        Tile[][] tilesToSave = map.getTiles();
        int width = map.getGridWidth();
        int height = map.getGridHeight();
        PrintWriter out = null;
        try {
            out = new PrintWriter(saveFile);
            writeHeaders(out, width, height);
            writeTiles(out, tilesToSave, width, height);
            writeClosers(out);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            out.close();
        }

        
    }

    private static void writeHeaders(PrintWriter out, int width, int height) {
        String version = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        String mapTag = "<map width=" + "\"" + width + "\"" + " height=" + "\""
                + height + "\"" + ">";

        String layerTag = "<layer>"; // for compatibility purposes		
        String dataTag = "<data>";

        out.println(version);
        out.println(mapTag);
        out.println(layerTag);
        out.println(dataTag);
    }

    private static void writeTiles(PrintWriter out, Tile[][] tilesToSave, int width, int height) {
        String tileTag = "<tile gid=";
        String tileCloseTag = "/>";

        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {
                if (tilesToSave[i][j] == null) {
                    out.println(tileTag + "\"0\"" + tileCloseTag);
                } else {
                    String tileId = tilesToSave[i][j].getId();
                    out.println(tileTag + "\"" + tileId + "\"" + tileCloseTag);
                }
            }
        }
    }

    private static void writeClosers(PrintWriter out) {
        String mapClose = "</map>";
        String layerCloseTag = "</layer>";
        String dataCloseTag = "</data>";

        out.println(dataCloseTag);
        out.println(layerCloseTag);
        out.println(mapClose);

    }
}
