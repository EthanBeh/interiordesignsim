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
    private JButton[] kitchenButtons;
    private int scene;

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
        kitch = new JButton("design kitchen");
        kitch.addActionListener(this);
        add(kitch);
        bath = new JButton("design bathroom");
        add(bath);
        bath.addActionListener(this);
        bed = new JButton("design bedroom");
        add(bed);
        bed.addActionListener(this);
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
            kitch.setLocation(480 - 150, 50 + stringy);
            bath.setLocation(480, 50 + stringy);
            bed.setLocation(480 + 150, 50 + stringy);
        } else if (scene == 1) { //scene 1 means kitchen

        } else if (scene == 2) { //scene 2 means bathroom

        } else if (scene == 3) { //scene 3 means bedroom

        }
    }

/*
        kitchenButtons = new JButton[3];
        kitchenButtons[0] = new JButton("AAH");
        kitchenButtons[1] = new JButton("BBH");
        kitchenButtons[2] = new JButton("CCH");
        for (JButton b : kitchenButtons) {
            add(b);
            b.addActionListener(this);
        }
*/

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

    private void removeStartingButtons() {
        kitch.setVisible(false);
        bed.setVisible(false);
        bath.setVisible(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton && e.getSource() == kitch) {
            scene = 1;
            removeStartingButtons();
            requestFocusInWindow();
        } else if (e.getSource() instanceof JButton && e.getSource() == bath) {
            scene = 2;
            removeStartingButtons();
            requestFocusInWindow();
        } else if (e.getSource() instanceof JButton && e.getSource() == bed) {
            scene = 3;
            removeStartingButtons();
            requestFocusInWindow();
        }
    }
}