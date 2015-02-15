package panel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import tilegrid.Tile;
import tilegrid.TileGrid;

/**
 *
 * @author ChrisMoscoso
 */
public class TileGridPanel extends JPanel {

    TileGrid grid;
    LineStyle gridLineStyle;

    private enum LineStyle {

        Solid, Dotted
    }

    public TileGridPanel(TileGrid grid) {
        this.grid = grid;
        this.gridLineStyle = LineStyle.Dotted;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int tw = TileGrid.getTileWidth();
        int th = TileGrid.getTileHeight();
        
        //Draw Tiles
        if (grid != null && grid.getTiles() != null) {
            for (int x = 0; x < grid.getTiles().length; x++) {
                for (int y = 0; y < grid.getTiles()[x].length; y++) {
                    Tile t = grid.getTile(x, y);
                    if (t != null) {
                        Image i = t.getImage();
                        g.drawImage(i, x * tw, y * th, this);
                    }
                }
            }
        }

        //Draw IDs
        g.setColor(Color.red);
        if (grid.isShowingIDs() && grid != null && grid.getTiles() != null) {
            for (int x = 0; x < grid.getTiles().length; x++) {
                for (int y = 0; y < grid.getTiles()[x].length; y++) {
                    Tile t = grid.getTile(x, y);
                    if (t != null) {
                        g.drawString(t.getId(), x * tw + (int)(0.25*tw), y * th + th/2);
                    }else{
                        g.drawString("0", x * tw + (int)(0.25*tw), y * th + th/2);
                    }
                }
            }
            //Turn up the opacity to draw the tiles
            //float opacity = 0.5f;
            //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));    
        }
        
        //Draw Grid Lines
        g.setColor(Color.black);
        if (grid.isShowingGrid()) {
            //Draw Grid
            for (int x = tw; x <= grid.getGridWidthInPixels(); x += tw) {
                switch (gridLineStyle) {
                    case Solid:
                        g.drawLine(x, 0, x, grid.getGridHeight());
                        break;
                    case Dotted:
                    default:
                        for (int y = 0; y <= grid.getGridHeightInPixels(); y++) {
                            if (y % 2 == 0) {
                                g.drawLine(x, y, x, y);
                            }
                        }
                        break;

                }

            }

            for (int y = th; y <= grid.getGridHeightInPixels(); y += th) {
                switch (gridLineStyle) {
                    case Solid:
                        g.drawLine(0, y, grid.getGridWidth(), y);
                        break;
                    case Dotted:
                    default:
                        for (int x = 0; x <= grid.getGridWidthInPixels(); x++) {
                            if (x % 2 == 0) {
                                g.drawLine(x, y, x, y);
                            }
                        }
                        break;

                }
            }
        }

    }

}
