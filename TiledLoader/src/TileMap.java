import java.util.ArrayList;
import javax.json.*;

/**
 * Created by brandonvanostaden on 20/02/2017.
 */
public class TileMap {

    private ArrayList<TileLayer> layers;
    private TileSet tilesCollection;

    private int width, height;

    private JsonReader reader;

    public TileMap(String fileName) {
        JsonReader reader = Json.createReader(getClass().getResourceAsStream(fileName));
        JsonObject inputJson = (JsonObject) reader.read();
        reader.close();
        this.layers = new ArrayList<>();
        this.width = inputJson.getInt("width");
        this.height = inputJson.getInt("height");

        JsonArray arrayLayers = inputJson.getJsonArray("layers");

        for (int i = 0; i < arrayLayers.size(); i++) {
            JsonObject tempLayer = arrayLayers.getJsonObject(i);
            JsonArray layerValues = tempLayer.getJsonArray("data");
            String name = tempLayer.getString("name");
            TileLayer layer = new TileLayer(name, layerValues);
            layers.add(layer);
        }

        JsonArray tileSetsArray = inputJson.getJsonArray("tilesets");
    String[] tileSetsFileNames = new String[tileSetsArray.size()];
        for (int i = 0; i < tileSetsArray.size(); i++) {
        JsonObject tileSetObjects = tileSetsArray.getJsonObject(0);
        String tileSetNameOfFile = tileSetObjects.getString("image");
        tileSetsFileNames[i] = tileSetNameOfFile;
    }
    TileSet tileSet = new TileSet(tileSetsFileNames, inputJson.getInt("tilewidth"), inputJson.getInt("tileheight"));
    tilesCollection = tileSet;
}

    public Tile getTile() {
        return tilesCollection.getTile(2247);
    }
}
