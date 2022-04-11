import java.util.ArrayList;

public class Invaders {

    public ArrayList<Enemy> enemies = new ArrayList<>();
    public ArrayList<Missile> missiles = new ArrayList<>();
    public static int time = 0;

    public boolean Update() {
        Shooter shooter = new Shooter(300, 10);
        StdDraw.enableDoubleBuffering();
        while (true) {
            StdDraw.clear();
            //part where we get the Keys pressed

            if (time % 50 == 0)
                enemies.add(new Enemy(0, 350));

            if (StdDraw.isKeyPressed(68))
                shooter.Move_right();
            if (StdDraw.isKeyPressed(65))
                shooter.Move_left();
            if (StdDraw.isKeyPressed(32) && time % 8 == 0) {
                missiles.add(new Missile(shooter.getXposition(), -300, shooter.angle));
            }
            if (StdDraw.isKeyPressed(90) && time % 10 == 0)
                shooter.setAngle(Math.PI / 32);
            if (StdDraw.isKeyPressed(66) && time % 10 == 0)
                shooter.setAngle(-Math.PI / 32);

            for (int i = 0; i < missiles.size(); i++) {
                if (missiles.get(i).Move()) {
                    missiles.get(i).Print();
                    for (int j = 0; j < enemies.size(); j++) {
                        if (missiles.get(i).contact(enemies.get(j))) {
                            enemies.remove(j);
                            missiles.remove(i);
                            break;
                        }
                    }
                } else
                    missiles.remove(i);

            }

            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).Move())
                    enemies.get(i).Print();
                else {
                    enemies.remove(i);
                    StdOut.println("fini");
                }

            }


            shooter.Print();


            StdDraw.show();
            StdDraw.pause(30);

            time++;
        }

    }


}
