package menu.menuitem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import static main.Main.map;
import static main.Main.spritesheet;
import main.MapEditorWindow;

/**
 *
 * @author ChrisMoscoso
 */
public class ShowIDsMenuItemListener implements ActionListener {

    private MapEditorWindow window;

    public ShowIDsMenuItemListener(MapEditorWindow w) {
        window = w;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBoxMenuItem i = (JCheckBoxMenuItem) e.getSource();
        map.shouldShowIDs(i.getState());
        spritesheet.shouldShowIDs(i.getState());
    }
    
}
