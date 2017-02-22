package Data;

import java.io.Serializable;

public class Artist implements Serializable {
    private String name;
    private double popularity;

    private TimeTable timeTable;

    public Artist(TimeTable timeTable, String name, double popularity)
    {
        this.timeTable = timeTable;
        this.timeTable.addToArtists(this);
        this.name = name;
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
