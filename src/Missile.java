public class Missile extends DefaultCritter {


    public Missile(double X, double Y, double dir) {
        x = X;
        y = Y;
        angle = dir;
        speed = 40;
        size = 10;
    }


    public boolean Move() {
        if (y + 10 < Invaders.scaleYMax && x + 10 < Invaders.scaleXMax && x - 10 > Invaders.scaleMin) {
            x += (speed) * Math.cos(angle);
            y += (speed) * Math.sin(angle);
            return true;
        }
        return false;

    }


}
