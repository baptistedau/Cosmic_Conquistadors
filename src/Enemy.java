public class Enemy extends DefaultCritter {


    public Enemy(double curX, double curY) {
        x = curX;
        y = curY;
        angle = 1;
        speed = 2;
        size = 30;
    }

    public boolean Move() {
        if (InvaderGameState.time % 30 == 0) {
            angle = -angle;
            y -= 9 * speed;
        } else {
            x += angle * speed;
        }
        return (y - 9 * speed > Invaders.scaleMin || contact(InvaderGameState.shooter));
    }


}
