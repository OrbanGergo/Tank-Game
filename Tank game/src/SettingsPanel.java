import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SettingsPanel extends JPanel {
    private BufferedImage img;

    public SettingsPanel(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton musicButton = new JButton("Stop music");

        musicButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5),
                BorderFactory.createLineBorder(Color.GRAY, 20)));
        musicButton.setFont(new Font("Bodoni MT", Font.PLAIN, 30));
        musicButton.setBackground(Color.GRAY);
        musicButton.setFocusPainted(false);
        musicButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton music2Button = new JButton("Start music");

        music2Button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5),
                BorderFactory.createLineBorder(Color.GRAY, 20)));
        music2Button.setFont(new Font("Bodoni MT", Font.PLAIN, 30));
        music2Button.setBackground(Color.GRAY);
        music2Button.setFocusPainted(false);
        music2Button.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("BACK");

        backButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5),
                BorderFactory.createLineBorder(Color.GRAY, 20)));
        backButton.setFont(new Font("Bodoni MT", Font.PLAIN, 30));
        backButton.setBackground(Color.GRAY);
        backButton.setFocusPainted(false);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        musicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.stopMusic();
                SoundPlayer soundPlayer = new SoundPlayer();
                soundPlayer.PlayClick();
            }
        });
        music2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.startMusic();
                SoundPlayer soundPlayer = new SoundPlayer();
                soundPlayer.PlayClick();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer soundPlayer = new SoundPlayer();
                soundPlayer.PlayClick();
                mainFrame.ChangeGameState(0);
            }
        });


        add(Box.createVerticalGlue());
        add(music2Button, BorderLayout.CENTER);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(musicButton, BorderLayout.CENTER);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(backButton, BorderLayout.CENTER);
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
