import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WinnerPanel extends JPanel {
    private BufferedImage img;

    public WinnerPanel(String winner, MainFrame mainFrame) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel(winner + " WON");
        label.setFont(new Font("Arial", Font.BOLD, 40));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setForeground(Color.BLACK);
        add(label, BorderLayout.CENTER);

        try {
            img = ImageIO.read(new File("images/winnerbg.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.ChangeGameState(0);
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

        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(img, 0, 0, 1400, 800, null);
    }
}
