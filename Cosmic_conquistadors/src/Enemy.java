public class Enemy extends DefaultCritter {

    Enemy next;

    public Enemy(double curX) {
        x = curX;
        y = 340;
        next = null;
    }

    public void setXposition(float curX) {
        x = curX;
    }

    public void setYposition(float curY) {
        y = curY;
    }

    public void Move() {
        if (y + 10 > -350) {
            y -= 60;
        }
        if (next != null)
            next.Move();
    }

    public void Print() {
        StdDraw.filledSquare(x, y, 20);
        if (next != null)
            next.Print();
    }

    public void NewWave() {
        if (next == null) {
            next = new Enemy(-480);
            Enemy temp2 = new Enemy(-360);
            next.next = temp2;
            Enemy temp3 = new Enemy(-240);
            temp2.next = temp3;
            Enemy temp4 = new Enemy(-120);
            temp3.next = temp4;
            Enemy temp5 = new Enemy(0);
            temp4.next = temp5;
            Enemy temp6 = new Enemy(120);
            temp5.next = temp6;
            Enemy temp7 = new Enemy(240);
            temp6.next = temp7;
            Enemy temp8 = new Enemy(360);
            temp7.next = temp8;
            Enemy temp9 = new Enemy(480);
            temp8.next = temp9;
            Enemy temp10 = new Enemy(-480);
            temp9.next = temp10;
            temp10.next = null;
        } else
            next.NewWave();
    }
}
