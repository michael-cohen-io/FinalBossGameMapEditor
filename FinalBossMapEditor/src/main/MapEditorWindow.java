package main;

import panel.MapPanel;
import panel.JField;
import menu.menuitem.*;
import panel.ObservableInt;
import java.awt.*;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import static main.Main.map;
import static main.Main.spritesheet;
import panel.TileGridPanel;

/**
 *
 * @author ChrisMoscoso
 */
public class MapEditorWindow extends JFrame implements Observer {

    public static JPanel previewSelectedTile;
    public static JTextField idField;
    public JScrollPane mapScrollPane;
    public TileGridPanel mapPanel;

    public MapEditorWindow() {
        initModifierPanel();
        initMapPanel();
        initMenu();

        //Set up Window
        this.setTitle("FINAL BOSS Map Editor v2");
        this.setSize(Main.WIDTH, Main.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    private void initModifierPanel() {
        JPanel north = new JPanel(), south = new JPanel();
        north.setBackground(Color.white);
        south.setBackground(Color.white);

        JPanel n1 = new JPanel(), n2 = new JPanel();
        
        //Fields
        n1.add(new JField("Map Width", map.getGridWidth(), this));
        n1.add(new JField("Map Height", map.getGridHeight(), this));
        //Add label and tile image
        n1.add(new JLabel("Selected tile: "));
        previewSelectedTile = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (spritesheet.getSelectedTile() != null) {
                    g.drawImage(spritesheet.getSelectedTile().getImage(), 0, 0, this);
                }
            }

        };
        previewSelectedTile.setPreferredSize(new Dimension(map.getTileWidth(), map.getTileHeight()));
        n1.add(previewSelectedTile);
        n2.add(new JLabel("Id:"));
        idField = new JTextField(5);
        idField.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateID();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateID();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateID();
            }
            
            private void updateID(){
                spritesheet.getSelectedTile().setId(idField.getText());
            }
        });
            
        
        n2.add(idField);
        
        north.setLayout(new BoxLayout(north, BoxLayout.PAGE_AXIS));
        
        n1.setBackground(Color.white);
        n2.setBackground(Color.white);
        north.add(n1);
        north.add(n2);
        
        south.add(new JField("Tile Width", map.getTileWidth(), this));
        south.add(new JField("Tile Height", map.getTileHeight(), this));

        //Container
        Container c = this.getContentPane();
        c.add(north, BorderLayout.NORTH);
        //c.add(south, BorderLayout.SOUTH);//Disabled for now

    }

    private void initMapPanel() {
        mapPanel = new MapPanel();
        map.addObserver((Observer) mapPanel);
        mapScrollPane = new JScrollPane(mapPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Container c = this.getContentPane();
        c.add(mapScrollPane);
    }

    private void initMenu() {
        //Step 1: Construct Menus
        JMenu fileMenu = new JMenu("File");
        JMenu saveMenu = new JMenu("Save to...");
        JMenu mapMenu = new JMenu("Map");

        //Step 2: Construct Menu Items
        JCheckBoxMenuItem showGrid = new JCheckBoxMenuItem("Show Grid", map.isShowingGrid());
        JCheckBoxMenuItem showIDs = new JCheckBoxMenuItem("Show IDs", map.isShowingIDs());
        JMenuItem fillMap = new JMenuItem("Fill Map With Selection");
        JMenuItem loadSpriteSheet = new JMenuItem("Choose Sprite Sheet...");
        JMenuItem loadMenu = new JMenuItem("Load Map...");
        JMenuItem saveToXML = new JMenuItem("XML");
        JMenuItem saveToGIF = new JMenuItem("GIF");
        JMenuItem exit = new JMenuItem("Exit");

        //Step 3: Add listeners to MenuItes
        fillMap.addActionListener(new fillMapMenuItemListener());
        showGrid.addActionListener(new ShowGridMenuItemListener(this));
        showIDs.addActionListener(new ShowIDsMenuItemListener(this));
        loadSpriteSheet.addActionListener(new LoadSpritesheetMenuItemListener(this));
        exit.addActionListener(new ExitMenuItemListener());
        saveToXML.addActionListener(new SaveToXMLMenuItemListener(this));
        saveToGIF.addActionListener(new SaveToGIFMenuItemListener(this));
        loadMenu.addActionListener(new LoadMapMenuItemListener(this));

        //Step 4: Add Menu Items to Menus
        fileMenu.add(loadSpriteSheet);
        fileMenu.add(loadMenu);
        fileMenu.addSeparator();
        fileMenu.add(saveMenu);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        mapMenu.add(fillMap);
        mapMenu.addSeparator();
        mapMenu.add(showIDs);
        mapMenu.add(showGrid);   
        saveMenu.add(saveToXML);
        saveMenu.add(saveToGIF);

        //Step 5: Add Menus to MenuBar
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(mapMenu);
        this.setJMenuBar(menuBar);
    }

    @Override
    public void update(Observable o, Object arg) {
        int value;
        String title = ((ObservableInt) o).getTitle();
        switch (title) {
            case "Map Width": {
                value = ((ObservableInt) o).getValue();
                map.setGridWidth(value);
                break;
            }
            case "Tile Width": {
                value = ((ObservableInt) o).getValue();
                map.setTileWidth(value);
                break;
            }
            case "Map Height": {
                value = ((ObservableInt) o).getValue();
                map.setGridHeight(value);
                break;
            }
            case "Tile Height": {
                value = ((ObservableInt) o).getValue();
                map.setTileHeight(value);
                break;
            }
        }
        mapPanel.setPreferredSize(new Dimension(map.getGridWidthInPixels(), map.getGridHeightInPixels()));
        mapScrollPane.updateUI();
        this.repaint();
    }
}
