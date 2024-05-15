import javax.swing.*;

public class MainFrame implements Runnable {
    private GraphicsPanel p;

    public MainFrame() {
        JFrame f = new JFrame("Interior Design on JOHN");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(960, 580); // 540 height of image + 40 for window menu bar
        f.setLocationRelativeTo(null); // auto-centers frame in screen

        // create and add panel
        p = new GraphicsPanel();
        f.add(p);

        // display the frame
        f.setVisible(true);

        // start thread, required for animation
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (true) {
            p.repaint();  // we don't ever call "paintComponent" directly, but call this to refresh the panel
        }
    }
}