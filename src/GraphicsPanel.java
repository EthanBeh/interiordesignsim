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
            bathroom = ImageIO.read(new File("src/bathroom.png"));
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
        mainButtons[2][0] = new JButton("empty"); //bathroom's buttons
        mainButtons[2][1] = new JButton("empty");
        mainButtons[2][2] = new JButton("empty");
        mainButtons[2][3] = new JButton("empty");
        mainButtons[3][0] = new JButton("add bed"); //bedroom's buttons
        mainButtons[3][1] = new JButton("add closet");
        mainButtons[3][2] = new JButton("add drawer");
        mainButtons[3][3] = new JButton("add mirror");
        for (JButton[] row : mainButtons) {
            for (JButton b : row) {
                if (b != null) {
                    b.addActionListener(this);
                    add(b);
                    b.setFocusable(false);
                }
            }
        }
        for (int i = 1; i < mainButtons.length; i++) {
            removeButtonRow(i);
        }
        thisLayout = null;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (thisLayout == null) { //scene 0 means welcome screen
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
        } else if (thisLayout instanceof Kitchen) {
            g.drawImage(kitchen, 0, 0, null);
            for (int i = 0; i < thisLayout.getItems().size(); i++) {
                g.drawImage(thisLayout.getItems().get(i).getImage(), thisLayout.getItems().get(i).getX(), thisLayout.getItems().get(i).getY(), null);
            }
            mainButtons[1][0].setLocation(15, 15);
            mainButtons[1][1].setLocation(15, 50);
            mainButtons[1][2].setLocation(15, 85);
            mainButtons[1][3].setLocation(15, 120);
        } else if (thisLayout instanceof Bathroom) {
            g.drawImage(bathroom, 0, -50, null);
            for (int i = 0; i < thisLayout.getItems().size(); i++) {
                g.drawImage(thisLayout.getItems().get(i).getImage(), thisLayout.getItems().get(i).getX(), thisLayout.getItems().get(i).getY(), null);
            }
            mainButtons[2][0].setLocation(15, 15);
            mainButtons[2][1].setLocation(15, 50);
            mainButtons[2][2].setLocation(15, 85);
            mainButtons[2][3].setLocation(15, 120);
        } else if (thisLayout instanceof Bedroom) {
            g.drawImage(bedroom, 0, 0, null);
            for (int i = 0; i < thisLayout.getItems().size(); i++) {
                g.drawImage(thisLayout.getItems().get(i).getImage(), thisLayout.getItems().get(i).getX(), thisLayout.getItems().get(i).getY(), null);
            }
            mainButtons[3][0].setLocation(15, 15);
            mainButtons[3][1].setLocation(15, 50);
            mainButtons[3][2].setLocation(15, 85);
            mainButtons[3][3].setLocation(15, 120);
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

    private void addButtonRow(int row) {
        for (JButton b : mainButtons[row]) {
            if (b != null) {
                b.setVisible(true);
            }
        }
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
            thisLayout = new Kitchen();
            removeButtonRow(0);
            addButtonRow(1);
            requestFocusInWindow();
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[0][1]) {
            thisLayout = new Bathroom();
            removeButtonRow(0);
            addButtonRow(2);
            requestFocusInWindow();
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[0][2]) {
            thisLayout = new Bedroom();
            removeButtonRow(0);
            addButtonRow(3);
            requestFocusInWindow();
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[1][0]) {
            thisLayout.addItem("src/Images/kitchencabinet.png", 225, 225, Item.Type.CABINET);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[1][1]) {
            thisLayout.addItem("src/Images/kitchensink.png", 309, 163, Item.Type.KITCHENSINK);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[1][2]) {
            thisLayout.addItem("src/Images/kitchenstove.png", 220, 229, Item.Type.STOVE);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[1][3]) {
            thisLayout.addItem("src/Images/kitchenfridge.png", 225, 225, Item.Type.FRIDGE);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[3][0]) {
            thisLayout.addItem("src/Images/bedroombed.png", 250, 250, Item.Type.BED);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[3][1]) {
            thisLayout.addItem("src/Images/bedroomcloset.png", 250, 250, Item.Type.CLOSET);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[3][2]) {
            thisLayout.addItem("src/Images/bedroomdrawer.png", 200, 200, Item.Type.DRAWER);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[3][3]) {
            thisLayout.addItem("src/Images/bedroommirror.png", 225, 225, Item.Type.BEDROOMMIRROR);
        }
    }
}