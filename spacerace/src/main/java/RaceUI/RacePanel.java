package RaceUI;

import Main.Constants;
import RaceData.Race;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Creates the racePanel for the ships
 * @author anton byström
 */
public class RacePanel extends JPanel {
    private final ArrayList<BufferedImage> imageList = new ArrayList<>();

    private final Race race;

    /**
     * loads images for each ship
     * @param race data
     */
    public RacePanel(Race race) {
        super();
        this.race = race;
        try {
            for (int i=1; i<=Constants.NUMBER_OF_SHIPS; i++) {
                imageList.add(ImageIO.read(new File("spaceships/SpaceShip" + i + ".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * paints the images of the ships to the raceFrame
     * @param g graphics
     */
    public void paint(Graphics g) {
        int laneHeight = this.getHeight() / Constants.NUMBER_OF_SHIPS;
        int laneWidth = this.getWidth() - imageList.get(1).getWidth();


        int y=0;

        for(int i=0; i<Constants.NUMBER_OF_SHIPS; i++) {
            int x = (int)(race.getShips().get(i).getRaceDistance() * laneWidth);
            g.drawImage(imageList.get(i), x, y, imageList.get(i).getWidth(), laneHeight, null);

            y+= laneHeight;
        }
    }
}
