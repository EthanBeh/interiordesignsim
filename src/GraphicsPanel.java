import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener, ActionListener {
    private BufferedImage astral;
    private BufferedImage kitchen;
    private BufferedImage bathroom;
    private BufferedImage bedroom;
    private JButton kitch;
    private JButton bath;
    private JButton bed;
    private JButton[][] mainButtons;
    private int scene;
    private Layout thisLayout;

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
        scene = 0;
        mainButtons = new JButton[4][4];
        mainButtons[0][0] = new JButton("design kitchen"); //row means what screen it's on, 0,1,2,3, column is which button it is
        mainButtons[0][1] = new JButton("design bathroom");
        mainButtons[0][2] = new JButton("design bedroom");
        mainButtons[1][0] = new JButton("add cabinet"); //kitchen's buttons
        mainButtons[1][1] = new JButton("add sink");
        mainButtons[1][2] = new JButton("add stove");
        mainButtons[1][3] = new JButton("add fridge");
        mainButtons[2][0] = new JButton("DDH"); //kitchen's buttons
        mainButtons[2][1] = new JButton("EEH");
        mainButtons[2][2] = new JButton("FFH");
        mainButtons[3][0] = new JButton("GGH"); //kitchen's buttons
        mainButtons[3][1] = new JButton("IIH");
        mainButtons[3][2] = new JButton("JJH");
        for (JButton[] row : mainButtons) {
            for (JButton b : row) {
                if (b != null) {
                    b.addActionListener(this);
                }
            }
        }
        for (JButton b : mainButtons[0]) {
            if (b != null) {
                add(b);
            }
        }
        thisLayout = null;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (scene == 0) { //scene 0 means welcome screen
            g.drawImage(astral, 0, 0, null);
            //code adapted from https://stackoverflow.com/a/27740330
            Font f = new Font("Courier New", Font.BOLD, 24);
            g.setFont(f);
            g.setColor(Color.WHITE);
            String toPrint = "WELCOME TO INTERIOR DESIGN SIM";
            FontMetrics metrics = g.getFontMetrics(f);
            int stringy = (540 - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(toPrint, (960 - metrics.stringWidth(toPrint)) / 2, (540 - metrics.getHeight()) / 2 + metrics.getAscent());
            //end of adapted code
            mainButtons[0][0].setLocation(480 - 150, 50 + stringy);
            mainButtons[0][1].setLocation(480, 50 + stringy);
            mainButtons[0][2].setLocation(480 + 150, 50 + stringy);
        } else if (scene == 1) { //scene 1 means kitchen
            thisLayout = new Kitchen();
            g.drawImage(kitchen, 0, 0, null);
        } else if (scene == 2) { //scene 2 means bathroom
            thisLayout = new Bathroom();
            g.drawImage(bathroom, 0, 0, null);
        } else if (scene == 3) { //scene 3 means bedroom
            thisLayout = new Bedroom();
            g.drawImage(bedroom, 0, 0, null);
        }
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

    private void removeButtonRow(int row) {
        for (JButton b : mainButtons[row]) {
            if (b != null) {
                b.setVisible(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton && e.getSource() == mainButtons[0][0]) {
            scene = 1;
            removeButtonRow(0);
            requestFocusInWindow();
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[0][1]) {
            scene = 2;
            removeButtonRow(0);
            requestFocusInWindow();
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[0][2]) {
            scene = 3;
            removeButtonRow(0);
            requestFocusInWindow();
        }
    }
}