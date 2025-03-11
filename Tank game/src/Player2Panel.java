import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player2Panel extends JPanel {
    private BufferedImage img1;
    private boolean myTurn;
    private JLabel label;
    private JPanel heartPanel;
    private JLabel heart1;
    private JLabel heart2;
    private JLabel heart3;
    private int hp;
    private int time;
    private JLabel timeLabel;

    public Player2Panel() {
        setBackground(Color.WHITE);
        setLayout(new GridLayout(3, 1));
        try {
            img1 = ImageIO.read(new File("images/heartt.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        label = new JLabel("PLAYER 1");
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setHorizontalAlignment(JLabel.CENTER);

        heart1 = new JLabel(new ImageIcon(img1));
        heart2 = new JLabel(new ImageIcon(img1));
        heart3 = new JLabel(new ImageIcon(img1));
        heart1.setSize(new Dimension(100, 100));
        heart2.setSize(new Dimension(100, 100));
        heart3.setSize(new Dimension(100, 100));

        heartPanel = new JPanel();
        heartPanel.setBackground(Color.WHITE);
        heartPanel.setLayout(new GridLayout(1, 3));
        heartPanel.add(heart1);
        heartPanel.add(heart2);
        heartPanel.add(heart3);

        hp = 3;

        timeLabel = new JLabel(Integer.toString(time));
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);

        add(heartPanel);
        add(label);
        add(timeLabel);
        myTurn = false;
        time = 0;
    }

    void ChangeMyTurn() {
        myTurn = !myTurn;
    }

    boolean getMyTurn() {
        return myTurn;
    }

    public void LowerHP() {
        hp--;
        if (hp == 2) {
            heartPanel.remove(heart3);
        } else if (hp == 1) {
            heartPanel.remove(heart2);
        } else {
            heartPanel.remove(heart1);
        }
        repaint();
    }

    public void setTime(int time) {
        this.time = time;
        timeLabel.setText(Integer.toString(time));
        repaint();
    }
}
