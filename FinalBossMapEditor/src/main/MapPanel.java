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
        this.setBackground(Color.PINK);
        panelWidth = w.getMapWidth() * w.getTileWidth();
        panelHeight = w.getMapHeight() * w.getTileHeight();
        this.setMaximumSize(new Dimension(panelWidth, panelHeight));
    }
    
    public void setSize(){
        //super.setSize(WIDTH, WIDTH);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int x = w.getTileWidth()-1; x < panelWidth; x += w.getTileWidth() ){
            g.drawLine(x,0,x,panelHeight);
        }
        
        for(int y = w.getTileWidth()-1; y < panelWidth; y += w.getTileWidth() ){
            g.drawLine(0,y,panelHeight,y);
        }
        
    }
}
