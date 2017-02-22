package ComponentManager.Panels;

import ComponentManager.ComponentManager;
import Data.Artist;
import Data.Day;
import Data.Stage;
import Data.TimeTable;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ArtistPanel extends JPanel
{
    private ComponentManager compManager;
    private TimeTable timeTable;

    private ArrayList<Artist> artists;
    private JTable artistsTable = new JTable(20, 1);
    private AbstractTableModel tableModel;

    public ArtistPanel(ComponentManager compManager, TimeTable timeTable)
    {
        super(new BorderLayout());
        this.compManager = compManager;
        this.timeTable = timeTable;

        artists = timeTable.getArtists();

        // table
        JScrollPane scrollPane = new JScrollPane(new JViewport());
        scrollPane.setViewportView(artistsTable);

        artistsTable.setModel(tableModel = new AbstractTableModel()
        {
            @Override
            public int getRowCount()
            {
                if (artists.size() < 1)
                {
                    return 0;
                }
                return artists.size();
            }

            @Override
            public int getColumnCount()
            {
                return 2;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex)
            {
                Artist artist = artists.get(rowIndex);
                if(columnIndex == 0)
                {
                    return artist.getName();
                }
                else if(columnIndex == 1)
                {
                    return (int)(artist.getPopularity() * 100) + "%";
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
                    return "Populariteit";
                }
                return "";
            }
        });
        artistsTable.getTableHeader().setReorderingAllowed(false);
        artistsTable.setAutoCreateRowSorter(true);
        artistsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // controls
        JComponent controls = new JPanel(new BorderLayout());

        JComponent labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JComponent inputPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JComponent buttonPanel = new JPanel();

        JTextField artistNameInput = new JTextField();
        artistNameInput.setMaximumSize(new Dimension(40, 200));
        JSlider artistPopularity = new JSlider(0, 100, 50);
        artistPopularity.setMajorTickSpacing(50);
        artistPopularity.setMinorTickSpacing(10);
        artistPopularity.setPaintTicks(true);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new Artist(timeTable, artistNameInput.getText(), (artistPopularity.getValue() * 0.01));
                artistNameInput.setText("");
                artistPopularity.setValue(50);

                compManager.updateTables();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                artists.remove(artistsTable.getSelectedRow());

                compManager.updateTables();
            }
        });

        labelPanel.add(new JLabel("Naam artiest: "));
        labelPanel.add(new JLabel("Populariteit: "));
        inputPanel.add(artistNameInput);
        inputPanel.add(artistPopularity);
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);

        controls.add(labelPanel, BorderLayout.WEST);
        controls.add(inputPanel, BorderLayout.CENTER);
        controls.add(buttonPanel, BorderLayout.SOUTH);

        // updateTables();

        if(artists != null)
        {
            for (int i = 0; i < artists.size(); i++)
            {
                artistsTable.add(new JLabel("" + artists.get(i).getName()));
            }
        }

        add(scrollPane, BorderLayout.NORTH);
        add(controls, BorderLayout.SOUTH);
    }

    public void refresh()
    {
        artistsTable.revalidate();
        repaint();
    }
}
