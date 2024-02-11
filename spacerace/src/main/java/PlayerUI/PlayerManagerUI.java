package PlayerUI;

import BetPool.BetPoolManager;
import Main.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * responsible for creating the GUI of the players
 * @author anton byström
 */
public abstract class PlayerManagerUI extends JFrame implements ActionListener {
    // creates buttons and interfaces displayed for the players
    private final BetPoolManager betPool = new BetPoolManager();
    private int founds = Constants.STARTING_FOUNDS;
    private final JButton betButton = new JButton("Place bet");
    private final ButtonGroup shipGroup = new ButtonGroup();
    private final JFormattedTextField amountField = new JFormattedTextField(NumberFormat.getNumberInstance());
    private final JLabel cash = new JLabel("Available founds: " + founds);

    protected ArrayList<JRadioButton> shipButtons = new ArrayList<>();
    protected ArrayList<JLabel> shipLabel = new ArrayList<>();

    /**
     * Used by extended player class to format the gui
     * @param playerNum players number
     */
    protected PlayerManagerUI(int playerNum) {
        this.setLayout(new BorderLayout());

        // Confirm betting button
        betButton.addActionListener(this);

        // Betting panel
        JPanel bet = new JPanel(new BorderLayout());
        bet.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel amountLabel = new JLabel("Amount to bet  ");

        // Bet amount field
        amountField.setValue((long)100);
        amountField.setColumns(10);
        amountLabel.setLabelFor(amountField);

        // Ship bet selector
        addRadioButtons();

        // Ship bet names
        addShipLabel();

        // adds comps to panels
        JPanel labelPanel = new JPanel(new GridLayout(0,1));
        labelPanel.add(amountLabel);
        for (JLabel ship : shipLabel) {
            labelPanel.add(ship);
        }

        JPanel fieldPanel = new JPanel(new GridLayout(0,1));
        fieldPanel.add(amountField);
        for (JRadioButton shipButton : shipButtons) {
            shipGroup.add(shipButton);
            fieldPanel.add(shipButton);
        }

        bet.add(cash, BorderLayout.PAGE_START);
        bet.add(labelPanel,BorderLayout.CENTER);
        bet.add(fieldPanel, BorderLayout.LINE_END);
        bet.add(betButton, BorderLayout.PAGE_END);

        this.add(bet);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Player " + playerNum);
        this.setSize(300,500);
        this.pack();
        this.setVisible(true);
    }

    /**
     * template method used to add radioButtons
     */
    public abstract void addRadioButtons();

    /**
     * template method used to add shipLabels
     */
    public abstract void addShipLabel();

    /**
     * updates the textarea for amount of available founds to use for betting
     */
    private void updateFounds() {
        cash.setText("Available founds: " + founds);
    }

    /**
     * gets the amount of founds the player has
     * @return amount of founds
     */
    public int getFounds() {
        return this.founds;
    }

    /**
     * adds founds to the player if they placed a winning bet
     * @param amount they bet on the ship
     */
    public void addFounds(int amount) {
        betPool.createConsumer(amount);
        this.founds += amount;
        updateFounds();
    }

    /**
     * remove founds from the player when they bet on a ship
     * @param amount they bet on the ship
     */
    public void removeFounds(int amount) {
        betPool.createProducer(amount);
        this.founds -= amount;
        updateFounds();
    }

    /**
     * used to get the selected button from the buttongroup
     * @return buttonGroup
     */
    protected ButtonGroup getShipGroup() {
        return shipGroup;
    }

    /**
     * get the bet amount
     * @return bet amount
     */
    protected JFormattedTextField getAmountField() {
        return amountField;
    }

    /**
     * set so the player cant bet if the game has started
     * @param toggle true or false depending on if they can bet or not
     */
    public void toggleButton(boolean toggle) {
        this.betButton.setEnabled(toggle);
    }
}
