package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author ChrisMoscoso
 */
class MapEditorWindow extends JFrame{
    
    private int MapWidth = 10, MapHeight = 10, TileWidth = 32, TileHeight = 32;

    public MapEditorWindow() {       
        JPanel north = new JPanel();
        north.setBackground(Color.white);
        north.add(new JLabel("Map Width"));
        north.add(new JButton("-"));
        north.add(new JLabel("10"));
        north.add(new JButton("+"));
        north.add(new JLabel("Map Height"));
        north.add(new JButton("-"));
        north.add(new JLabel("10"));
        north.add(new JButton("+"));
        north.add(new JLabel("Tile Width"));
        north.add(new JButton("-"));
        north.add(new JLabel("30"));
        north.add(new JButton("+"));
        north.add(new JLabel("Tile Height"));
        north.add(new JButton("-"));
        north.add(new JLabel("30"));
        north.add(new JButton("+"));
        
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.LINE_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        

        JPanel map = new MapPanel(this);
        JScrollPane mapScrollPane = new JScrollPane(map);
        mapScrollPane.setMaximumSize(new Dimension(this.getTileWidth() * this.getMapWidth(), this.getTileHeight() * this.getMapHeight()));
        //mapScrollPane.setMaximumSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        
        
        JPanel sprites = new JPanel();
        sprites.setBackground(Color.yellow);
        sprites.setMaximumSize(new Dimension(300,300));
        
        //Container
        Container c = this.getContentPane();
        //c.setLayout(null);
        c.add(north, BorderLayout.NORTH);
        c.add(center);
        
        center.add(mapScrollPane);
        center.add(sprites);
        
        //Set up Window
        this.setTitle("FINAL BOSS Map Editor v1");
        this.setSize(Main.WIDTH + 350, Main.HEIGHT + 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public int getMapWidth() {
        return MapWidth;
    }

    public void setMapWidth(int MapWidth) {
        this.MapWidth = MapWidth;
    }

    public int getMapHeight() {
        return MapHeight;
    }

    public void setMapHeight(int MapHeight) {
        this.MapHeight = MapHeight;
    }

    public int getTileWidth() {
        return TileWidth;
    }

    public void setTileWidth(int TileWidth) {
        this.TileWidth = TileWidth;
    }

    public int getTileHeight() {
        return TileHeight;
    }

    public void setTileHeight(int TileHeight) {
        this.TileHeight = TileHeight;
    }
    
}
