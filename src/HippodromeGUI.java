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
            moveHorses();

            currentFrame++;
            if (currentFrame >= frames.length) {
                currentFrame = 0;
            }

            repaint();
        });

        timer.start();
    }

    private void moveHorses() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int y = 50;

        for (Horse horse : horses) {
            int x = (int) horse.getDistance();

            g.drawImage(frames[currentFrame], x, y, 80, 50, null);
            //Отрисовка имени рядосм лошадью
            g.setColor(Color.BLACK);
            g.drawString(horse.getName(), x, y - 5);

            y += 60;
        }
    }
}

