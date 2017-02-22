package ComponentManager.Panels;

import ComponentManager.ComponentManager;
import Data.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StagePanel extends JPanel
{
    private ComponentManager compManager;
    private TimeTable timeTable;

    private ArrayList<Stage> stages;
    private JTable stagesTable = new JTable(20, 1);
    private AbstractTableModel tableModel;

    private ArrayList<Performance> performances;
    private JComboBox performanceInput = new JComboBox(new DefaultComboBoxModel());

    private ArrayList<Host> hosts;
    private JComboBox hostInput = new JComboBox(new DefaultComboBoxModel());

    public StagePanel(ComponentManager compManager, TimeTable timeTable)
    {
        super(new BorderLayout());
        this.compManager = compManager;
        this.timeTable = timeTable;

        stages = timeTable.getStages();
        performances = timeTable.getPerformances();
        hosts = timeTable.getHosts();

        // table
        JScrollPane scrollPane = new JScrollPane(new JViewport());
        scrollPane.setViewportView(stagesTable);

        stagesTable.setModel(tableModel = new AbstractTableModel()
        {
            @Override
            public int getRowCount()
            {
                if (stages.size() < 1)
                {
                    return 0;
                }
                return stages.size();
            }

            @Override
            public int getColumnCount()
            {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex)
            {
                Stage stage = stages.get(rowIndex);
                if(columnIndex == 0)
                {
                    return stage.getName();
                }
                else if(columnIndex == 1)
                {
                    String performancesToString = "";
                    for (int i = 0; i < stage.getPerformances().size(); i++) {
                        if (i != stage.getPerformances().size() - 1) {
                            performancesToString += stage.getPerformances().get(i).getArtistNames() + ", ";
                        } else {
                            performancesToString += stage.getPerformances().get(i).getArtistNames();
                        }
                    }
                    return performancesToString;
                }
                else if(columnIndex == 2)
                {
                    stage.getHost();
                }


                return null;
            }

            @Override
            public String getColumnName(int column)
            {
                if(column == 0)
                {
                    return "Podium";
                }
                else if(column == 1)
                {
                    return "Optredens";
                }
                else if(column == 2)
                {
                    return "Host";
                }
                return "";
            }
        });
        stagesTable.getTableHeader().setReorderingAllowed(false);
        stagesTable.setAutoCreateRowSorter(true);
        stagesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // controls
        JComponent controls = new JPanel(new BorderLayout(5, 5));

        JTextField stageNameInput = new JTextField();
        JTextField stageXInput = new JTextField();
        JTextField stageYInput = new JTextField();

        JComboBox stageCapacityInput = new JComboBox();
        for(int i = 1; i <= 10; i++)
        {
            stageCapacityInput.addItem(i);
        }

        JButton createButton = new JButton("Aanmaken");
        createButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new Stage(timeTable, stageNameInput.getText(), Double.parseDouble(stageXInput.getText()), Double.parseDouble(stageYInput.getText()), 3, new Host(timeTable, "Karel", "11:00", "12:00"));
                stageNameInput.setText("");
                stageXInput.setText("");
                stageYInput.setText("");
                compManager.updateTables();
            }
        });

        JButton deleteButton = new JButton("Verwijderen");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Row: " + stagesTable.getSelectedRow());
                System.out.println("Removed: " + stages.get(stagesTable.getSelectedRow()));
                stages.remove(stagesTable.getSelectedRow());
                compManager.updateTables();
            }
        });

        JButton addPerformanceButton = new JButton("Toevoegen");
        addPerformanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                timeTable.getStages().get(stagesTable.getSelectedRow()).addPerformance(performances.get(performanceInput.getSelectedIndex()));
                compManager.updateTables();
            }
        });

        JButton resetPerformancesButton = new JButton("Resetten");
        resetPerformancesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                timeTable.getStages().get(stagesTable.getSelectedRow()).removeAllPerformances();
                compManager.updateTables();
            }
        });


        JButton addHostButton = new JButton("Toevoegen");
        addPerformanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                timeTable.getStages().get(stagesTable.getSelectedRow()).setHost(hosts.get(hostInput.getSelectedIndex()));
                compManager.updateTables();
            }
        });

        JButton resetHostButton = new JButton("Resetten");
        resetPerformancesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                timeTable.getStages().get(stagesTable.getSelectedRow()).removeHost();
                compManager.updateTables();
            }
        });

        JComponent labelPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        labelPanel.add(new JLabel("Naam podium: "));
        labelPanel.add(new JLabel("Grootte (x, y): "));
        labelPanel.add(new JLabel("Capaciteit: "));
        labelPanel.add(new JLabel("Optredens: "));
        labelPanel.add(new JLabel("Host: "));

        JComponent inputPanel = new JPanel(new GridLayout(5, 3, 5, 5));
        inputPanel.add(stageNameInput);     inputPanel.add(new JLabel(""));    inputPanel.add(new JLabel(""));
        inputPanel.add(stageXInput);        inputPanel.add(stageYInput);            inputPanel.add(new JLabel(""));
        inputPanel.add(stageCapacityInput); inputPanel.add(new JLabel(""));    inputPanel.add(new JLabel(""));
        inputPanel.add(performanceInput);   inputPanel.add(addPerformanceButton);   inputPanel.add(resetPerformancesButton);
        inputPanel.add(hostInput);          inputPanel.add(addHostButton);          inputPanel.add(resetHostButton);

        JComponent buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);

        controls.add(labelPanel, BorderLayout.WEST);
        controls.add(inputPanel, BorderLayout.CENTER);
        controls.add(buttonPanel, BorderLayout.SOUTH);

        if(stages != null)
        {
            for (int i = 0; i < stages.size(); i++)
            {
                stagesTable.add(new JLabel("" + stages.get(i).getName()));
            }
        }

        add(scrollPane, BorderLayout.NORTH);
        add(controls, BorderLayout.CENTER);
    }

    public void refresh()
    {
        performanceInput.removeAllItems();
        performanceInput.setModel(new DefaultComboBoxModel(compManager.fillComboBoxes(1)));
        hostInput.removeAllItems();
        hostInput.setModel(new DefaultComboBoxModel(compManager.fillComboBoxes(3)));
        revalidate();
        repaint();
    }
}
