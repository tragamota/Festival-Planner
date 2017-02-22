import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class TileSet {

    private ArrayList<Tile> tiles;
    private int tileWidth;
    private int tileHeight;

    public TileSet(String[] name, int tileWidth, int tileHeight) {
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        this.tiles = new ArrayList<>();
        try {
            BufferedImage alpha = ImageIO.read(getClass().getResource("Tileset\\Alpha.png")).getSubimage(0, 0, tileWidth, tileHeight);
            tiles.add(new Tile(alpha));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cutAllImages(name);
    }

    private void cutAllImages(String[] fileNames) {
        BufferedImage image;
        int imageWidth, imageHeight;

        int counter = 0;
        for (int i = 0; i < fileNames.length; i++) {
            try {
                image = ImageIO.read(getClass().getResource("Tileset\\" + fileNames[i]));
                imageWidth = image.getWidth();
                imageHeight = image.getHeight();
                for (int y = 0; y < imageHeight; y += tileHeight) {
                    for (int x = 0; x < imageWidth; x += tileWidth) {
                        BufferedImage cutImage = image.getSubimage(x, y, tileWidth, tileHeight);
                        if(AlphaTester.testAlhpa(tiles.get(0).getImage(), cutImage)) {
                            tiles.add(tiles.get(0));
                            counter++;
                        }
                        else {
                            tiles.add(new Tile(cutImage));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(counter);
        }
    }

    public Tile getTile(int index) {
        return tiles.get(index);
    }
}