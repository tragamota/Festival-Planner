package Data; /**
 * Created by brandonvanostaden on 30/01/2017.
 */

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.Date;
import java.util.*;

public class Performance implements Serializable{
    private ArrayList<Artist> artists = null;
    private String genre;
    private LocalTime startTime;
    private LocalTime endTime;
    private double popularity;
    private int amountOfPersons;

    private TimeTable timeTable;

    public Performance(TimeTable timeTable, Artist artist, String genre, String startTime, String endTime)
    {
        this.timeTable = timeTable;
        this.timeTable.addToPerformances(this);

        artists = new ArrayList<>();
        artists.add(artist);
        this.genre = genre;
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        try {
            this.startTime = LocalTime.parse(startTime);
        }
        catch(DateTimeException e) {
            e.printStackTrace();
        }
    }

    public void addArtist(Artist artist) { artists.add(artist); }

    public void removeAllArtists() { artists.clear(); }

    public ArrayList<Artist> getArtistList() {
        return artists;
    }

    public String getArtistNames()
    {
        String names = "";

        for(int i = 0; i < artists.size(); i++)
        {
            if(i != artists.size() - 1)
            {
                names += artists.get(i).getName() + " & ";
            }
            else
            {
                names += artists.get(i).getName();
            }
        }

        return names;
    }


    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = LocalTime.parse(endTime);
    }

    public String getTime(LocalTime t) {
        String uitkomst = "";
        uitkomst += t.getHour() + ":" + t.getMinute();
        return uitkomst;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public void calculateAndStorePopularity() {
        double calcPopularity = 0;
        for (Artist a : artists) {
            calcPopularity = calcPopularity + a.getPopularity();
        }
        this.popularity = calcPopularity / amountOfPersons;
    }

    public void calculateAndStoreAmountOfPersons() {
        this.amountOfPersons = artists.size();
    }
}
