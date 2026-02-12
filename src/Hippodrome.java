import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    static Hippodrome game;
    private List<Horse> horses;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    void run() throws InterruptedException {
        for (int i = 1; i < 101; i++){
            move();
            print();
            Thread.sleep(200);
        }
    }
    void move(){
        for(Horse horse : horses){
            horse.move();
        }
    }
    void print(){
        for(Horse horse : horses){
            horse.print();
        }
        for(int i = 0; i < 10; i++){
            System.out.println();
        }
    }

    public Horse getWinner(){
        Horse result = horses.get(0);
        for(Horse horse : horses){
            if(horse.getDistance() > result.getDistance()){
                result = horse;
            }
        }
        return result;
    }
    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

    public static void main(String[] args) {

        ArrayList<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Amigo", 3, 0));
        horses.add(new Horse("Diego", 3, 0));
        horses.add(new Horse("Emmy", 3, 0));

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hippodrome");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 400);

            HippodromeGUI panel = new HippodromeGUI(horses);
            frame.add(panel);

            frame.setVisible(true);
        });
    }
}