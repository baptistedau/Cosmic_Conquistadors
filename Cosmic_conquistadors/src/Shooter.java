public class Shooter extends DefaultCritter {

    double speed;
    double angle;
    Missile missile;

    public Shooter(double X, double Speed) {
        x = X;
        speed = Speed;
        angle = Math.PI / 2;
        missile = null;
        StdDraw.filledSquare(x, -500, 30);
    }


    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAngle(double n) {
        if (angle + n < Math.PI && angle + n > 0)
            angle += n;
    }


    public void Move_right() {
        if (x + 60 < 600)
            x += speed;
    }

    public void Move_left() {

        if (x - 60 > -600)
            x -= speed;
    }

    public void Shoot() {
        if (missile == null) {
            missile = new Missile(getXposition(), 0, null, angle);
        } else
            missile.NewMissile(x, -350, angle);

    }

}





