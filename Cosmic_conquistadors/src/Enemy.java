public class Enemy extends DefaultCritter {


    public Enemy(double curX, double curY) {
        x = curX;
        y = curY;
        angle = Math.PI / 2;
        speed = 2;
        size = 20;
    }

    public boolean Move() {
        if (y - speed > -350) {
            y -= speed;
            return true;
        }
        return false;
    }

    public void NewWave() {

    }
}
