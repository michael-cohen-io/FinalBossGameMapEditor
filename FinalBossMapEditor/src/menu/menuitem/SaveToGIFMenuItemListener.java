package menu.menuitem;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import static main.Main.map;
import main.MapEditorWindow;
import tilegrid.Tile;

/**
 *
 * @author ChrisMoscoso
 */
public class SaveToGIFMenuItemListener implements ActionListener {

    private JFileChooser chooser = new JFileChooser(new File("./src/resources"));
    private MapEditorWindow window;

    public SaveToGIFMenuItemListener(MapEditorWindow w) {
        window = w;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = chooser.showSaveDialog(null);

        if (choice == JFileChooser.APPROVE_OPTION) {
            File saveFile = chooser.getSelectedFile();
            saveGIF(saveFile);

        }
    }
    
    private void saveGIF(File f){
        Tile[][] tileArray = map.getTiles();
        BufferedImage image = new BufferedImage(tileArray.length * map.getTileWidth(), tileArray[0].length * map.getTileHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        if (tileArray != null) {
            for (int x = 0; x < tileArray.length; x++) {
                for (int y = 0; y < tileArray[x].length; y++) {
                    if (tileArray[x][y] != null) {
                        g.drawImage(tileArray[x][y].getImage(), x * map.getTileWidth(), y * map.getTileHeight(), window);
                    }

                }
            }
        }
        try {
            ImageIO.write(image, "gif", f);

        } catch (IOException ex) {
            Logger.getLogger(MapEditorWindow.class
                    .getName()).log(Level.INFO, null, ex);
        }
    }

}
