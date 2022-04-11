public class Shooter extends DefaultCritter {


    public Shooter(double X, double Speed) {
        x = X;
        y = -310;
        speed = Speed;
        angle = Math.PI / 2;
        size = 30;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAngle(double n) {
        if (angle + n < Math.PI && angle + n > 0)
            angle += n;
    }


    public void Move_right() {
        if (x + size < 600)
            x += speed;
    }

    public void Move_left() {
        if (x - size > -600)
            x -= speed;
    }
    

}





