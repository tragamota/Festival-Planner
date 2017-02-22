package ComponentManager.Panels;

import ComponentManager.ComponentManager;
import Data.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PerformancePanel extends JPanel
{
    private ComponentManager compManager;
    private TimeTable timeTable;

    private ArrayList<Performance> performances;
    private JTable performancesTable = new JTable(20, 1);
    private AbstractTableModel tableModel;

    private ArrayList<Artist> artists;
    private JComboBox artistInput = new JComboBox(new DefaultComboBoxModel());

    public PerformancePanel(ComponentManager compManager, TimeTable timeTable)
    {
        super(new BorderLayout());
        this.compManager = compManager;
        this.timeTable = timeTable;

        performances = timeTable.getPerformances();
        artists = timeTable.getArtists();

        // table
        JScrollPane scrollPane = new JScrollPane(new JViewport());
        scrollPane.setViewportView(performancesTable);

        performancesTable.setModel(tableModel = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                if (performances.size() < 1) {
                    return 0;
                }
                return performances.size();
            }

            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Performance performance = performances.get(rowIndex);
                if (columnIndex == 0) {
                    return performance.getArtistNames();
                } else if (columnIndex == 1) {
                    return performance.getGenre();
                } else if (columnIndex == 2) {
                    return performance.getStartTime();
                } else if (columnIndex == 3) {
                    return performance.getEndTime();
                }
                return null;
            }

            @Override
            public String getColumnName(int column) {
                if (column == 0) {
                    return "Artiesten";
                } else if (column == 1) {
                    return "Genre";
                } else if (column == 2) {
                    return "Begintijd";
                } else if (column == 3) {
                    return "Eindtijd";
                }
                return "";
            }
        });
        performancesTable.getTableHeader().setReorderingAllowed(false);
        performancesTable.setAutoCreateRowSorter(true);
        performancesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // controls
        JComponent controls = new JPanel(new BorderLayout());

        JTextField genreInput = new JTextField();
        JComboBox startHour = new JComboBox();
        JComboBox startMin = new JComboBox();
        JComboBox endHour = new JComboBox();
        JComboBox endMin = new JComboBox();

        for (
                Artist artist : timeTable.getArtists()) {
            artistInput.addItem(artist.getName());
        }

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

        JButton createButton = new JButton("Aanmaken");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Performance(timeTable, artists.get(artistInput.getSelectedIndex()), genreInput.getText(), (startHour.getSelectedItem() + ":" + startMin.getSelectedItem()), (endHour.getSelectedItem() + ":" + endMin.getSelectedItem()));
                genreInput.setText("");
                compManager.updateTables();
            }
        });

        JButton deleteButton = new JButton("Verwijderen");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Row: " + performancesTable.getSelectedRow());
                System.out.println("Removed: " + performances.get(performancesTable.getSelectedRow()));
                performances.remove(performancesTable.getSelectedRow());
                compManager.updateTables();
            }
        });

        JButton addArtistButton = new JButton("Toevoegen");
        addArtistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeTable.getPerformances().get(performancesTable.getSelectedRow()).addArtist(artists.get(artistInput.getSelectedIndex()));
                compManager.updateTables();
            }
        });

        JButton resetArtistsButton = new JButton("Resetten");
        resetArtistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeTable.getPerformances().get(performancesTable.getSelectedRow()).removeAllArtists();
                compManager.updateTables();
            }
        });

        JComponent labelPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        labelPanel.add(new JLabel("Artiesten: "));
        labelPanel.add(new JLabel("Genre: "));
        labelPanel.add(new JLabel("Begintijd: "));
        labelPanel.add(new JLabel("Eindtijd: "));

        JComponent inputPanel = new JPanel(new GridLayout(4, 3, 5, 5));
        inputPanel.add(artistInput);    inputPanel.add(addArtistButton);        inputPanel.add(resetArtistsButton);
        inputPanel.add(genreInput);     inputPanel.add(new JLabel(""));    inputPanel.add(new JLabel(""));
        inputPanel.add(startHour);      inputPanel.add(startMin);               inputPanel.add(new JLabel(""));
        inputPanel.add(endHour);        inputPanel.add(endMin);                 inputPanel.add(new JLabel(""));

        JComponent buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);

        controls.add(labelPanel, BorderLayout.WEST);
        controls.add(inputPanel, BorderLayout.CENTER);
        controls.add(buttonPanel, BorderLayout.SOUTH);

        setSize(new Dimension(250, 176));

        if (performances != null) {
            for (int i = 0; i < performances.size(); i++) {
                performancesTable.add(new JLabel("" + performances.get(i).getArtistList()));
            }
        }

        add(scrollPane, BorderLayout.NORTH);
        add(controls, BorderLayout.CENTER);
    }

    public void refresh()
    {
        artistInput.removeAllItems();
        artistInput.setModel(new DefaultComboBoxModel(compManager.fillComboBoxes(2)));
        revalidate();
        repaint();
    }
}
