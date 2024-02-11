package Main;

import RaceData.Race;
import RaceData.SpaceShip;
import RaceUI.RaceUI;

/**
 * Runs the game
 * @author anton byström
 */
public class SpaceRace implements Runnable{
    private static boolean newGame = true;
    private static boolean runGame = false;
    private static Race race;

    /**
     * Starts a new race
     */
    public static void main(final String[] args) {
        race = new Race(); // ship information
        SpaceRace a = new SpaceRace(); // starts a new spaceRace in a separate thread
        Thread raceStart = new Thread(a);
        raceStart.start();
    }

    /**
     * changes game state depending on game should be stopped or keep running
     * @param game true or false
     */
    public static void setRunGame(boolean game) {
        runGame = game;
    }

    /**
     * pauses or resumes all race ui if the game is stopped
     * @param game true or false
     */
    public static void toggleGame(boolean game) {
        newGame = game;
    }

    /**
     * runes the game and updates the ui with race information
     */
    @Override
    public void run() {
        RaceUI ui = new RaceUI(race);
        race.setListener(ui);

        while (newGame) {
            while (!runGame) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            ui.notifyStatus("Race in progress");
            SpaceShip winner = race.race();
            ui.notifyStatus("Race winner: Ship " + (Integer.parseInt(winner.getName()) + 1));
            race.reset();
            runGame = false;
        }
    }
}
