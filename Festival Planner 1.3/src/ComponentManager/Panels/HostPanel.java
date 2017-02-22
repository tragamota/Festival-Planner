package ComponentManager.Panels;

import ComponentManager.ComponentManager;
import Data.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HostPanel extends JPanel
{
    private ComponentManager compManager;
    private TimeTable timeTable;

    private ArrayList<Host> hosts;
    private JTable hostsTable = new JTable(20, 1);
    private AbstractTableModel tableModel;

    public HostPanel(ComponentManager compManager, TimeTable timeTable)
    {
        super(new BorderLayout());
        this.compManager = compManager;
        this.timeTable = timeTable;

        hosts = timeTable.getHosts();

        // table
        JScrollPane scrollPane = new JScrollPane(new JViewport());
        scrollPane.setViewportView(hostsTable);

        hostsTable.setModel(tableModel = new AbstractTableModel()
        {
            @Override
            public int getRowCount()
            {
                if (hosts.size() < 1)
                {
                    return 0;
                }
                return hosts.size();
            }

            @Override
            public int getColumnCount()
            {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex)
            {
                Host host = hosts.get(rowIndex);
                if(columnIndex == 0)
                {
                    return host.getName();
                }
                else if(columnIndex == 1)
                {
                    return host.getStartTime();
                }
                else if(columnIndex == 2)
                {
                    return host.getEndTime();
                }
                return null;
            }

            @Override
            public String getColumnName(int column)
            {
                if(column == 0)
                {
                    return "Naam";
                }
                else if(column == 1)
                {
                    return "Starttijd";
                }
                else if(column == 2)
                {
                    return "Eindtijd";
                }
                return "";
            }
        });
        hostsTable.getTableHeader().setReorderingAllowed(false);
        hostsTable.setAutoCreateRowSorter(true);
        hostsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // controls
        JComponent controls = new JPanel(new BorderLayout());

        JTextField hostNameInput = new JTextField();
        hostNameInput.setMaximumSize(new Dimension(40, 200));

        JComboBox startHour = new JComboBox();
        JComboBox startMin = new JComboBox();
        JComboBox endHour = new JComboBox();
        JComboBox endMin = new JComboBox();

        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                startHour.addItem("0" + i);
                endHour.addItem("0" + i);
            } else {
                startHour.addItem(i);
                endHour.addItem(i);
            }
        }

        for (int i = 0; i < 60; i += 15) {
            if (i < 10) {
                startMin.addItem("00");
                endMin.addItem("00");
            } else {
                startMin.addItem(i);
                endMin.addItem(i);
            }
        }

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new Host(timeTable, hostNameInput.getText(), (startHour.getSelectedItem() + ":" + startMin.getSelectedItem()), (endHour.getSelectedItem() + ":" + endMin.getSelectedItem()));
                hostNameInput.setText("");

                compManager.updateTables();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                hosts.remove(hostsTable.getSelectedRow());

                compManager.updateTables();
            }
        });

        JComponent labelPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        labelPanel.add(new JLabel("Naam host: "));
        labelPanel.add(new JLabel("Starttijd: "));
        labelPanel.add(new JLabel("Eindtijd: "));

        JComponent inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(hostNameInput); inputPanel.add(new JLabel(""));
        inputPanel.add(startHour); inputPanel.add(startMin);
        inputPanel.add(endHour); inputPanel.add(endMin);

        JComponent buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);

        controls.add(labelPanel, BorderLayout.WEST);
        controls.add(inputPanel, BorderLayout.CENTER);
        controls.add(buttonPanel, BorderLayout.SOUTH);

        if(hosts != null)
        {
            for (int i = 0; i < hosts.size(); i++)
            {
                hostsTable.add(new JLabel("" + hosts.get(i).getName()));
            }
        }

        add(scrollPane, BorderLayout.NORTH);
        add(controls, BorderLayout.SOUTH);
    }

    public void refresh()
    {
        hostsTable.revalidate();
        repaint();
    }
}
