package RaceData;


import Main.Constants;

/**
 * Creates the spaceShips
 * @author anton byström
 */
public class SpaceShip{

    private int distance;
    private final String name;

    /**
     * constructs the spaceShip
     * @param name of ship
     */
    public SpaceShip(String name) {
        this.name = name;
    }

    /**
     * run along the track.
     */
    public void run() {
        int step = (int)(Math.random() * 100);
        distance += step;
    }

    /**
     * Reset the position of the ship back to the start of the race.
     */
    public void reset() {
        distance = 0;
    }

    /**
     * Gets the distance of the spaceShip along the track.
     * @return distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * checks if the race is completed
     * @return distance
     */
    public double getRaceDistance() {
        return (double)distance / (double) Constants.RACE_LENGTH;
    }

    /**
     * gets the ship name
     * @return ship name
     */
    public String getName() {
        return name;
    }

}
