import javax.swing.*;
import java.awt.*;

/**
 * Created by Ian on 20-2-2017.
 */
public class GUI extends JPanel {

    private TileMap tileMap;

    public GUI() {
        JFrame frame = new JFrame("Tile Loader");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));

        tileMap = new TileMap("Maps/Festival Map.json");

        frame.setContentPane(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(getWidth() / 2, getHeight() / 2);
        g2d.scale(2, 2);

        for (int i = 0; i < (20 * 16); i += 16) {
            g2d.drawImage(tileMap.getTile().getImage(), null, -tileMap.getTile().getImage().getWidth() / 2 + i, -tileMap.getTile().getImage().getHeight() / 2);
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}
