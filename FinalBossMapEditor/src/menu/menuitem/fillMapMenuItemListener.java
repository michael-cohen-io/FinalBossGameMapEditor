package menu.menuitem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static main.Main.map;
import static main.Main.selectedTile;
import main.MapEditorWindow;

/**
 *
 * @author ChrisMoscoso
 */
public class fillMapMenuItemListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < map.getTiles().length; i++){
            for(int j = 0; j < map.getTiles()[0].length; j++){
                map.setTile(i, j, selectedTile);
            }
        }
    }
    
}
