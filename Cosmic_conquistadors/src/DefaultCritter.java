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
    
    public double getSpeed() {
        return speed;
    }

    public void Print() {
        if (Picture != null)
            StdDraw.picture(x, y, Picture, size * 2, size * 2, angle);
        else
            StdDraw.filledSquare(x, y, size);
    }

    public boolean contact(DefaultCritter b, double n) {
        double dist = (this.size + b.size) / n;
        if (Math.abs(this.y - b.y) <= dist && Math.abs(this.x - b.x) <= dist)
            return true;
        return false;
    }
}
