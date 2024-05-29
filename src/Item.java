import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Item {
    private BufferedImage itemImage;
    private int xCoord;
    private int yCoord;
    private int imgWidth;
    private int imgHeight;
    private Type t;

    public Item(String path, int imgWidth, int imgHeight, Type type) {
        try {
            itemImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        xCoord = 480 - imgWidth / 2;
        yCoord = 290 - imgHeight / 2;
        t = type;
    }

    public BufferedImage getImage() {
        return itemImage;
    }

    public void moveUp() {
        yCoord -= 25;
        if (yCoord < 0) {
            yCoord = 0;
        }
    }

    public void moveLeft() {
        xCoord -= 25;
        if (xCoord < 0) {
            xCoord = 0;
        }
    }

    public void moveDown() {
        yCoord += 25;
        if (yCoord > 580 - imgHeight / 2) {
            yCoord = 580 - imgHeight / 2;
        }
    }

    public void moveRight() {
        xCoord += 25;
        if (xCoord > 960 - imgWidth / 2) {
            xCoord = 960 - imgWidth / 2;
        }
    }

    public int getX() {
        return xCoord;
    }

    public int getY() {
        return yCoord;
    }

    public Rectangle getRect() {
        return new Rectangle(xCoord, yCoord, imgWidth, imgHeight);
    }

    public enum Type {
        CABINET, KITCHENSINK, STOVE, FRIDGE,
        BED, BEDROOMMIRROR, DRAWER, CLOSET,
        TOILET, SHOWER, BATHROOMSINK, BATHROOMMIRROR //add mirror and sink too
    }
}