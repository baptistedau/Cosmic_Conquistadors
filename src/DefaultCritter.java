public class DefaultCritter {

    double x;
    double y;
    double speed;
    double angle;
    double size;
    String player_pic;
    String invader_pic;
    String Print;

    public void player_pic() {
        StdDraw.picture(x, y, "../resources/player.png", 100, 100);
    }

    public void Print() {
        StdDraw.filledSquare(x, y, size);
    }

    public void invader_pic() {
        StdDraw.picture(x, y, "../resources/enemy1.png");
    }

    public double getXposition() {
        return x;
    }

    public double getYposition() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }


    public boolean contact(DefaultCritter b) {
        double dist = (this.size + b.size) / 2;
        if (Math.abs(this.y - b.y) <= dist && Math.abs(this.x - b.x) <= dist)
            return true;
        return false;
    }
}
