package Data;

import java.io.Serializable;
import java.util.*;

public class TimeTable implements Serializable {
    private String projectName;
    private ArrayList<Artist> unusedArtists;
    private ArrayList<Host> unusedHosts;

    private ArrayList<Day> days;
    private ArrayList<Stage> stages;
    private ArrayList<Performance> performances;
    private ArrayList<Artist> artists;
    private ArrayList<Host> hosts;

    public TimeTable() {
        projectName = "Nieuw Data TimeTable";
        unusedArtists = new ArrayList<>();
        unusedHosts = new ArrayList<>();
        days = new ArrayList<>();
        stages = new ArrayList<>();
        performances = new ArrayList<>();
        artists = new ArrayList<>();
        hosts = new ArrayList<>();
    }

    public TimeTable(String projectName) {
        this.projectName = projectName;
        unusedArtists = new ArrayList<>();
        unusedHosts = new ArrayList<>();
        days = new ArrayList<>();
        stages = new ArrayList<>();
        performances = new ArrayList<>();
        artists = new ArrayList<>();
        hosts = new ArrayList<>();
    }

    public void setProjectname(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public ArrayList<Artist> getUnusedArtists() {
        return unusedArtists;
    }

    public void setUnusedArtists(ArrayList<Artist> unusedArtists) {
        this.unusedArtists = unusedArtists;
    }

    public ArrayList<Host> getUnusedHosts() {
        return unusedHosts;
    }

    public void setUnusedHosts(ArrayList<Host> unusedHosts) {
        this.unusedHosts = unusedHosts;
    }

    public Day getDay(int index) {
        return days.get(index);
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }

    public void addToDays(Day day)
    {
        days.add(day);
    }
    public void addToStages(Stage stage)
    {
        stages.add(stage);
    }
    public void addToPerformances(Performance performance)
    {
        performances.add(performance);
    }
    public void addToArtists(Artist artist) { artists.add(artist); }
    public void addToHosts(Host host) { hosts.add(host); }

    public ArrayList<Day> getDays() {
        return days;
    }
    public ArrayList<Stage> getStages() {
        return stages;
    }
    public ArrayList<Performance> getPerformances() {
        return performances;
    }
    public ArrayList<Artist> getArtists() { return artists; }
    public ArrayList<Host> getHosts() { return hosts; }
}