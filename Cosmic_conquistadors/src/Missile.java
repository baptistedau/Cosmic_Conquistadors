public class Missile extends DefaultCritter {


    public Missile(double X, double Y, double dir) {
        x = X;
        y = Y;
        angle = 90 + dir;
        speed = 20;
        size = 15;
        Picture = "../resources/BulletV1.png";
    }


    public boolean Move() {
        if (y + 10 < Invaders.scaleYMax && x + 10 < Invaders.scaleXMax && x - 10 > Invaders.scaleMin) {
            x += (speed) * Math.cos(Math.PI / 180 * angle);
            y += (speed) * Math.sin(Math.PI / 180 * angle);
            return true;
        }
        return false;

    }


}
