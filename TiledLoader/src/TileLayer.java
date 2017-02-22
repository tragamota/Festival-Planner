import javax.json.JsonArray;
import java.util.ArrayList;

/**
 * Created by brandonvanostaden on 20/02/2017.
 */
public class TileLayer {
    private String layerName;
    private ArrayList<Integer> layerIDList;

    public TileLayer(String name, JsonArray layerValues) {
        layerName = name;
        layerIDList = new ArrayList<>();
        buildArrayList(layerValues);
    }

    public ArrayList<Integer> getLayerIDList() {
        return layerIDList;
    }

    public String getLayerName() {
        return layerName;
    }

    private void buildArrayList(JsonArray layerValues) {
        try {
            for (int i = 0; i < layerValues.size(); i++) {
                layerIDList.add(layerValues.getInt(i));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}