package RaceData;

import Main.Constants;
import RaceUI.RaceListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Race stats of the game
 * @author anton byström
 */
public class Race {
    private final List<SpaceShip> ships = new ArrayList<>();

    private RaceListener listener;

    /**
     * Construct the race object and add the number of ships
     */
    public Race() {
        int numberOfShips = Constants.NUMBER_OF_SHIPS;
        for(int i = 0; i< numberOfShips; i++) {
            ships.add(new SpaceShip(String.valueOf(i)));
        }
    }

    /**
     * Reset the race back to the start.
     */
    public void reset() {
        for(SpaceShip ship : ships) {
            ship.reset();
        }
    }

    /**
     * Run the race and return the winner.
     * @return winner of the race.
     */
    public SpaceShip race() {

        SpaceShip winner = getWinner();
        while(winner == null) {

            try {
                int DELAY = 25;
                Thread.sleep(DELAY);
            } catch(InterruptedException e) {}



            for(SpaceShip ship : ships) {
                ship.run();
            }

            if(listener != null) {
                listener.notifyRaceProgress();
            }

            winner = getWinner();
        }
        return winner;

    }

    /**
     * gets the winner of the race
     * @return ship number
     */
    public SpaceShip getWinner() {
        for(SpaceShip ship : ships) {
            int raceLength = Constants.RACE_LENGTH;
            if(ship.getDistance() > raceLength) {
                return ship;
            }
        }
        return null;
    }

    /**
     * list of ships
     * @return list of ships
     */
    public List<SpaceShip> getShips() {
        return ships;
    }

    /**
     * adds the RaceListener
     * @param listener RaceListener
     */
    public void setListener(RaceListener listener) {
        this.listener = listener;
    }

}
