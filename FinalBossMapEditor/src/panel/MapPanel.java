package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import static main.Main.map;

/**
 *
 * @author ChrisMoscoso
 */
public class MapPanel extends TileGridPanel implements Observer {

    public MapPanel() {
        super(map); 
        setPreferredSize(new Dimension(map.getGridWidthInPixels(), map.getGridHeightInPixels()));
        this.addMouseListener(new MapPanelMouseListener());
        this.addMouseMotionListener(new MapPanelMouseMotionListener());
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
}
