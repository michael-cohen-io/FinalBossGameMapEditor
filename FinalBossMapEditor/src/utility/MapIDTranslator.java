package utility;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import utility.ImageSplitter;

/*
 * Until model-specific metadata can be directly added from the editor, 
 */
public class MapIDTranslator {

    //Enums
    private enum AreaEffect {

        DEFAULT, TAKEDAMAGE, HEAL, INSTANTDEATH, LEVELUP;
    }

    private enum Terrain {

        DEFAULT, GRASS, WATER, MOUNTAIN
    }

    public static final int SIZE = 99;

    //private static File tileSheet = new File("resources/tiles-map.png");
    private static BufferedImage img = null;
    private static Terrain[] terrain = new Terrain[SIZE];
    private static AreaEffect[] areaEffects = new AreaEffect[SIZE];
    private BufferedImage[] tiles = new BufferedImage[SIZE];
    //private int width, height;

    public MapIDTranslator() {
        for (int i = 0; i < SIZE; ++i) {
            terrain[i] = Terrain.DEFAULT;
            areaEffects[i] = AreaEffect.DEFAULT;
        }

        try {
            img = ImageIO.read(getClass().getResource("/resources/tiles-map.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        tiles = setTile();
    }

    /*public BufferedImage getTile(String id) {

        if (id.equals("0")) {
            System.out.println("Invalid Argument");
        }
        return tiles[id - 1];
    }*/

    /*public Terrain getTerrain(String id) {
        if (id.equals("0")) {
            System.out.println("Invalid Argument");
        }
        return terrain[id - 1];
    }*/

    public AreaEffect getAreaEffect(int id) {
        if (id < 1) {
            System.out.println("Invalid Argument");
        }
        return areaEffects[id - 1];
    }

    private BufferedImage[] setTile() {
        BufferedImage[] tiles = new BufferedImage[SIZE];
        ImageSplitter splitter = new ImageSplitter(img, 32, 32);
        int counter = 0;
        for (int j = 0; j < splitter.getY(); ++j) {
            for (int i = 0; i < splitter.getX(); ++i) {
                tiles[counter++] = splitter.getTile(i, j);
            }
        }
        return tiles;
    }

    // for local testing
	/*
     public static void main(String [] args){
		
     MapIDTranslator m = new MapIDTranslator();
     JFrame j = new JFrame("Test");
     j.setSize(100, 100);
     j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
     BufferedImage im = m.getTile(25);
		
     JLabel tile = new JLabel(new ImageIcon(im));
     j.getContentPane().add(tile);
     j.setVisible(true);
     }
     */
}
