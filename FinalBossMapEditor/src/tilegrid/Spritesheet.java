package tilegrid;

import java.awt.image.BufferedImage;
import static main.Main.selectedTile;
import main.MapEditorWindow;

/**
 *
 * @author ChrisMoscoso
 */
public class Spritesheet extends TileGrid {

    private BufferedImage image;

    public Spritesheet(BufferedImage i) {
        super(0, 0);
        setImage(i);

    }

    public BufferedImage getImage() {
        return image;
    }

    public final void setImage(BufferedImage img) {
        if (img != null) {
            int gWidth = (img.getWidth() % TileGrid.getTileWidth() == 0)
                    ? img.getWidth() / TileGrid.getTileWidth() : (img.getWidth() / TileGrid.getTileWidth()) + 1;
            int gHeight = (img.getHeight() % TileGrid.getTileHeight() == 0)
                    ? img.getHeight() / TileGrid.getTileHeight() : (img.getHeight() / TileGrid.getTileHeight()) + 1;

            setGridWidth(gWidth);
            setGridHeight(gHeight);
        }

        this.image = img;

    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedTile(Tile t) {
        selectedTile = t;
        MapEditorWindow.previewSelectedTile.repaint();
        MapEditorWindow.idField.setText(selectedTile.getId()+"");
        System.out.println(selectedTile.getId());
        this.setChanged();
        this.notifyObservers();
    }

}
