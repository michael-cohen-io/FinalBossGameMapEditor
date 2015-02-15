package menu.menuitem;

import main.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;
import static main.Main.map;
import main.MapEditorWindow;
import utility.SavedMapLoader;
import tilegrid.Tile;
import org.xml.sax.SAXException;

/**
 *
 * @author ChrisMoscoso
 */
public class LoadMapMenuItemListener implements ActionListener {

    private JFileChooser chooser = new JFileChooser(new File("./src/resources"));
    private MapEditorWindow window;

    public LoadMapMenuItemListener(MapEditorWindow w) {
        this.window = w;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = chooser.showOpenDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File loadFile = chooser.getSelectedFile();
            Tile[][] tiles = getTilesFromFile(loadFile);
            
            map.setTiles(tiles);
            //window.mapPanel.repaint();
        }
    }

    private Tile[][] getTilesFromFile(File f) {
        InputStream is = null;
        Tile[][] tiles = null;

        try {
            is = new FileInputStream(f);
            try {
                tiles = SavedMapLoader.getTiles(is, map.getGridWidth(), map.getGridHeight());
                map.setTiles(tiles);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return tiles;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadMapMenuItemListener.class.getName()).log(Level.INFO, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(LoadMapMenuItemListener.class.getName()).log(Level.INFO, null, ex);
            }
        }
        return tiles;
    }

}
