package GUIWindows;

import Data.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TablePlanner extends JTable {

    private JPanel tablePane;

    private TimeTable timeTable;
    private AbstractTableModel tableModel;

    private int day = 0;

    private JButton nextDay, previousDay;
    private JLabel textDay;

    private ArrayList<Performance> performances;
    private ArrayList<Stage> stages;

    public TablePlanner(TimeTable timeTable) {
        this.timeTable = timeTable;
        performances = timeTable.getDay(day).getPerformances();
        stages = timeTable.getDay(day).getStages();
        this.setModel(tableModel = new AbstractTableModel() {

            @Override
            public int getRowCount() {
                if (performances.size() < 1) {
                    return 0;
                }
                return performances.size();
            }

            @Override
            public int getColumnCount() {
                return 7;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Performance p = performances.get(rowIndex);
                if (columnIndex == 0) {
                    return columnIndex + 1;
                }
                if (columnIndex == 1) {
                    return p.getArtistList().get(0).getName();
                }
                if (columnIndex == 2){
                    for(Stage stage: stages){
                        if(stage.getPerformances().contains(p)){
                            return stage.getHost().getName();
                        }
                    }
                }
                if(columnIndex == 3){
                    return p.getGenre();
                }
                if(columnIndex == 4){
                   for(Stage stage : stages) {
                       if(stage.getPerformances().contains(p)){
                           return stage.getName();
                       }
                   }
                }
                if(columnIndex == 5){
                    return p.getStartTime();
                }
                if(columnIndex == 6){
                    return p.getEndTime();
                }
        
                return null;
            }

            @Override
            public String getColumnName(int column) {
                if (column == 0) {
                    return "No #";
                } else if (column == 1) {
                    return "Artist";
                } else if (column == 2) {
                    return "Host";
                } else if (column == 3) {
                    return "Genre";
                } else if (column == 4) {
                    return "Stage";
                } else if (column == 5) {
                    return "Start tijd";
                } else if (column == 6) {
                    return "Eind tijd";
                }
                return "";
            }
        });
        getTableHeader().setReorderingAllowed(false);
        setAutoCreateRowSorter(true);

        tablePane = new JPanel(new BorderLayout());
        tablePane.add(new JScrollPane(this), BorderLayout.CENTER);
        tablePane.add(getUnderPane(), BorderLayout.SOUTH);

        tableModel.fireTableDataChanged();
    }

    public JPanel getPanel() {
        return tablePane;
    }

    private JPanel getUnderPane() {
        JPanel bottomBar = new JPanel(new FlowLayout());
        nextDay = new JButton("Volgende dag");
        previousDay = new JButton("Vorige dag");
        textDay = new JLabel(timeTable.getDay(day).getDateString());

        bottomBar.add(previousDay);
        bottomBar.add(textDay);
        bottomBar.add(nextDay);

        previousDay.setEnabled(false);
        nextDay.setEnabled(false);

        nextDay.addActionListener(e -> {
            if (day < timeTable.getDays().size() - 1) {
                day++;
            }
            refreshTable();
        });
        previousDay.addActionListener(e -> {
            if (day >= 1) {
                day--;
            }
            refreshTable();
        });
        return bottomBar;
    }

    public void refresh()
    {
        performances = timeTable.getDay(day).getPerformances();
        revalidate();
        repaint();
    }

    public void refreshTable() {
        stages = timeTable.getDay(day).getStages();
        performances = timeTable.getDay(day).getAllPerfomances();
        textDay.setText(timeTable.getDay(day).getDateString());

        if(day > 0 && day < timeTable.getDays().size() - 1) {
            nextDay.setEnabled(true);
            previousDay.setEnabled(true);
        }
        else {
            if (day == timeTable.getDays().size() - 1) {
                nextDay.setEnabled(false);
            }
            if (day == 0) {
                previousDay.setEnabled(false);
            }
            if (day < timeTable.getDays().size() - 1) {
                nextDay.setEnabled(true);
            }
            if (day > 0) {
                previousDay.setEnabled(true);
            }
        }
        tableModel.fireTableDataChanged();
    }

    public void reloadTable() {
        day = 0;
        refreshTable();
    }
}

