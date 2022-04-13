public class Enemy extends DefaultCritter {


    public Enemy(double curX, double curY, int level) {
        x = curX;
        y = curY;
        angle = 1;
        speed = 2;
        size = 30;
        if (level == 0) {
            
        }
    }

    public boolean Move() {
        if (InvaderGameState.time % 30 == 0) {
            angle = -angle;
            y -= 8 * speed;
        } else {
            x += angle * speed;
        }
        return (y - 8 * speed > Invaders.scaleMin || contact(InvaderGameState.shooter));
    }


}
