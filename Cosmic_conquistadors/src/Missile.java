public class Missile extends DefaultCritter {


    public Missile(double X, double Y, double dir) {
        x = X;
        y = Y;
        angle = dir;
        speed = 20;
        size = 10;
    }


    public boolean Move() {
        if (y + 10 < 350 && x + 10 < 600 && x - 10 > -600) {
            x += (speed) * Math.cos(angle);
            y += (speed) * Math.sin(angle);
            return true;
        }
        return false;

    }


}
