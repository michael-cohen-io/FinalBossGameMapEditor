package menu.menuitem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static main.Main.map;
import static main.Main.spritesheet;
import utility.ImageSplitter;
import main.MapEditorWindow;
import panel.SpritesheetPanel;
import tilegrid.Spritesheet;
import tilegrid.Tile;

/**
 *
 * @author ChrisMoscoso
 */
public class LoadSpritesheetMenuItemListener implements ActionListener {

    private final JFileChooser chooser;
    private final MapEditorWindow window;

    public LoadSpritesheetMenuItemListener(MapEditorWindow w) {
        this.chooser = new JFileChooser(new File("./src/resources"));
        this.window = w;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = chooser.showOpenDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File loadFile = chooser.getSelectedFile();
            loadSpriteSheet(loadFile);
        }
    }

    private void loadSpriteSheet(File f) {
        try {
            BufferedImage image = ImageIO.read(f); //add choose method call here
            
            spritesheet = new Spritesheet(image);
            
            ImageSplitter splitter = new ImageSplitter(image, spritesheet.getTileWidth(), spritesheet.getTileHeight());
            int id = 1;
            for (int y = 0; y < splitter.getY(); y++) {
                for (int x = 0; x < splitter.getX(); x++) {
                    spritesheet.setTile(x, y, new Tile(""+id, splitter.getTile(x,y), x, y));
                    id++;
                }
            }
                     
            JFrame spriteSheetFrame = new JFrame(chooser.getSelectedFile().getPath());
            SpritesheetPanel s = new SpritesheetPanel();
            spritesheet.addObserver(s);
            spriteSheetFrame.setContentPane(s);
            spriteSheetFrame.setSize(spritesheet.getGridWidthInPixels(), spritesheet.getGridHeightInPixels()+ 20);
            spriteSheetFrame.setAlwaysOnTop(true);
            spriteSheetFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            spriteSheetFrame.setVisible(true);

        } catch (IOException ex) {
            Logger.getLogger(MapEditorWindow.class.getName()).log(Level.INFO, null, ex);
        }
    }

}
