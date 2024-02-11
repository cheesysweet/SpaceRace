package Players;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * used to create the players and edit their information
 * @author anton byström
 */
public class PlayerInterface{

    private final Deque<Player> players = new ArrayDeque<>();

    /**
     * creates a number of players
     * @param numOfPlayers number of players
     */
    public void playerInterface(int numOfPlayers) {
        for (int i=1; i <= numOfPlayers; i++) {
            players.addFirst(new Player(i));
        }
    }

    /**
     * closes the player interfaces
     */
    public void closeInterfaces() {
        players.forEach(Window::dispose);
    }

    /**
     * changes if the players should be able to bet or not
     * @param toggle true if they are allowed to bet.
     *               False if they are not allowed to bet.
     */
    public void toggleButton(boolean toggle) {
        players.forEach(player -> player.toggleButton(toggle));
    }

    /**
     * calculates if the players placed a winning bet, and adds the winning to the players founds.
     * @param winner winning ship
     */
    public void calculateWinner(String winner) {
        players.stream().filter(player -> player.getBets().containsKey(winner.substring(winner.length() - 1)))
                .forEach(player -> player.addFounds(
                        player.getBets().get(winner.substring(winner.length()-1)) * 5));
    }

    /**
     * clear all bets placed in earlier round
     */
    public void clearBets() {
        players.forEach(Player::resetBets);
    }
}
