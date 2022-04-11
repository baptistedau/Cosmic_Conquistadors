public class DefaultCritter {

    double x;
    double y;
    double speed;
    double angle;
    double size;
    String Picture;

    public double getXposition() {
        return x;
    }

    public double getYposition() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public void Print() {
        StdDraw.filledSquare(x, y, size);
    }

    public boolean contact(DefaultCritter b) {
        double dist = (this.size + b.size) / 2;
        //if (Math.sqrt((this.x - b.x) * (this.x - b.x) - (this.y - b.y) * (this.y - b.x)) <= dist)
        if (Math.abs(this.y - b.y) <= dist && Math.abs(this.x - b.x) <= dist)
            return true;
        return false;
    }
}
