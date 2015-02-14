package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 *
 * @author ChrisMoscoso
 */
class MapEditorWindow extends JFrame implements Observer {

    private int mapWidth = 100, mapHeight = 100, tileWidth = 32, tileHeight = 32;
    private final MapPanel map;
    private final JFileChooser chooser;
    private ImageSplitter splitter;
    private Tile selectedTile;
    private JPanel previewSelectedTile;
    public JScrollPane mapScrollPane;
    public JCheckBoxMenuItem showGridItem;
    SpritePanel spriteSheet = new SpritePanel(this);

    BufferedImage[][] b;

    ArrayList<Tile> tileList = new ArrayList<Tile>();

    public MapEditorWindow() {
        JPanel north = new JPanel();
        north.setBackground(Color.white);
        JPanel south = new JPanel();
        north.setBackground(Color.white);
        
        JPanel south2 = new JPanel();
        south2.setBackground(Color.white);

        //Fields
        north.add(new JField("Map Width", mapWidth, this));
        north.add(new JField("Map Height", mapHeight, this)); 
        //Add label and tile image
        north.add(new JLabel("Selected tile: "));
        previewSelectedTile = new JPanel(){            
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(selectedTile != null){
                    g.drawImage(selectedTile.getImage(), 0, 0, this);
                }
            }
            
        };
        previewSelectedTile.setPreferredSize(new Dimension(tileWidth, tileHeight));
        north.add(previewSelectedTile);
        south.add(new JField("Tile Width", tileWidth, this));
        south.add(new JField("Tile Height", tileHeight, this));

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.LINE_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //ScrollPane
        map = new MapPanel(this);
        mapScrollPane = new JScrollPane(map);
        mapScrollPane.setMaximumSize(new Dimension(1080, 720));

        //Container
        Container c = this.getContentPane();
        c.add(north, BorderLayout.NORTH);
        c.add(center, BorderLayout.CENTER);
        //c.add(south, BorderLayout.SOUTH); //removed tile height and width selection for now. Buggy

        center.add(mapScrollPane);

        //Set up jChooser
        chooser = new JFileChooser();

        //Set up menu
        addMenu();

        //Set up Window
        this.setTitle("FINAL BOSS Map Editor v1");
        this.setSize(Main.WIDTH + 350, Main.HEIGHT + 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    private void addMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenu saveMenu = new JMenu("Save to...");
        JMenu showMenu = new JMenu("Show");
       
        showGridItem = new JCheckBoxMenuItem("Show Grid", true);
        JMenuItem run = new JMenuItem("Choose Sprite Sheet...");
        JMenuItem loadMenu = new JMenuItem("Load");
        
        JMenuItem saveToXML = new JMenuItem("XML");
        JMenuItem saveToGIF = new JMenuItem("GIF");
        
        JMenuItem exit = new JMenuItem("Exit");

        showGridItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                map.repaint();
                spriteSheet.repaint();
            }
        });
        
        run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int choice = chooser.showOpenDialog(null);
                File file = null;
                if (choice == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile();
                }
                try {
                    BufferedImage image = ImageIO.read(file); //add choose method call here
                    splitter = new ImageSplitter(image, tileWidth, tileHeight);
                    Tile[][] t = new Tile[splitter.getX()][splitter.getY()];
                    int id = 1;
                    for (int y = 0; y < splitter.getY(); y++) {
                        for (int x = 0; x < splitter.getX(); x++) {
                            t[x][y] = new Tile(id, splitter.getTile(x, y), new Bounds(x * tileWidth, y * tileHeight, tileWidth, tileHeight));
                            id++;
                        }
                    }

                    JFrame spriteSheetFrame = new JFrame(chooser.getSelectedFile().getPath());

                    spriteSheetFrame.setContentPane(spriteSheet);
                    spriteSheetFrame.setSize(splitter.getSheetWidth(), splitter.getSheetHeight() + 20);
                    spriteSheetFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    spriteSheetFrame.setVisible(true);
                    spriteSheet.drawTiles(t);

                } catch (IOException ex) {
                    Logger.getLogger(MapEditorWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }

        });

        //XML
        saveToXML.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                chooser.setSelectedFile(new File("untitled.xml"));
                int choice = chooser.showSaveDialog(null);

                if (choice == JFileChooser.APPROVE_OPTION) {
                    File saveFile = chooser.getSelectedFile();
                    save(saveFile);
                }
            }

        });
        
        //GIF
        saveToGIF.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent event){
               chooser.setSelectedFile(new File("untitled.gif"));
                int choice = chooser.showSaveDialog(null);

                if (choice == JFileChooser.APPROVE_OPTION) {
                    File saveFile = chooser.getSelectedFile();
                    saveGIF(saveFile);
                }
           } 
        });
        
        loadMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //chooser.setSelectedFile(new File("untitled.xml"));
                int choice = chooser.showSaveDialog(null);

                if (choice == JFileChooser.APPROVE_OPTION) {
                    File loadFile = chooser.getSelectedFile();
                    try {
						InputStream is = new FileInputStream(loadFile);
						Tile[][] tiles = SavedMapLoader.getTiles(is, getMapWidth(), getMapHeight());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    //save(saveFile);
 catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SAXException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }

        });

        //File
        fileMenu.add(run);
        fileMenu.add(saveMenu);
        fileMenu.add(loadMenu);
        fileMenu.add(exit);
        //Show
        showMenu.add(showGridItem);
        //Save submenu
        saveMenu.add(saveToXML);
        saveMenu.add(saveToGIF);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(showMenu);
        this.setJMenuBar(menuBar);
    }

    private void saveGIF(File saveFile){
        Tile[][] tileArray = map.getTileArray();
        BufferedImage image = new BufferedImage(tileArray.length * tileWidth, tileArray[0].length * tileHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        if (tileArray != null) {
            for (int x = 0; x < tileArray.length; x++) {
                for (int y = 0; y < tileArray[x].length; y++) {
                    if (tileArray[x][y] != null) {
                        //tileArray[x][y].getImage();
                        g.drawImage(tileArray[x][y].getImage(), x * tileWidth, y * tileHeight, this);
                    }

                }
            }
        }
        try {
            ImageIO.write(image, "gif", saveFile);
        } catch (IOException ex) {
            Logger.getLogger(MapEditorWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Save file to xml
    private void save(File saveFile) {
        XMLWriter writer = new XMLWriter(map.getTileArray(),
                saveFile, mapWidth, mapHeight);
        //upon construction, writer should save the file
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
        if (((ObservableInt) o).getTitle().equals("Map Width")) {
            mapWidth = ((ObservableInt) o).getValue();
            map.resetPanelWidth();
        } else if (((ObservableInt) o).getTitle().equals("Tile Width")) {
            tileWidth = ((ObservableInt) o).getValue();
            map.resetPanelWidth();
        } else if (((ObservableInt) o).getTitle().equals("Map Height")) {
            mapHeight = ((ObservableInt) o).getValue();
            map.resetPanelHeight();
        } else if (((ObservableInt) o).getTitle().equals("Tile Height")) {
            tileHeight = ((ObservableInt) o).getValue();
            map.resetPanelHeight();
        }
    }

    public void setSelectedTile(Tile t) {
        this.selectedTile = t;
        previewSelectedTile.repaint();
    }

    public Tile getSelectedTile() {
        return this.selectedTile;
    }

}
