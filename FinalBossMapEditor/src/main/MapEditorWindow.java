package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author ChrisMoscoso
 */
class MapEditorWindow extends JFrame implements Observer{
    
    private int mapWidth = 100, mapHeight = 100, tileWidth = 32, tileHeight = 32;
    private MapPanel map;
    private JFileChooser chooser;
    private ImageSplitter splitter;
    private Tile selectedTile;
    public JScrollPane mapScrollPane;
    SpritePanel spriteSheet = new SpritePanel(this);
    
    BufferedImage[][] b;
    
    ArrayList<Tile> tileList = new ArrayList<Tile>();
    

    public MapEditorWindow() {       
        JPanel north = new JPanel();
        north.setBackground(Color.white);
        JPanel south = new JPanel();
        north.setBackground(Color.white);
        
        //Fields
        north.add(new JField("Map Width", mapWidth, this));
        north.add(new JField("Map Height", mapHeight, this));
        south.add(new JField("Tile Width", tileWidth, this ));
        south.add(new JField("Tile Height", tileHeight, this));
        
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.LINE_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        
        //ScrollPane
        map = new MapPanel(this);
        mapScrollPane = new JScrollPane(map);
        mapScrollPane.setMaximumSize(new Dimension(1080, 720));
        
        //Container
        Container c = this.getContentPane();
        c.add(north, BorderLayout.NORTH);
        c.add(center, BorderLayout.CENTER);
        c.add(south, BorderLayout.SOUTH);
        
        center.add(mapScrollPane);
        
        //Set up menu
        addMenu();
        //Set up jChooser
        chooser = new JFileChooser();
        
        //Set up Window
        this.setTitle("FINAL BOSS Map Editor v1");
        this.setSize(Main.WIDTH + 350, Main.HEIGHT + 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
       
    }
    
    private void addMenu(){
    	JMenu fileMenu = new JMenu("File");
		JMenuItem run = new JMenuItem("Choose Sprite Sheet");
                JMenuItem save = new JMenuItem("Save Map XML");
		JMenuItem exit = new JMenuItem("Exit");
		
		run.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
                            int choice = chooser.showOpenDialog(null);
                            File file = null;
                            if(choice == JFileChooser.APPROVE_OPTION){
                               file = chooser.getSelectedFile();
                            }
                            try {
                                BufferedImage image = ImageIO.read(file); //add choose method call here
                                splitter = new ImageSplitter(image, tileWidth, tileHeight);
                                Tile[][] t = new Tile[splitter.getX()][splitter.getY()]; 
                                for(int y = 0; y < splitter.getY(); y++ ){
                                    for(int x = 0; x < splitter.getX(); x++ ){
                                        t[x][y] = new Tile (x+y+1, splitter.getTile(x,y), new Bounds(x*tileWidth,y*tileHeight, tileWidth, tileHeight));
                                    }   
                                }
                                
                                
                                JFrame spriteSheetFrame = new JFrame(chooser.getSelectedFile().getPath());
                                
                                
                                spriteSheetFrame.setContentPane(spriteSheet);
                                spriteSheetFrame.setSize(splitter.getSheetWidth(), splitter.getSheetHeight());
                                spriteSheetFrame.setResizable(false);
                                spriteSheetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                spriteSheetFrame.setVisible(true);
                                spriteSheet.drawTiles(t);
                                
                            } catch (IOException ex) {
                                Logger.getLogger(MapEditorWindow.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}

		});

		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);	
			}

		});
		
		fileMenu.add(run);
                fileMenu.add(save);
		fileMenu.add(exit);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);
    }
    
    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
        map.resize();
        mapScrollPane.revalidate();
        
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
        map.resize();
        mapScrollPane.revalidate();
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int TileHeight) {
        this.tileHeight = TileHeight;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(((ObservableInt) o).getTitle().equals("Map Width")){
            mapWidth = ((ObservableInt) o).getValue();
            map.resetPanelWidth();
        }else if(((ObservableInt) o).getTitle().equals("Tile Width")){
            tileWidth = ((ObservableInt) o).getValue();
            map.resetPanelWidth();
        }else if(((ObservableInt) o).getTitle().equals("Map Height")){
            mapHeight = ((ObservableInt) o).getValue();
            map.resetPanelHeight();
        }else if(((ObservableInt) o).getTitle().equals("Tile Height")){
            tileHeight = ((ObservableInt) o).getValue();
            map.resetPanelHeight();
        }
    }

    public void setSelectedTile(Tile t) {
        this.selectedTile = t;
    }
    
    public Tile getSelectedTile() {
        return this.selectedTile;
    }
    
}
