public class Horse {
    String name;
    double speed;
    double distance;

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setName(String name) {
        this.name = name;
    }

    void move(){
        distance += (speed * Math.random());
    }
    void print(){
        StringBuilder toch = new StringBuilder();
        for(int i = 0; i < (int) distance; i++){
            toch.append(".");
        }
        System.out.println(toch + getName());
    }
}