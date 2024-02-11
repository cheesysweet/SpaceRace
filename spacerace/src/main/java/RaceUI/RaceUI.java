package RaceUI;

import RaceData.Race;

import javax.swing.*;
import java.awt.*;

/**
 * Creates the GUI for the racePanel
 * @author anton byström
 */
public class RaceUI extends JFrame implements RaceListener {
    private final RaceInfo InfoPanel;
    private final RacePanel RacePanel;


    /**
     * creates the frame for the racePanel
     * @param race information
     */
    public RaceUI(Race race) {
        this.setTitle("Space Race");
        this.setLayout(new BorderLayout());

        RacePanel = new RacePanel(race);
        InfoPanel = new RaceInfo();

        this.add(RacePanel, BorderLayout.CENTER);
        this.add(InfoPanel, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500,800);
        this.setVisible(true);
    }

    /**
     * repaints the racePanel
     */
    public void updateGraphics() {
        RacePanel.repaint();
        repaint();
    }

    /**
     * used to visualize the ships moving along the racetrack.
     */
    @Override
    public void notifyRaceProgress() {
        updateGraphics();
    }

    /**
     * updates the status of the race
     * @param status string status of race
     */
    @Override
    public void notifyStatus(String status) {
        InfoPanel.updateStatus(status);
        repaint();
    }

}
