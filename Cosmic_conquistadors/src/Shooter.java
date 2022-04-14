public class Shooter extends DefaultCritter {


    public Shooter() {
        size = 32;
        x = Invaders.scaleXMax / 2;
        y = Invaders.scaleMin + size + 20;
        speed = 20;
        angle = 0;
        Picture = "../resources/SpaceShipV1.png";

    }

    public void setAngle(double n) {
        if (angle + n < 90 && angle + n > -90)
            angle += n;
    }
    
    public void Move_right() {
        if (x + speed < Invaders.scaleXMax)
            x += speed;
    }

    public void Move_left() {
        if (x - speed > Invaders.scaleMin)
            x -= speed;
    }


}





