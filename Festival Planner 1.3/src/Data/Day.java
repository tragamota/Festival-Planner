package Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Day implements Serializable{
    private ArrayList<Stage> stages;
    private LocalDate date;
    private TimeTable timeTable;

    public Day(TimeTable timeTable)
    {
        this.timeTable = timeTable;
        this.timeTable.addToDays(this);
        stages = new ArrayList<>();
        date = LocalDate.now();
    }

    public Day(TimeTable timeTable, int day, int month, int year)
    {
        this.timeTable = timeTable;
        this.timeTable.addToDays(this);
        stages = new ArrayList<>();
        date = LocalDate.of(year, month, day);
    }

    public Day(TimeTable timeTable, int test)
    {
        this.timeTable = timeTable;
        stages = new ArrayList<>();
        date = LocalDate.now();
        stages.add(new Stage(timeTable, "Test", 2.66, 2.77, 1, new Host(timeTable, "Bernard", "12:30", "16:30")));
        stages.get(0).addPerformance(new Performance(timeTable, new Artist(timeTable, "thiesto", 0.8), "Rock", "15:30", "16:30" ));
        stages.get(0).addPerformance(new Performance(timeTable, new Artist(timeTable, "HardWell", 0.8), "Trance", "16:30", "17:30" ));
        this.timeTable.addToDays(this);
    }

    public void addStage(Stage stage) {
        stages.add(stage);
    }

    public void removeStage(Stage stage) {
        Iterator it = stages.iterator();
        while(it.hasNext()) {
           Stage tempStage = (Stage) it.next();
           if(stage.equals(tempStage)) {
               it.remove();
           }
        }
    }

    public void removeAllStages() { stages.clear(); }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Stage getSingleStage(int index){
        return stages.get(index);
    }

    public int getTotalPerformances() {
        int totalPerfomances = 0;
        for(int i = 0; i < stages.size(); i++) {
            totalPerfomances += stages.get(i).getPerformances().size();
        }
        return totalPerfomances;
    }

    public ArrayList<Performance> getAllPerfomances() {
        ArrayList<Performance> tempPerformances = new ArrayList<>();
        for (int i = 0; i < stages.size(); i++) {
            for (int j = 0; j < stages.get(i).getPerformances().size(); j++)
                tempPerformances.add(stages.get(i).getSinglePerformance(j));
        }
        return tempPerformances;
    }


    public void setDate(int day, int month, int year) {
        date = LocalDate.of(year, month, day);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDateString() {
        String s = "";
        s += date.getDayOfWeek().toString();
        s += " " + date.getDayOfMonth();
        s += " " + date.getMonth().toString();
        s += " " + date.getYear();
        return s.toLowerCase();
    }

    public ArrayList<Performance> getPerformances()
    {
        ArrayList<Performance> performancesOfTheDay = new ArrayList<>();

        for(Stage stage : stages)
        {
            for(Performance performance : stage.getPerformances())
            {
                performancesOfTheDay.add(performance);
            }
        }
        System.out.println(performancesOfTheDay + " !!!");
        return performancesOfTheDay;
    }
}