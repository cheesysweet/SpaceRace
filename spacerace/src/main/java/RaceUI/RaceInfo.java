package RaceUI;

import BetPool.ObjectPool;
import Main.Constants;
import Main.SpaceRace;
import Players.PlayerInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * creates the buttons and information on the raceFrame
 * @author anton byström
 */
public class RaceInfo extends JPanel implements ActionListener {

    private final JLabel statusLabel = new JLabel("Select number of Players and press Start");
    private final JComboBox players;
    private final JButton startRunButton = new JButton("Start");
    private final JButton newRunButton = new JButton("New Game");
    private final JButton stopRunButton = new JButton("stop Game");
    PlayerInterface playerInterface = new PlayerInterface();
    ObjectPool objectPool;

    /**
     * creates buttons a label for information and comboBox to select players
     * then adds them to this JPanel
     */
    public RaceInfo() {
        this.setLayout(new FlowLayout());

        // Creates a JComboBox for each player
        String[] playerArray = new String[Constants.NUMBER_OF_PLAYERS];
        for (int i = 0; i< Constants.NUMBER_OF_PLAYERS; i++) {
            if (i == 0) {
                playerArray[i] = ((i+1) + " Player");
            } else {
                playerArray[i] = ((i+1) + " Players");
            }
        }
        players = new JComboBox(playerArray);

        // formats buttons
        players.setSelectedIndex(0);
        players.addActionListener(this);
        startRunButton.addActionListener(this);
        startRunButton.setVisible(false);
        newRunButton.addActionListener(this);
        stopRunButton.addActionListener(this);
        stopRunButton.setVisible(false);

        this.add(statusLabel);
        this.add(players);
        this.add(startRunButton);
        this.add(newRunButton);
        this.add(stopRunButton);

    }

    /**
     * updates the statusLabel
     * @param status new status information
     */
    public void updateStatus(String status) {
        statusLabel.setText(status);
        if (status.contains("winner")) {
            playerInterface.toggleButton(true);
            playerInterface.calculateWinner(status);
            playerInterface.clearBets();
            objectPool.executeConsumers();
        }
    }

    /**
     * Used to select how many players are going to play.
     * @param players player amount
     */
    protected void updateLabel(String players) {
        int numOfPlayers = Character.getNumericValue(players.charAt(0));
        playerInterface.playerInterface(numOfPlayers);
    }

    /**
     * Select witch buttons are to be showed depending on what button are pressed.
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // If the player presses the new game button
        if (e.getActionCommand().equals("New Game")) {
            SpaceRace.toggleGame(true); // makes the game runnable
            startRunButton.setVisible(true);
            newRunButton.setVisible(false);
            stopRunButton.setVisible(true);
            players.setVisible(false);
            updateLabel((String) Objects.requireNonNull(players.getSelectedItem())); // number of players to play
            objectPool = new ObjectPool(); // creates a new object pool
            objectPool.executeProducers();
        }
        // If the player presses the start button
        if (e.getActionCommand().equals("Start")) {
            SpaceRace.setRunGame(true); // starts a game
            playerInterface.toggleButton(false); // makes so the players cant bet during the race
        }
        // If the players presses the stop game button
        if (e.getActionCommand().equals("stop Game")) {
            newRunButton.setVisible(true);
            players.setVisible(true);
            startRunButton.setVisible(false);
            stopRunButton.setVisible(false);
            SpaceRace.toggleGame(false); // stops the game
            playerInterface.closeInterfaces(); // closes the players interfaces
            objectPool.killExecutor();
        }
    }
}
