import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class InvaderGameState {


    public static ArrayList<Enemy> enemies;
    public static ArrayList<Missile> missiles;
    public static Shooter shooter;

    public static int time;
    public static int time_shoot;
    public static int score;

    public InvaderGameState() {
        enemies = new ArrayList<>();
        missiles = new ArrayList<>();
        shooter = new Shooter();

        time = 0;
        time_shoot = 0;
        score = 0;
    }


    public void Update() {
        StdDraw.enableDoubleBuffering();
        boolean quit = false;
        NewWave();

        System.out.print("Hi");
        
        while (!quit) {
            StdDraw.clear();
            //part where we get the Keys pressed
            if (time % 200 == 0)
                NewWave();


            if (StdDraw.isKeyPressed(KeyEvent.VK_Q))
                quit = true;

            if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
                shooter.Move_right();
            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
                shooter.Move_left();

            if (StdDraw.isKeyPressed(32) && time_shoot == 0) {
                time_shoot = 10;
                missiles.add(new Missile(shooter.getXposition(), -300, shooter.angle));
            }

            if (StdDraw.isKeyPressed(KeyEvent.VK_UP) && time % 10 == 0)
                shooter.setAngle(Math.PI / 32);
            if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && time % 10 == 0)
                shooter.setAngle(-Math.PI / 32);
            if (StdDraw.isKeyPressed(KeyEvent.VK_C))
                shooter.angle = Math.PI / 2;

            for (int i = 0; i < missiles.size(); i++) {
                if (missiles.get(i).Move()) {
                    missiles.get(i).Print();
                    for (int j = 0; j < enemies.size(); j++) {
                        if (missiles.get(i).contact(enemies.get(j))) {
                            enemies.remove(j);
                            missiles.remove(i);
                            score++;
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
                    quit = true;
                }
            }

            shooter.Print();
            StdDraw.show();
            StdDraw.pause(20);

            time++;
            if (time_shoot > 0)
                time_shoot--;
        }

    }

    public static void NewWave() {
        for (int x = Invaders.scaleMin + 100; x < Invaders.scaleXMax - 50; x += 80) {
            enemies.add(new Enemy(x, Invaders.scaleYMax));
        }
    }

    public void PrintMenu() {
        boolean quit = false;
        StdDraw.enableDoubleBuffering();

        while (!quit) {

            StdDraw.clear();
            StdDraw.picture(0.25, 0.25, "../resources/back.png", 1200, 700);

            Font name = new Font("Castellar", Font.BOLD, 60);
            Font rest = new Font("Castellar", Font.BOLD, 20);
            Font star = new Font("Castellar", Font.BOLD, 40);
            StdDraw.setPenColor(StdDraw.ORANGE);

            StdDraw.setFont(name);
            StdDraw.text(0, 200, "Cosmic Conquistadors");
            StdDraw.setFont(rest);
            StdDraw.text(0, 100, "Controls:");
            StdDraw.text(0, 75, "(A) Move Left    (D) Move Right    (SPACEBAR) Shoot");
            StdDraw.text(0, -200, "(Q) Quit Game");
            StdDraw.setFont(star);
            StdDraw.text(0, -100, "(START) START Game");

            if (StdDraw.isKeyPressed(10) || StdDraw.isKeyPressed(81)) {
                quit = true;
                StdAudio.close();
            }
            if (StdDraw.isKeyPressed(32)) {
                quit = true;
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
    }

}
