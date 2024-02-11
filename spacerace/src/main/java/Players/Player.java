package Players;


import Main.Constants;
import PlayerUI.Notifier;
import PlayerUI.PlayerManagerUI;
import PlayerUI.PlayerNotifier;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * player that extends the playerManagerUI controls bets and values for each player
 * @author anton byström
 */
public class Player extends PlayerManagerUI{
    private final Map<String, Integer> bets = new HashMap<>(); //stores bets places on each ship
    private final int playerNum;

    /**
     * starts a player gui with the player number
     * @param playerNum player number
     */
    public Player(int playerNum) {
        super(playerNum);
        this.playerNum = playerNum;
    }

    /**
     * template method used to add radioButtons
     */
    @Override
    public void addRadioButtons() {
        for (int i = 1; i <= Constants.NUMBER_OF_SHIPS; i++) {
            JRadioButton ship = new JRadioButton("");
            ship.setActionCommand(""+i);
            if (i == 1){
                ship.setSelected(true);
            }
            shipButtons.add(ship);
        }
    }

    /**
     * template method used to add shipLabels
     */
    @Override
    public void addShipLabel() {
        for (int i = 1; i <= Constants.NUMBER_OF_SHIPS; i++) {
            shipLabel.add(new JLabel("Bet on ship " + i + " "));
        }
    }

    /**
     * get the bets placed
     * @return bets
     */
    public Map<String, Integer> getBets() {
        return bets;
    }

    /**
     * clears bets placed after each round
     */
    public void resetBets() {
        bets.clear();
    }

    /**
     * performs betting actions when the bet button is pressed
     * @param e action command
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        long betAmount = (long) super.getAmountField().getValue();
        String ship = super.getShipGroup().getSelection().getActionCommand();
        if (e.getActionCommand().equals("Place bet")) {
            if (super.getAmountField().getValue().hashCode() <= super.getFounds()) { // checks if betAmount <= available founds
                if (bets.containsKey(ship)) {
                    bets.computeIfPresent(ship, (k,v) -> Math.toIntExact(v + betAmount));
                } else {
                    bets.put(ship, (int) betAmount);
                }
                super.removeFounds((int) betAmount);
            }
            else {
                PlayerNotifier playerNotifier = new PlayerNotifier();
                Notifier notifier = playerNotifier.create("notEnoughFounds" + playerNum);
                notifier.notifyUser();
            }
        }
    }
}
