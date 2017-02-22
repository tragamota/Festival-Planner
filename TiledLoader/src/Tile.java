import java.awt.image.BufferedImage;

/**
 * Created by brandonvanostaden on 20/02/2017.
 */
public class Tile {

    BufferedImage image;

    public Tile(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}
