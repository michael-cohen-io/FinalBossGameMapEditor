package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author ChrisMoscoso
 */
public class MapPanel extends JPanel {
    MapEditorWindow w;
    int panelWidth, panelHeight;
    
    
    public MapPanel(MapEditorWindow w){
        this.w = w;
        panelWidth = w.getMapWidth() * w.getTileWidth();
        panelHeight = w.getMapHeight() * w.getTileHeight();
        this.setMaximumSize(new Dimension(panelWidth, panelHeight));
    }
    
    public void resetPanelWidth(){
        panelWidth = w.getMapWidth() * w.getTileWidth();
        repaint();
    }
    
    public void resetPanelHeight(){
        panelHeight = w.getMapHeight() * w.getTileHeight();
        repaint();
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
    }
}
