import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {
    private BufferedImage img;

    public MenuPanel(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playButton = new JButton("Start Game");

        playButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5),
                BorderFactory.createLineBorder(Color.GRAY, 20)));
        playButton.setFont(new Font("Bodoni MT", Font.PLAIN, 30));
        playButton.setBackground(Color.GRAY);
        playButton.setFocusPainted(false);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton settingsButton = new JButton("Settings");

        settingsButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5),
                BorderFactory.createLineBorder(Color.GRAY, 20)));
        settingsButton.setFont(new Font("Bodoni MT", Font.PLAIN, 30));
        settingsButton.setBackground(Color.GRAY);
        settingsButton.setFocusPainted(false);
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer soundPlayer = new SoundPlayer();
                soundPlayer.PlayClick();
                mainFrame.ChangeGameState(1);
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer soundPlayer = new SoundPlayer();
                soundPlayer.PlayClick();
                mainFrame.ChangeGameState(4);
            }
        });


        add(Box.createVerticalGlue());
        add(playButton, BorderLayout.CENTER);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(settingsButton, BorderLayout.CENTER);
        add(Box.createVerticalGlue());

        try {
            img = ImageIO.read(new File("images/background2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(img, 0, 0, 1400, 800, null);
    }
}
