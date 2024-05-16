import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener {
    private BufferedImage astral;
    private BufferedImage kitchen;
    private BufferedImage bathroom;
    private BufferedImage bedroom;

    public GraphicsPanel() {
        try {
            astral = ImageIO.read(new File("src/astral.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            kitchen = ImageIO.read(new File("src/kitchen.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            bathroom = ImageIO.read(new File("src/bathroom.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            bedroom = ImageIO.read(new File("src/bedroom.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(astral, 0, 0, null);
        //code adapted from https://stackoverflow.com/a/27740330
        Font f = new Font("Courier New", Font.BOLD, 24);
        g.setFont(f);
        g.setColor(Color.WHITE);
        String toPrint = "WELCOME TO INTERIOR DESIGN SIM";
        FontMetrics metrics = g.getFontMetrics(f);
        g.drawString(toPrint, (960 - metrics.stringWidth(toPrint)) / 2, (540 - metrics.getHeight()) / 2 + metrics.getAscent());
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}