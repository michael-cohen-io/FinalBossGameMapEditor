package menu.menuitem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import static main.Main.map;
import main.MapEditorWindow;
import xml.XMLWriter;

/**
 *
 * @author ChrisMoscoso
 */
public class SaveToXMLMenuItemListener implements ActionListener {

    private JFileChooser chooser = new JFileChooser(new File("./src/resources"));
    private MapEditorWindow window;

    public SaveToXMLMenuItemListener(MapEditorWindow w) {
        window = w;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = chooser.showSaveDialog(null);

        if (choice == JFileChooser.APPROVE_OPTION) {
            File saveFile = chooser.getSelectedFile();
            saveXML(saveFile);

        }
    }
    
    private void saveXML(File f){
        XMLWriter.writeMaptoFile(map, f);
    }

}
