public class Enemy extends DefaultCritter {


    public Enemy(double curX, double curY) {
        x = curX;
        y = curY;
        angle = 1;
        if (InvaderGameState.Level < 6)
            speed = 5;
        else
            speed = 7;
        size = 32;
        Picture = "../resources/Enemy.png";
    }

    public boolean Move() {
        if (InvaderGameState.time % 30 == 0) {
            angle = -angle;
            y -= 6 * speed;
        } else {
            x += angle * speed;
        }
        return (y - 6 * speed > Invaders.scaleMin && !contact(InvaderGameState.shooter, 1));
    }


}
