package Data; /**
 * Created by brandonvanostaden on 30/01/2017.
 */

import java.io.Serializable;
import java.util.*;

public class Stage implements Serializable {
    private String name;
    private double sizeX;
    private double sizeY;
    private int maxArtists;
    private ArrayList<Performance> performances;
    private Host host;

    private TimeTable timeTable;

    public Stage(TimeTable timeTable, String name, double sizeX, double sizeY, int maxArtists, Host host)
    {
        this.timeTable = timeTable;
        this.timeTable.addToStages(this);
        this.performances = new ArrayList<>();
        this.name = name;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.maxArtists = maxArtists;
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSizeX() {
        return sizeX;
    }

    public void setSizeX(double sizeX) {
        this.sizeX = sizeX;
    }

    public double getSizey() {
        return sizeY;
    }

    public void setSizey(double sizeY) {
        this.sizeY = sizeY;
    }

    public int getMaxArtists() {
        return maxArtists;
    }

    public void setMaxArtists(int maxArtists) {
        this.maxArtists = maxArtists;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }
    public void removeHost() { host = null; }

    public void addPerformance(Performance performance) {
        performances.add(performance);
    }

    public void removeAllPerformances() { performances.clear(); }

    public ArrayList<Performance> getPerformances() {
        return performances;
    }

    public Performance getSinglePerformance(int index) {
        return performances.get(index);
    }

    public Performance getSingleStage(int index)  {
        return performances.get(index);
    }

    public void removeStage(int index) throws IndexOutOfBoundsException {
        ArrayList<Performance> tempPerformances = performances;
        Iterator<Performance> it = performances.iterator();
        while(it.hasNext()) {
            Performance tempPerform = it.next();
            if(tempPerform.equals(performances.get(index))) {
                it.remove();
            }
        }
    }

}
