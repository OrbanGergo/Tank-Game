import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    private int[] map;
    private Tank tank1;
    private Tank tank2;
    private BufferedImage img;
    private BufferedImage turretImg;
    private JSlider slider;
    private JSlider slider2;
    private JPanel controls;
    private JPanel bottomPanel;
    private Player1Panel player1;
    private Player2Panel player2;
    private boolean turn;
    private int time;
    private Timer timer;
    private Graphics graphics;
    private boolean trajectory;
    private TrajectoryCalculator trajectoryCalculator;
    private Tank tank;
    private MainFrame mainFrame;

    public GamePanel(MainFrame mainFrame) {

        this.mainFrame=mainFrame;
        setLayout(new BorderLayout());

        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setPreferredSize(new Dimension(1400, 200));
        bottomPanel.setLayout(new GridLayout(1, 3));

        this.add(bottomPanel, BorderLayout.SOUTH);

        controls = new JPanel();
        controls.setBackground(Color.WHITE);
        controls.setLayout(new GridLayout(3, 1));

        player1 = new Player1Panel();
        player2 = new Player2Panel();

        bottomPanel.add(player1);
        bottomPanel.add(controls);
        bottomPanel.add(player2);

        JLabel label1 = new JLabel("Set The Turret Angle:");
        label1.setFont(new Font("Arial", Font.PLAIN, 15));
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalAlignment(JLabel.BOTTOM);

        slider = new JSlider(0, 180);
        slider.setValue(180);
        slider.setBackground(Color.WHITE);
        slider.add(label1);


        JLabel label2 = new JLabel("Set The Turret Power:");
        label2.setFont(new Font("Arial", Font.PLAIN, 15));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalAlignment(JLabel.BOTTOM);

        slider2 = new JSlider(0, 300);
        slider2.setValue(180);
        slider2.setBackground(Color.WHITE);
        slider2.add(label2);

        JButton button = new JButton("FIRE!");
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5),
                BorderFactory.createLineBorder(Color.GRAY, 20)));
        button.setFont(new Font("Bodoni MT", Font.PLAIN, 25));
        button.setBackground(Color.GRAY);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(400, 100));

        controls.add(slider);
        controls.add(slider2);
        controls.add(button);

        MapGenerator mapGenerator = new MapGenerator();
        map = mapGenerator.GetMap();
        tank1 = MapGenerator.GenerateTankLeft();
        tank2 = MapGenerator.GenerateTankRight();

        //player1.ChangeMyTurn();
        turn = false;
        Turn1();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer soundPlayer = new SoundPlayer();
                soundPlayer.PlayClick();

                //System.out.println("aaa");
                //System.out.println(tank1.getTurretAngle());
                //System.out.println(tank1.getDiff());
                //System.out.println(tank1.getTurretAngle()+tank1.getDiff());
                if (tank1.getTurretAngle() + tank1.getDiff() > 0 && tank1.getTurretAngle() + tank1.getDiff() < 90 && !turn) {
                    timer.stop();
                    trajectoryCalculator = new TrajectoryCalculator(tank1.getTurretAngle() + tank1.getDiff(), tank1.getPower(), 5000);
                    tank = tank1;
                    trajectory = true;
                    repaint();

                    //ChangeTurn();
                }
                int newTurrAngle = 180 - tank2.getTurretAngle() - tank2.getDiff();
                if (turn && newTurrAngle > 0 && newTurrAngle < 90) {
                    //System.out.print("diff");
                    //System.out.println(tank2.getDiff());
                    //System.out.print("turr");
                    //System.out.println(tank2.getTurretAngle());

                    //System.out.println(newTurrAngle);

                    timer.stop();
                    trajectoryCalculator = new TrajectoryCalculator(newTurrAngle, tank2.getPower(), 5000);
                    tank = tank2;
                    trajectory = true;
                    repaint();

                    //ChangeTurn();
                }
            }
        });

        try {
            img = ImageIO.read(new File("images/tank2.png"));
            turretImg = ImageIO.read(new File("images/turret2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (turn) {
                    tank2.setTurretAngle(180 - slider.getValue());
                } else {
                    tank1.setTurretAngle(180 - slider.getValue());
                }
                repaint();
            }
        });

        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (turn) {
                    tank2.setPower(slider2.getValue());
                } else {
                    tank1.setPower(slider2.getValue());
                }
                repaint();
            }
        });

    }

    public GamePanel(MainFrame mainFrame,Tank tankk1, Tank tankk2, int[] map){

        this.mainFrame=mainFrame;
        setLayout(new BorderLayout());

        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setPreferredSize(new Dimension(1400, 200));
        bottomPanel.setLayout(new GridLayout(1, 3));

        this.add(bottomPanel, BorderLayout.SOUTH);

        controls = new JPanel();
        controls.setBackground(Color.WHITE);
        controls.setLayout(new GridLayout(3, 1));

        player1 = new Player1Panel();
        player2 = new Player2Panel();

        bottomPanel.add(player1);
        bottomPanel.add(controls);
        bottomPanel.add(player2);

        JLabel label1 = new JLabel("Set The Turret Angle:");
        label1.setFont(new Font("Arial", Font.PLAIN, 15));
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalAlignment(JLabel.BOTTOM);

        slider = new JSlider(0, 180);
        slider.setValue(180);
        slider.setBackground(Color.WHITE);
        slider.add(label1);


        JLabel label2 = new JLabel("Set The Turret Power:");
        label2.setFont(new Font("Arial", Font.PLAIN, 15));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalAlignment(JLabel.BOTTOM);

        slider2 = new JSlider(0, 300);
        slider2.setValue(180);
        slider2.setBackground(Color.WHITE);
        slider2.add(label2);

        JButton button = new JButton("FIRE!");
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5),
                BorderFactory.createLineBorder(Color.GRAY, 20)));
        button.setFont(new Font("Bodoni MT", Font.PLAIN, 25));
        button.setBackground(Color.GRAY);
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(400, 100));

        controls.add(slider);
        controls.add(slider2);
        controls.add(button);

        MapGenerator mapGenerator = new MapGenerator();
        map = mapGenerator.GetMap();
        tank1 = MapGenerator.GenerateTankLeft();
        tank2 = MapGenerator.GenerateTankRight();

        //player1.ChangeMyTurn();
        turn = false;
        Turn1();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundPlayer soundPlayer = new SoundPlayer();
                soundPlayer.PlayClick();

                //System.out.println("aaa");
                //System.out.println(tank1.getTurretAngle());
                //System.out.println(tank1.getDiff());
                //System.out.println(tank1.getTurretAngle()+tank1.getDiff());
                if (tank1.getTurretAngle() + tank1.getDiff() > 0 && tank1.getTurretAngle() + tank1.getDiff() < 90 && !turn) {
                    timer.stop();
                    trajectoryCalculator = new TrajectoryCalculator(tank1.getTurretAngle() + tank1.getDiff(), tank1.getPower(), 5000);
                    tank = tank1;
                    trajectory = true;
                    repaint();

                    //ChangeTurn();
                }
                int newTurrAngle = 180 - tank2.getTurretAngle() - tank2.getDiff();
                if (turn && newTurrAngle > 0 && newTurrAngle < 90) {
                    //System.out.print("diff");
                    //System.out.println(tank2.getDiff());
                    //System.out.print("turr");
                    //System.out.println(tank2.getTurretAngle());

                    //System.out.println(newTurrAngle);

                    timer.stop();
                    trajectoryCalculator = new TrajectoryCalculator(newTurrAngle, tank2.getPower(), 5000);
                    tank = tank2;
                    trajectory = true;
                    repaint();

                    //ChangeTurn();
                }
            }
        });

        try {
            img = ImageIO.read(new File("images/tank2.png"));
            turretImg = ImageIO.read(new File("images/turret2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (turn) {
                    tank2.setTurretAngle(180 - slider.getValue());
                } else {
                    tank1.setTurretAngle(180 - slider.getValue());
                }
                repaint();
            }
        });

        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (turn) {
                    tank2.setPower(slider2.getValue());
                } else {
                    tank1.setPower(slider2.getValue());
                }
                repaint();
            }
        });
        this.map=map;
        this.tank1=tankk1;
        this.tank2=tankk2;
        tank1.setPositionB(map[tank1.getPosition()]);
        tank2.setPositionB(map[tank2.getPosition()]);

    }

    public static BufferedImage rotate(BufferedImage bimg, Double angle) {
        double sin = Math.abs(Math.sin(Math.toRadians(angle))),
                cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = bimg.getWidth();
        int h = bimg.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin),
                newh = (int) Math.floor(h * cos + w * sin);
        BufferedImage rotated = new BufferedImage(neww, newh, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.translate((neww - w) / 2, (newh - h) / 2);
        graphic.rotate(Math.toRadians(angle), w / 2, h / 2);
        graphic.drawRenderedImage(bimg, null);
        graphic.dispose();
        return rotated;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = g;

        g.setColor(new Color(20, 100, 0));
        for (int i = 0; i < 1400; i++) {
            g.fillRect(i, map[i], 1, 800 - map[i]);
        }

        g.setColor(new Color(100, 230, 255));
        for (int i = 0; i < 1400; i++) {
            g.fillRect(i, 0, 1, map[i]);
        }
        //System.out.print(turn);

        if (trajectory) {
            if (!turn) {
                DrawTrajectoryTurn1();
            } else {
                DrawTrajectoryTurn2();
            }

        }
        //System.out.println(turn);

        BufferedImage img1 = rotate(img, (double) tank1.getAngle());
        BufferedImage img2 = rotate(img, (double) tank2.getAngle());

        g.drawImage(img1, tank1.getImagePositionA(), tank1.getImagePositionB(), tank1.getImgWidth(), tank1.getImgHeight(), null);
        g.drawImage(img2, tank2.getImagePositionA(), tank2.getImagePositionB(), tank2.getImgWidth(), tank2.getImgHeight(), null);
        g.setColor(Color.DARK_GRAY);


        BufferedImage turretImg2 = rotate(turretImg, (double) tank1.getRealTurretAngle());
        g.drawImage(turretImg2, tank1.getImagePositionA(), tank1.getImagePositionB() - 20, 60, 60, null);

        //g.fillRect(tank1.getPosition()-turretPosA,map[tank1.getPosition()] - turretPosB,35,6);


        BufferedImage turretImg3 = rotate(turretImg, (double) tank2.getRealTurretAngle());
        g.drawImage(turretImg3, tank2.getImagePositionA(), tank2.getImagePositionB() - 20, 60, 60, null);

        //g.fillRect(tank2.getPosition()-turretPosA,map[tank2.getPosition()] - turretPosB,35,6);


        //g.setColor(Color.RED);
        //g.fillRect(tank1.getImagePositionA(),tank1.getImagePositionB(),3,3);
        //g.fillRect(tank1.getPosition(),map[tank1.getPosition()],3,3);

    }

    public void ChangeTurn() {
        player1.ChangeMyTurn();
        player2.ChangeMyTurn();

        mainFrame.setMap(map);
        mainFrame.setTank1(tank1);
        mainFrame.setTank2(tank2);

        turn = !turn;
        repaint();
        if (turn) {
            Turn2();
            //System.out.println("turn2");
            //System.out.println(player1.getMyTurn());
            //System.out.println(player2.getMyTurn());
        } else {
            Turn1();
            //System.out.println("turn1");
            // System.out.println(player1.getMyTurn());
            //System.out.println(player2.getMyTurn());
        }
    }

    public void Turn1() {
        turn = false;
        if (!player1.getMyTurn()) {
            player1.ChangeMyTurn();
        }
        slider.setValue(180-tank1.getTurretAngle());
        slider2.setValue(tank1.getPower());
        time = 30;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time--;
                player1.setTime(time);
                if (time <= 0) {
                    timer.stop();
                    ChangeTurn();
                }
            }
        });
        timer.start();
        turn = false;

    }

    public void Turn2() {
        turn = true;
        if (!player2.getMyTurn()) {
            player2.ChangeMyTurn();
        }

        slider.setValue(180-tank2.getTurretAngle());
        slider2.setValue(tank2.getPower());
        time = 30;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time--;
                player2.setTime(time);
                if (time <= 0) {
                    timer.stop();
                    ChangeTurn();
                }
            }
        });
        timer.start();
        turn = true;
    }

    private void DrawTrajectoryTurn1() {
        //System.out.println("TRAJ1");
        //System.out.println(turn);

        boolean ok = true;
        int[] coordinates = trajectoryCalculator.getXCoordinates();
        int[] coordinates2 = trajectoryCalculator.getYCoordinates();
        for (int i = 0; ok && tank.getPosition() + i < 1400; i++) {
            int tPosA = tank.getPosition() - tank.getTurretPosA() + 15;
            int tPosB = map[tank.getPosition()] - tank.getTurretPosB() + 15;
            int y = tPosB - coordinates2[i];
            int x = tPosA + coordinates[i];

            graphics.setColor(Color.RED);
            //graphics.fillRect(tPosA,tPosB,3,3);
            if (x > 0 && y > 0) {
                graphics.fillOval(x, y, 5, 5);
            }
            if (x > 1398 || y > map[x] && i > 10) {
                ok = false;
            }

            if (trajectoryCalculator.Distance(x, y, tank2.getPosition(), map[tank2.getPosition()]) < 50) {
                tank2.lowerHealth();
                player2.LowerHP();
                if(tank2.getHealth()==0){
                    mainFrame.ChangeGameState(2);
                }
                ok = false;
            }

        }
        trajectory = false;


        ChangeTurn();
    }

    private void DrawTrajectoryTurn2() {

        //System.out.println("TRAJ2");
        //System.out.println(turn);
        boolean ok = true;
        int[] coordinates = trajectoryCalculator.getXCoordinates();
        int[] coordinates2 = trajectoryCalculator.getYCoordinates();
        for (int i = 0; ok && tank.getPosition() + i < 1400; i++) {
            int tPosA = tank.getPosition() - tank.getTurretPosA();
            int tPosB = map[tank.getPosition()] - tank.getTurretPosB() + 15;
            int y = tPosB - coordinates2[i];
            int x = tPosA - coordinates[i];

            graphics.setColor(Color.RED);
            graphics.fillRect(tPosA, tPosB, 3, 3);
            if (x > 0 && y > 0) {
                graphics.fillOval(x, y, 5, 5);
            }
            if (x > 1398 || x < 3 || y > map[x] && i > 10) {
                ok = false;
            }
            if (trajectoryCalculator.Distance(x, y, tank1.getPosition(), map[tank1.getPosition()]) < 50) {
                tank1.lowerHealth();
                player1.LowerHP();
                ok = false;
                //System.out.println("ALMA");
                if(tank1.getHealth()==0){
                    mainFrame.ChangeGameState(3);
                }
            }
            //try {
            //    Thread.sleep(100);
            //} catch (InterruptedException e) {
            //    Thread.currentThread().interrupt();
            //}
        }
        trajectory = false;

        ChangeTurn();
    }
}
