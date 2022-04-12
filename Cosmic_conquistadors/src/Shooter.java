public class Shooter extends DefaultCritter {


    public Shooter() {
        size = 32;
        x = Invaders.scaleXMax / 2;
        y = Invaders.scaleMin + size;
        speed = 10;
        angle = Math.PI / 2;

    }

    public void setAngle(double n) {
        if (angle + n < Math.PI && angle + n > 0)
            angle += n;
    }


    public void Move_right() {
        if (x + size < Invaders.scaleXMax)
            x += speed;
    }

    public void Move_left() {
        if (x - size > Invaders.scaleMin)
            x -= speed;
    }


}





