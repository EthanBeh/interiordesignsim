import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Door {
    private static BufferedImage closed;
    private static BufferedImage open;
    private boolean isClosed;

    public Door() {
        try {
            closed = ImageIO.read(new File("src/closeddoor.jpg"));
            open = ImageIO.read(new File("src/opendoor.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        isClosed = false;
    }

    public void openOrClose() {
        isClosed = !isClosed;
    }

    public BufferedImage getImage() {
        if (isClosed) {
            return closed;
        } return open;
    }
}