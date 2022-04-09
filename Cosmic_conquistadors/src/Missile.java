public class Missile extends DefaultCritter {

    double direct;
    Missile next;

    public void setNext(Missile next) {
        this.next = next;
    }

    public Missile(double X, double Y, Missile missile, double dir) {
        x = X;
        y = Y;
        next = missile;
        direct = dir;
        StdDraw.filledCircle(x, y, 10);
    }


    public boolean Move() {
        boolean temp = false;
        if (y + 10 < 350 && x + 10 < 600 && x - 10 > -600) {
            x = (float) (x + (50) * Math.cos(direct));
            y = (float) (y + (50) * Math.sin(direct));
            StdDraw.filledCircle(x, y, 10);
            temp = true;
        }
        if (next != null) {
            if (next.Move())
                return temp;
            else
                next = next.next;
        }
        return temp;
    }


    public void NewMissile(double X, double Y, double Dir) {
        if (next == null)
            setNext(new Missile(X, -350, null, Dir));
        else
            next.NewMissile(X, -350, Dir);
    }

    public void Print() {
        StdDraw.filledCircle(x, y, 10);
        if (next != null)
            next.Print();
    }
}
