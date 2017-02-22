package ComponentManager.Panels;

import ComponentManager.ComponentManager;
import Data.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.util.ArrayList;

public class DayPanel extends JPanel
{
    private ComponentManager compManager;
    private TimeTable timeTable;

    private ArrayList<Day> days;
    private JTable daysTable = new JTable(20, 1);
    private AbstractTableModel tableModel;

    private ArrayList<Stage> stages;
    private JComboBox stageInput;

    public DayPanel(ComponentManager compManager, TimeTable timeTable)
    {
        super(new BorderLayout());
        this.compManager = compManager;
        this.timeTable = timeTable;

        days = timeTable.getDays();
        stages = timeTable.getStages();

        stageInput = new JComboBox(new DefaultComboBoxModel());

        // table
        JScrollPane scrollPane = new JScrollPane(new JViewport());
        scrollPane.setViewportView(daysTable);

        daysTable.setModel(tableModel = new AbstractTableModel()
        {
            @Override
            public int getRowCount()
            {
                if (days.size() < 1)
                {
                    return 0;
                }
                return days.size();
            }

            @Override
            public int getColumnCount() {
                return 2;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex)
            {
                Day day = days.get(rowIndex);
                if (columnIndex == 0) {
                    return day.getDateString();
                }

                if (columnIndex == 1) {
                    String stagesToString = "";
                    for(int i = 0; i < day.getStages().size(); i++)
                    {
                        if(i != day.getStages().size() - 1)
                        {
                            stagesToString += day.getStages().get(i).getName() + ", ";
                        }
                        else
                        {
                            stagesToString += day.getStages().get(i).getName();
                        }
                    }
                    return stagesToString;
                }
                return null;
            }

            @Override
            public String getColumnName(int column)
            {
                if(column == 0)
                {
                    return "Datum";
                }
                else if(column == 1)
                {
                    return "Podia";
                }
                return "";
            }
        });
        daysTable.getTableHeader().setReorderingAllowed(false);
        daysTable.setAutoCreateRowSorter(true);
        daysTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // controls
        JComponent controls = new JPanel(new BorderLayout());

        JComboBox dayInput = new JComboBox();
        JComboBox monthInput = new JComboBox();
        JComboBox yearInput = new JComboBox();
        stageInput = new JComboBox();

        for(int i = 1; i <= 31; i++)
        {
            dayInput.addItem(i);
        }

        for(int i = 1; i <= 12; i++)
        {
            monthInput.addItem(i);
        }

        for(int i = 2017; i <= 2099; i++)
        {
            yearInput.addItem(i);
        }

        for(int i = 0; i < stages.size(); i++)
        {
            stageInput.addItem(stages.get(i).getName());
        }

        JButton createButton = new JButton("Aanmaken");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    new Day(timeTable, (int)dayInput.getSelectedItem(), (int)monthInput.getSelectedItem(), (int)yearInput.getSelectedItem());
                    compManager.updateTables();
                }
                catch(DateTimeException error)
                {
                    JOptionPane.showMessageDialog(compManager, "U heeft een ongeldige datum ingevoerd.", "Foutmelding", JOptionPane.OK_OPTION);
                }
            }
        });

        JButton deleteButton = new JButton("Verwijderen");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Row: " + daysTable.getSelectedRow());
                System.out.println("Removed: " + days.get(daysTable.getSelectedRow()));
                days.remove(daysTable.getSelectedRow());
                compManager.updateTables();
            }
        });

        JButton addStageButton = new JButton("Toevoegen");
        addStageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                timeTable.getDays().get(daysTable.getSelectedRow()).addStage(stages.get(stageInput.getSelectedIndex()));
                compManager.updateTables();
            }
        });

        JButton resetStagesButton = new JButton("Resetten");
        resetStagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                timeTable.getDays().get(daysTable.getSelectedRow()).removeAllStages();
                compManager.updateTables();
            }
        });

        JComponent labelPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        labelPanel.add(new JLabel("Dag: "));
        labelPanel.add(new JLabel("Maand: "));
        labelPanel.add(new JLabel("Jaar: "));
        labelPanel.add(new JLabel("Podia: "));

        JComponent inputPanel = new JPanel(new GridLayout(4, 3, 5, 5));
        inputPanel.add(dayInput); inputPanel.add(new JLabel("")); inputPanel.add(new JLabel(""));
        inputPanel.add(monthInput); inputPanel.add(new JLabel("")); inputPanel.add(new JLabel(""));
        inputPanel.add(yearInput); inputPanel.add(new JLabel("")); inputPanel.add(new JLabel(""));
        inputPanel.add(stageInput); inputPanel.add(addStageButton); inputPanel.add(resetStagesButton);

        JComponent buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);

        controls.add(labelPanel, BorderLayout.WEST);
        controls.add(inputPanel, BorderLayout.CENTER);
        controls.add(buttonPanel, BorderLayout.SOUTH);

        if(days != null)
        {
            for (int i = 0; i < days.size(); i++)
            {
                daysTable.add(new JLabel("" + days.get(i).getDateString()));
            }
        }

        add(scrollPane, BorderLayout.NORTH);
        add(controls, BorderLayout.CENTER);
    }

    public void setDays()
    {
        days = timeTable.getDays();
    }

    public void setStages()
    {
        stages = timeTable.getStages();
    }

    public void refresh()
    {
        stageInput.removeAllItems();
        stageInput.setModel(new DefaultComboBoxModel(compManager.fillComboBoxes(0)));
        revalidate();
        repaint();
    }
}
