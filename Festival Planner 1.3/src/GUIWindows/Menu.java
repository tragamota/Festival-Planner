package GUIWindows;

import ComponentManager.ComponentManager;

import javax.swing.*;
import Data.*;

import java.io.*;

/**
 * Created by Ian van de poll on 6-2-2017.
 */
public class Menu extends JMenuBar {

    JFrame gui;
    TimeTable timeTable;
    TablePlanner planner;

    public Menu(JFrame gui, TimeTable timeTable, TablePlanner tablePlanner) {
        super();
        this.gui = gui;
        this.timeTable = timeTable;
        this.planner = tablePlanner;

        //bestand menu
        JMenu fileMenu = new JMenu("Bestand");

        JMenuItem newFile = new JMenuItem("Nieuw");
        JMenuItem openFile = new JMenuItem("Openen");
        JMenuItem saveFile = new JMenuItem("Opslaan");
        JMenuItem exitProgram = new JMenuItem("Sluiten");

        add(fileMenu);
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(exitProgram);

        newFile.addActionListener(e -> newFileEvent());
        openFile.addActionListener(e -> openFileEvent());
        saveFile.addActionListener(e -> saveFileEvent());
        exitProgram.addActionListener(e -> exitFileEvent());

        //component menu
        JMenu componentMenu = new JMenu("Componenten");
        JMenuItem manageComponents = new JMenuItem("Beheer");

        add(componentMenu);
        componentMenu.add(manageComponents);

        manageComponents.addActionListener(e -> new ComponentManager(timeTable, tablePlanner));


    }

    public void newFileEvent() {
        timeTable.setProjectname("Nieuw Data TimeTable");
        timeTable.getUnusedArtists().clear();
        timeTable.getUnusedHosts().clear();
        timeTable.getDays().clear();
        timeTable.getDays().add(new Day(timeTable));
        planner.refresh();
    }

    public void openFileEvent() {
        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
            return;
        }

        try {
            FileInputStream file = new FileInputStream(fileChooser.getSelectedFile());
            ObjectInputStream input = new ObjectInputStream(file);
            TimeTable tempTable = (TimeTable) input.readObject();
            input.close();

            timeTable.setDays(tempTable.getDays());
            timeTable.setProjectname(tempTable.getProjectName());
            timeTable.setUnusedHosts(tempTable.getUnusedHosts());
            timeTable.setUnusedArtists(tempTable.getUnusedArtists());
            planner.reloadTable();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveFileEvent()  {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(timeTable.getProjectName() + ".B011"));

        int input = fileChooser.showSaveDialog(null);
        if(input == JFileChooser.CANCEL_OPTION) {
            return;
        }
        File file = new File(fileChooser.getSelectedFile().getPath());
        if(file.exists()) {
            //JDialog.setDefaultLookAndFeelDecorated(true);
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to override existing file?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.NO_OPTION) {
                System.out.println("No button clicked");
                return;
            } else if (response == JOptionPane.YES_OPTION) {
                System.out.println("Yes button clicked");
            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("JOptionPane closed");
                return;
            }
        }

        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream output = new ObjectOutputStream(fileOut);
            output.writeObject(timeTable);
            output.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitFileEvent() {
        System.exit(0);
    }
}
