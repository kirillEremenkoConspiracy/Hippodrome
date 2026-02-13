import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HippodromeGUI extends JPanel {

    private List<Horse> horses;
    private Timer timer;

    private BufferedImage[] frames;
    private int currentFrame = 0;

    private boolean gameOver = false;
    private Horse winner = null;

    private final int FINISH_X = 700; // координата финиша

    private BufferedImage background; //фон

    public HippodromeGUI(List<Horse> horses) {
        this.horses = horses;

        frames = new BufferedImage[9];

        try {
            for (int i = 0; i < 9; i++) {
                frames[i] = ImageIO.read(new File(
                        "C:/JavaProject/Hippodrome/src/resources/Hours/" + (i + 1) + ".png"
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        timer = new Timer(120, e -> {

            if (!gameOver) {
                moveHorses();
                checkWinner();
            }

            currentFrame++;
            if (currentFrame >= frames.length) {
                currentFrame = 0;
            }

            repaint();
        });

        timer.start();

        try {
            background = ImageIO.read(new File(
                    "C:/JavaProject/Hippodrome/src/resources/background.png"
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveHorses() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    private void checkWinner() {
        for (Horse horse : horses) {
            if (horse.getDistance() >= FINISH_X) {
                gameOver = true;
                winner = horse;
                timer.stop();
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // рисуем фон
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        }

        int y = 50;

        for (Horse horse : horses) {
            int x = (int) horse.getDistance();

            g.drawImage(frames[currentFrame], x, y, 80, 50, null);

            g.setColor(Color.BLACK);
            g.drawString(horse.getName(), x, y - 5);

            y += 60;
        }

        if (gameOver && winner != null) {
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.setColor(Color.RED);
            g.drawString("Победил " + winner.getName() + "!", 250, 200);
        }
    }
}

