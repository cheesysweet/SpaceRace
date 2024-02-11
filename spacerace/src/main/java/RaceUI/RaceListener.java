package RaceUI;

/**
 * RaceListener used to update the graphical ui and game status
 * @author anton byström
 */
public interface RaceListener {
    /**
     * used to visualize the ships moving along the racetrack.
     */
    void notifyRaceProgress();

    /**
     * updates the status of the race
     * @param status string status of race
     */
    void notifyStatus(String status);
}
