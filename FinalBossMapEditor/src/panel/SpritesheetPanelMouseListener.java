package panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static main.Main.spritesheet;

/**
 *
 * @author ChrisMoscoso
 */
public class SpritesheetPanelMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (spritesheet != null) {
            for (int x = 0; x < spritesheet.getTiles().length; x++) {
                for (int y = 0; y < spritesheet.getTiles()[x].length; y++) {
                    if (spritesheet.getTile(x, y).getBounds().containtsPoint(e.getPoint())) {
                        spritesheet.setSelectedTile(spritesheet.getTiles()[x][y]);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
