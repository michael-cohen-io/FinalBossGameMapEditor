package panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static main.Main.map;
import static main.Main.selectedTile;

/**
 *
 * @author ChrisMoscoso
 */
public class MapPanelMouseListener implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getPoint().x / map.getTileWidth();
        int y = e.getPoint().y / map.getTileHeight();
        map.setTile(x, y, selectedTile);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
