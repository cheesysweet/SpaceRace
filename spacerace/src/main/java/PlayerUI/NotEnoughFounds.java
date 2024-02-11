package PlayerUI;

import javax.swing.*;
import java.awt.*;

/**
 * concrete implementation that creates a GUI for when a player is out of founds
 * @author anton byström
 */
public class NotEnoughFounds extends JFrame implements Notifier {
    private final String playerNum;

    /**
     * sets the players number
     * @param player player name
     */
    public NotEnoughFounds(String player) {
        this.playerNum = player.substring(player.length()-1);
    }

    /**
     * displays a gui for when the player is out of founds.
     */
    @Override
    public void notifyUser() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JLabel label = new JLabel("You are out of founds");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button = new JButton("Okay");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> {
            this.dispose();
        });

        this.add(label);
        this.add(button);
        this.setTitle("Player " + playerNum);
        this.pack();
        this.setVisible(true);
    }
}
