import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainFrame extends JFrame {

    private int gameState;
    private CardLayout layout;
    private JPanel mainPanel;
    private JPanel winnerPanel1;
    private JPanel winnerPanel2;
    private JPanel gamePanel;
    SoundPlayer soundPlayer = new SoundPlayer();
    private Tank tank1;
    private Tank tank2;
    private int[] map;

    public MainFrame() {
        gameState = 0;
        layout = new CardLayout();
        mainPanel = new JPanel();
        winnerPanel1 = new WinnerPanel("PLAYER 1", this);
        winnerPanel2 = new WinnerPanel("PLAYER 2", this);
        mainPanel.setLayout(layout);
        add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        SettingsPanel settingsPanel = new SettingsPanel(this);


        MenuPanel menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(this);
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(gamePanel, "game");
        mainPanel.add(winnerPanel1, "winner1");
        mainPanel.add(winnerPanel2, "winner2");
        mainPanel.add(settingsPanel, "settings");


        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");

        saveItem.addActionListener(e -> saveGame());
        loadItem.addActionListener(e -> loadGame());

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        soundPlayer.PlayBGMusic();
        setVisible(true);
    }

    public void ChangeGameState(int gameState) {
        this.gameState = gameState;
        switch (gameState) {
            case 0:
                layout.show(mainPanel, "menu");


                break;
            case 1:
                layout.show(mainPanel, "game");
                break;
            case 2:

                mainPanel.remove(gamePanel);
                gamePanel = new GamePanel(this);
                mainPanel.add(gamePanel, "game");
                layout.show(mainPanel, "winner1");
                break;
            case 3:
                mainPanel.remove(gamePanel);
                gamePanel = new GamePanel(this);
                mainPanel.add(gamePanel, "game");
                layout.show(mainPanel, "winner2");
                break;
            case 4:
                layout.show(mainPanel, "settings");
                break;

        }
    }

    private void loadGame() {

        Tank tank1 = null;
        Tank tank2 = null;
        int[] map = null;

        try (BufferedReader in = new BufferedReader(new FileReader("savedGame.dat"))) {
            String line;
            map = new int[1400];
            int angle, pos, turr, power, health;


            try {
                line = in.readLine();
                angle = Integer.parseInt(line.trim());

                line = in.readLine();
                pos = Integer.parseInt(line.trim());

                line = in.readLine();
                turr = Integer.parseInt(line.trim());

                line = in.readLine();
                power = Integer.parseInt(line.trim());

                line = in.readLine();
                health = Integer.parseInt(line.trim());

                tank1 = new Tank(angle, pos, 0);
                tank1.setTurretAngle(turr);
                tank1.setHealth(health);
                tank1.setPower(power);

                line = in.readLine();
                angle = Integer.parseInt(line.trim());

                line = in.readLine();
                pos = Integer.parseInt(line.trim());

                line = in.readLine();
                turr = Integer.parseInt(line.trim());

                line = in.readLine();
                power = Integer.parseInt(line.trim());

                line = in.readLine();
                health = Integer.parseInt(line.trim());

                tank2 = new Tank(angle, pos, 0);
                tank2.setTurretAngle(turr);
                tank2.setHealth(health);
                tank2.setPower(power);

            } catch (NumberFormatException e) {
                System.err.println("WRONG FORMAT");
            }
            for (int i = 0; i < 1400 && (line = in.readLine()) != null; i++) {
                try {
                    int number = Integer.parseInt(line.trim());
                    map[i] = number;
                } catch (NumberFormatException e) {
                    System.err.println("WRONG FORMAT");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainPanel.remove(gamePanel);
        gamePanel = new GamePanel(this, tank1, tank2, map);
        mainPanel.add(gamePanel, "game");
    }

    public void setTank1(Tank tank1) {
        this.tank1 = tank1;
    }

    public void setTank2(Tank tank2) {
        this.tank2 = tank2;
    }

    public void setMap(int[] map) {
        this.map = map;
    }

    private void saveGame() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("savedGame.dat"))) {
            out.write(String.valueOf(tank1.getAngle()));
            out.newLine();
            out.write(String.valueOf(tank1.getPosition()));
            out.newLine();
            out.write(String.valueOf(tank1.getTurretAngle()));
            out.newLine();
            out.write(String.valueOf(tank1.getPower()));
            out.newLine();
            out.write(String.valueOf(tank1.getHealth()));
            out.newLine();

            out.write(String.valueOf(tank2.getAngle()));
            out.newLine();
            out.write(String.valueOf(tank2.getPosition()));
            out.newLine();
            out.write(String.valueOf(tank2.getTurretAngle()));
            out.newLine();
            out.write(String.valueOf(tank2.getPower()));
            out.newLine();
            out.write(String.valueOf(tank2.getHealth()));
            out.newLine();

            for (int i = 0; i < 1400; i++) {
                out.write(String.valueOf(map[i]));
                out.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        ChangeGameState(1);
    }

    public void stopMusic() {
        soundPlayer.StopBGMusic();
    }

    public void startMusic() {
        soundPlayer.PlayBGMusic();
    }
}
