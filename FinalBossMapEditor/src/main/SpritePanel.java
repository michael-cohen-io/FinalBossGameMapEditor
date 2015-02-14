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

    Tile[][] tileArray;

    public SpritePanel(final MapEditorWindow w) {
        this.w = w;
        this.setMaximumSize(new Dimension(352, 288));
        this.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getPoint());
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
            public void mouseEntered(MouseEvent e) {
                System.out.println(e.getPoint());
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
        /*for(int i = 0; i < imageList.size(); i++){
         g.drawImage(imageList.get(i), i*w.getTileWidth(), 0, this);
         }*/

        if (tileArray != null) {
            for (int x = 0; x < tileArray.length; x++) {
                for (int y = 0; y < tileArray[x].length; y++) {
                    //System.out.println("x , y: " + x + ", " + y);
                    g.drawImage(tileArray[x][y].getImage(), x * w.getTileWidth(), y * w.getTileHeight(), this);
                }
            }
        }
    }

}
