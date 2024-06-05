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

    public Item(String path, int imgWidth, int imgHeight) {
        try {
            itemImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        xCoord = 480 - imgWidth / 2;
        yCoord = 290 - imgHeight / 2;
    }

    public BufferedImage getImage() {
        return itemImage;
    }

    public void moveUp() {
        yCoord -= 1;
        if (yCoord < 0) {
            yCoord = 0;
        }
    }

    public void moveLeft() {
        xCoord -= 1;
        if (xCoord < 0) {
            xCoord = 0;
        }
    }

    public void moveDown() {
        yCoord += 1;
        if (yCoord > 580 - imgHeight / 2) {
            yCoord = 580 - imgHeight / 2;
        }
    }

    public void moveRight() {
        xCoord += 1;
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

}