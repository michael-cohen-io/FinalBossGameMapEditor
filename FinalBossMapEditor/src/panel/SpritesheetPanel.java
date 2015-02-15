package panel;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import static main.Main.spritesheet;

/**
 *
 * @author ChrisMoscoso
 */
public class SpritesheetPanel extends TileGridPanel implements Observer {
    

    public SpritesheetPanel() {
        super(spritesheet);
        setPreferredSize(new Dimension(spritesheet.getGridWidthInPixels(), spritesheet.getGridHeightInPixels()));
        this.addMouseListener(new SpritesheetPanelMouseListener());
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    

}
