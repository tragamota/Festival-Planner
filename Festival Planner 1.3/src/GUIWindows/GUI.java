/**
 * Created by B3 on 30/01/2017.
 */

package GUIWindows;
import Data.*;

import javax.swing.*;
import java.awt.*;

/*
    To do:
    - Previous/next buttons van de TablePlanner klasse werken niet goed.
    - Verwijderfunctie kan op dit moment per uitvoering van de Component Manager maar 1 component verwijderen.
    - Component Manager button layout hersorteren volgens "Nieuw component" / "Component wijzigen".
    - JScrollPanes (waar de component tables in staan) kleiner maken, en laten scrollen als de lijst te lang is.
    - Meerdere hosts per stage mogelijk maken.
    - Implementeren van "unusedArtists", "unusedHosts" e.d. in de ComboBoxes van de Component Manager panels.
    - Aantal Exceptions bij foute invoering van data, zoals lege/ongeldige velden.
*/

public class GUI extends JFrame {

    private JTabbedPane mainPanel;
    private TimeTable timeTable = new TimeTable();
    private TablePlanner tablePlanner;

    public static void main(String[] args)
    {
        new GUI();
    }

    public GUI()
    {
        super("Festival Planner");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        // setExtendedState(getExtendedState() | MAXIMIZED_BOTH);


        new Day(timeTable, 0);
        new Day(timeTable, 15, 2, 2017);
        new Day(timeTable, 16, 2, 2017);
        new Day(timeTable, 17, 2, 2017);

        tablePlanner = new TablePlanner(timeTable);

        setJMenuBar(new Menu(this, timeTable, tablePlanner));

        mainPanel = new JTabbedPane();
        setContentPane(mainPanel);
        mainPanel.addTab("Table", new JScrollPane(tablePlanner.getPanel()));
        mainPanel.addTab("2D Table", null);
        mainPanel.addTab("Simulator", null);

        setVisible(true);
    }

    public TimeTable getTimeTable()
    {
        return timeTable;
    }
}