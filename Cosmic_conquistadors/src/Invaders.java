public class Invaders {

    Enemy enemies;

    public boolean test() {


        StdDraw.setCanvasSize(1200, 700);
        StdDraw.setYscale(-350, 350);
        StdDraw.setXscale(-600, 600);
        StdDraw.enableDoubleBuffering();

        int time = 0;
        Shooter shooter = new Shooter(300, 10);
        enemies = new Enemy(0);

        while (true) {

            //part where we get the Keys pressed
            if (StdDraw.isKeyPressed(68))
                shooter.Move_right();
            if (StdDraw.isKeyPressed(65))
                shooter.Move_left();
            if (StdDraw.isKeyPressed(32) && time % 10 == 0) {
                time = 10;
                shooter.Shoot();
            }
            if (StdDraw.isKeyPressed(90) && time % 10 == 0)
                shooter.setAngle(Math.PI / 32);
            if (StdDraw.isKeyPressed(66) && time % 10 == 0)
                shooter.setAngle(-Math.PI / 32);

            //part where we Update every item position
            if (enemies != null) {
                if (time % 20 == 0) {
                    enemies.Move();
                }
                if (time % 40 == 0)
                    enemies.NewWave();
            }
            if (shooter.missile != null) {
                shooter.missile.Move();
            }

            //part where we print all the modifications.
            StdDraw.clear();

            if (shooter.missile != null)
                shooter.missile.Print();
            enemies.Print();
            StdDraw.filledSquare(shooter.getXposition(), -300, 30);

            StdDraw.show();
            StdDraw.pause(30);

            time++;
        }

    }


}
