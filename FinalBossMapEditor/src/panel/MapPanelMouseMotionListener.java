package panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import static main.Main.map;
import static main.Main.selectedTile;

/**
 *
 * @author ChrisMoscoso
 */
public class MapPanelMouseMotionListener implements MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getPoint().x / map.getTileWidth();
        int y = e.getPoint().y / map.getTileHeight();
        map.setTile(x, y, selectedTile);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

}
