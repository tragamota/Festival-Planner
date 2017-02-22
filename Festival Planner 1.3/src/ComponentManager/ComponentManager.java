package ComponentManager;

import ComponentManager.Panels.*;
import Data.*;
import GUIWindows.TablePlanner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ComponentManager extends JFrame
{
    private TablePlanner tablePlanner;
    private ComponentManager instance;

    private JTabbedPane tabbedPane;
    private Dimension screenSize;

    private Data.TimeTable timeTable;
    private AbstractTableModel tableModel;

    private DayPanel dayPanel;
    private StagePanel stagePanel;
    private PerformancePanel performancePanel;
    private ArtistPanel artistPanel;
    private HostPanel hostPanel;

    private ArrayList<Day> days;
    private JTable daysTable = new JTable(20, 1);

    private ArrayList<Stage> stages;
    private JTable stagesTable = new JTable(20, 1);
    // private JComboBox stageInput = new JComboBox(new DefaultComboBoxModel());

    private ArrayList<Performance> performances;
    private JTable performancesTable = new JTable(20, 1);
    // private JComboBox performanceInput = new JComboBox(new DefaultComboBoxModel());

    private ArrayList<Artist> artists;
    private JTable artistsTable = new JTable(20, 1);
    // private JComboBox artistInput = new JComboBox(new DefaultComboBoxModel());

    private ArrayList<Host> hosts;
    private JTable hostsTable = new JTable(20, 1);
    // private JComboBox hostInput = new JComboBox(new DefaultComboBoxModel());

//    private DefaultComboBoxModel stageModel = new DefaultComboBoxModel(fillComboBoxes(0));
//    private DefaultComboBoxModel performanceModel = new DefaultComboBoxModel(fillComboBoxes(1));
//    private DefaultComboBoxModel artistModel = new DefaultComboBoxModel(fillComboBoxes(2));
//    private DefaultComboBoxModel hostModel = new DefaultComboBoxModel(fillComboBoxes(3));

    public ComponentManager(Data.TimeTable timeTable, TablePlanner tablePlanner)
    {
        super("Componenten beheer");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.timeTable = timeTable;
        this.tablePlanner = tablePlanner;

        instance = this;
        days = timeTable.getDays();
        stages = timeTable.getStages();
        performances = timeTable.getPerformances();
        artists = timeTable.getArtists();
        hosts = timeTable.getHosts();

        int displayWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int displayHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int windowWidth = 600;
        int windowHeight = 600;

        tabbedPane = new JTabbedPane();
        setContentPane(tabbedPane);

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                pack();
            }
        });

        buildDayPanel();
        buildStagePanel();
        buildPerformancePanel();
        buildArtistPanel();
        buildHostPanel();

        JMenuBar menuBar = new JMenuBar();
        JMenu testMenu = new JMenu("Testing");
        JMenuItem showArrays = new JMenuItem("Show component arrays");
        showArrays.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println(days.size() + " dagen:");
                System.out.println(days);
                System.out.println("");
                System.out.println(stages.size() + " podia:");
                System.out.println(stages);
                System.out.println("");
                System.out.println(performances.size() + " optredens:");
                System.out.println(performances);
                System.out.println("");
                System.out.println(artists.size() + " artiesten:");
                System.out.println(artists);
                System.out.println("");
                System.out.println(hosts.size() + " hosts:");
                System.out.println(hosts);
            }
        });
        menuBar.add(testMenu);
        testMenu.add(showArrays);
        setJMenuBar(menuBar);

        setLocation(((displayWidth / 2) - (windowWidth / 2)), ((displayHeight / 2) - (windowHeight / 2)));
        pack();
        setVisible(true);
    }

    private void buildDayPanel()
    {
        dayPanel = new DayPanel(this, timeTable);

        tabbedPane.addTab("Dag", dayPanel);
        tabbedPane.setMnemonicAt(0, MouseEvent.BUTTON1);
    }

    private void buildStagePanel()
    {
        stagePanel = new StagePanel(this, timeTable);

        tabbedPane.addTab("Podium", stagePanel);
        tabbedPane.setMnemonicAt(1, MouseEvent.BUTTON1);
    }

    private void buildPerformancePanel()
    {
        performancePanel = new PerformancePanel(this, timeTable);

        tabbedPane.addTab("Optreden", performancePanel);
        tabbedPane.setMnemonicAt(2, MouseEvent.BUTTON1);
    }

    private void buildArtistPanel()
    {
        artistPanel = new ArtistPanel(this, timeTable);

        tabbedPane.addTab("Artiest", artistPanel);
        tabbedPane.setMnemonicAt(3, MouseEvent.BUTTON1);
    }

    private void buildHostPanel()
    {
        hostPanel = new HostPanel(this, timeTable);

        tabbedPane.addTab("Host", hostPanel);
        tabbedPane.setMnemonicAt(4, MouseEvent.BUTTON1);
    }










    public void updateTables()
    {
        dayPanel.refresh();
        stagePanel.refresh();
        performancePanel.refresh();
        artistPanel.refresh();
        hostPanel.refresh();
        tablePlanner.refresh();
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public ArrayList<Performance> getPerformances() {
        return performances;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public ArrayList<Host> getHosts() {
        return hosts;
    }

    public String[] fillComboBoxes(int targetComponent)
    {
        String[] resultArray;

        switch(targetComponent)
        {
            case 0:
                resultArray = new String[stages.size()];
                for(int i = 0; i < stages.size(); i++)
                    resultArray[i] = stages.get(i).getName();
                return resultArray;
            case 1:
                resultArray = new String[performances.size()];
                for(int i = 0; i < performances.size(); i++)
                    resultArray[i] = performances.get(i).getArtistNames();
                return resultArray;
            case 2:
                resultArray = new String[artists.size()];
                for(int i = 0; i < artists.size(); i++)
                    resultArray[i] = artists.get(i).getName();
                return resultArray;
            case 3:
                resultArray = new String[hosts.size()];
                for(int i = 0; i < hosts.size(); i++)
                    resultArray[i] = hosts.get(i).getName();
                return resultArray;
        }
        return null;
    }
}
