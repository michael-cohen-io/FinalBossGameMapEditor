package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author ChrisMoscoso
 */
public class MapPanel extends JPanel {
    MapEditorWindow w;
    int panelWidth, panelHeight;
    
    Tile[][] tileArray;
    
    public MapPanel(final MapEditorWindow w){
        this.w = w;
        this.resize();
        tileArray = new Tile[w.getMapWidth()][w.getMapHeight()];
        
        this.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                int x = e.getPoint().x / w.getTileWidth();
                int y = e.getPoint().y / w.getTileWidth();
                tileArray[x][y] = w.getSelectedTile();
                repaint();

            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}

        });
    }
    
    public void resetPanelWidth(){
        panelWidth = w.getMapWidth() * w.getTileWidth();
        repaint();
    }
    
    public void resetPanelHeight(){
        panelHeight = w.getMapHeight() * w.getTileHeight();
        repaint();
    }
    
    public void resize(){
        panelWidth = w.getMapWidth() * w.getTileWidth();
        panelHeight = w.getMapHeight() * w.getTileHeight();
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int x = w.getTileWidth(); x <= panelWidth; x += w.getTileWidth() ){
            g.drawLine(x,0,x,panelHeight);
        }
        
        for(int y = w.getTileHeight(); y <= panelHeight; y += w.getTileHeight() ){
            g.drawLine(0,y,panelWidth,y);
        }
        
        if (tileArray != null) {
            for (int x = 0; x < tileArray.length; x++) {
                for (int y = 0; y < tileArray[x].length; y++) {
                    if(tileArray[x][y] != null){
                        g.drawImage(tileArray[x][y].getImage(), x * w.getTileWidth(), y * w.getTileHeight(), this);
                    }
                    
                }
            }
        }
    }
    
    public Tile[][] getTileArray() {
        return tileArray;
    }
    
    
}
