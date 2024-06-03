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
    private JButton[][] mainButtons;
    private int scene;
    private Layout thisLayout;
    private Point containerLocation;
    private boolean[] keys; //0 = W, 1 = A, 2 = S, 3 = D

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
        mainButtons[2][0] = new JButton("add mirror"); //bathroom's buttons
        mainButtons[2][1] = new JButton("add shower");
        mainButtons[2][2] = new JButton("add sink");
        mainButtons[2][3] = new JButton("add toilet");
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
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        containerLocation = null;
        keys = new boolean[4];
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int buttonRow = 0;
        //containerLocation = g.getLocationOnScreen();
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
            buttonRow = 1;
        } else if (thisLayout instanceof Bathroom) {
            g.drawImage(bathroom, 0, -50, null);
            buttonRow = 2;
        } else if (thisLayout instanceof Bedroom) {
            g.drawImage(bedroom, 0, 0, null);
            buttonRow = 3;
        }
        if (thisLayout != null) {
            for (int i = 0; i < thisLayout.getItems().size(); i++) {
                g.drawImage(thisLayout.getItems().get(i).getImage(), thisLayout.getItems().get(i).getX(), thisLayout.getItems().get(i).getY(), null);
            }
            mainButtons[buttonRow][0].setLocation(15, 15);
            mainButtons[buttonRow][1].setLocation(15, 50);
            mainButtons[buttonRow][2].setLocation(15, 85);
            mainButtons[buttonRow][3].setLocation(15, 120);
            if (thisLayout.getItems().size() != 0) {
                Item last = thisLayout.getItems().get(thisLayout.getItems().size() - 1);
                if (keys[0]) {
                    System.out.println("kms");
                    last.moveUp();
                }
                if (keys[1]) {
                    System.out.println("kys");
                    last.moveLeft();
                }
                if (keys[2]) {
                    System.out.println("khs");
                    last.moveDown();
                }
                if (keys[3]) {
                    System.out.println("kts");
                    last.moveRight();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 87: //w
                keys[0] = true;
                break;
            case 65: //a
                keys[1] = true;
                break;
            case 83: //s
                keys[2] = true;
                break;
            case 68: //d
                keys[3] = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 87: //w
                keys[0] = false;
                break;
            case 65: //a
                keys[1] = false;
                break;
            case 83: //s
                keys[2] = false;
                break;
            case 68: //d
                keys[3] = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && thisLayout != null) {
            thisLayout.identifyItem(MouseInfo.getPointerInfo().getLocation());
            //so what i gotta do is let the code work no mattter what position the cursor's at on the screen
        }
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
            thisLayout.addItem("src/Images/kitchencabinet.png", 169, 173, Item.Type.CABINET);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[1][1]) {
            thisLayout.addItem("src/Images/kitchensink.png", 169, 139, Item.Type.KITCHENSINK);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[1][2]) {
            thisLayout.addItem("src/Images/kitchenstove.png", 175, 229, Item.Type.STOVE);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[1][3]) {
            thisLayout.addItem("src/Images/kitchenfridge.png", 117, 222, Item.Type.FRIDGE);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[3][0]) {
            thisLayout.addItem("src/Images/bedroombed.png", 205, 125, Item.Type.BED);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[3][1]) {
            thisLayout.addItem("src/Images/bedroomcloset.png", 104, 238, Item.Type.CLOSET);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[3][2]) {
            thisLayout.addItem("src/Images/bedroomdrawer.png", 143, 136, Item.Type.DRAWER);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[3][3]) {
            thisLayout.addItem("src/Images/bedroommirror.png", 99, 215, Item.Type.BEDROOMMIRROR);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[2][0]) {
            thisLayout.addItem("src/Images/bathroommirror.png", 200, 149, Item.Type.BATHROOMMIRROR);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[2][1]) {
            thisLayout.addItem("src/Images/bathroomshower.png", 225, 234, Item.Type.SHOWER);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[2][2]) {
            thisLayout.addItem("src/Images/bathroomsink.png", 107, 166, Item.Type.BATHROOMSINK);
        } else if (e.getSource() instanceof JButton && e.getSource() == mainButtons[2][3]) {
            thisLayout.addItem("src/Images/bathroomtoilet.png", 91, 167, Item.Type.TOILET);
        }
    }
}