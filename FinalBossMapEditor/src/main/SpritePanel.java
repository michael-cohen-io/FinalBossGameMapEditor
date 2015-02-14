package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author ChrisMoscoso
 */
public class SpritePanel extends JPanel {

    MapEditorWindow w;
    ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
    BufferedImage[][] imageArray;
    int width, height;
    Tile[][] tileArray;

    public SpritePanel(final MapEditorWindow w, int width, int height) {
        this.w = w;
        this.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                if (tileArray != null) {
                    for (int x = 0; x < tileArray.length; x++) {
                        for (int y = 0; y < tileArray[y].length; y++) {
                            if(tileArray[x][y].getBounds().containtsPoint(e.getPoint())){
                                w.setSelectedTile(tileArray[x][y]);
                            }
                        }
                    }
                }

            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}

        });
    }
    
    public Tile[][] getTileArray() {
        return tileArray;
    }
    

    public void drawBufferedImages(ArrayList<BufferedImage> b) {
        this.setMaximumSize(new Dimension(b.size() * w.getTileWidth(), w.getTileHeight()));
        imageList = b;
        this.repaint();
    }

    public void drawBufferedImages(BufferedImage[][] b) {
        this.setMaximumSize(new Dimension(b.length * w.getTileWidth(), b[0].length * w.getTileHeight()));
        imageArray = b;
        this.repaint();
    }

    public void drawTiles(Tile[][] t) {
        this.setMaximumSize(new Dimension(t.length * w.getTileWidth(), t[0].length * w.getTileHeight()));
        tileArray = t;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = w.getTileWidth() * w.getMapWidth();
        int panelHeight = w.getTileHeight() * w.getMapHeight();
        
        for(int x = w.getTileWidth(); x <= width; x += w.getTileWidth() ){
            g.drawLine(x,0,x,panelHeight);
        }
        
        for(int y = w.getTileHeight(); y <= height; y += w.getTileHeight() ){
            g.drawLine(0,y,panelWidth,y);
        }
        
        if (tileArray != null) {
            for (int x = 0; x < tileArray.length; x++) {
                for (int y = 0; y < tileArray[x].length; y++) {
                    g.drawImage(tileArray[x][y].getImage(), x * w.getTileWidth(), y * w.getTileHeight(), this);
                }
            }
        }
    }

}
