import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HippodromeGUI extends JPanel {

    private List<Horse> horses;
    private Timer timer;

    public HippodromeGUI(List<Horse> horses) {
        this.horses = horses;

        timer = new Timer(200, e -> {
            moveHorses();
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

            g.setColor(Color.BLUE);
            g.fillRect(x, y, 60, 20);

            // имя рядом
            g.setColor(Color.BLACK);
            g.drawString(horse.getName(), x, y - 5);

            y += 60;
        }
    }
}

