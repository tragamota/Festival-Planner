package Data; /**
 * Created by brandonvanostaden on 30/01/2017.
 */
import java.io.Serializable;
import java.time.LocalTime;

public class Host implements Serializable{
    private TimeTable timeTable;

    private String name;
    private LocalTime startTime;
    private LocalTime endTime;

    public Host(TimeTable timeTable, String name, String startTime, String endTime) {
        this.timeTable = timeTable;
        this.name = name;
        this.startTime = LocalTime.parse(startTime) ;
        this.endTime = LocalTime.parse(endTime);
        timeTable.addToHosts(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = LocalTime.parse(startTime);
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = LocalTime.parse(endTime);
    }

    public String getTime(LocalTime time) {
        return "" + time.getHour() + ":" + time.getMinute();
    }
}
