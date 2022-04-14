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
    public static int Level;

    public InvaderGameState() {
        enemies = new ArrayList<>();
        missiles = new ArrayList<>();
        shooter = new Shooter();

        time = 0;
        time_shoot = 10;
        score = 0;
        Level = 1;
    }

    public void Play() {
        boolean state = true;
        while (state) {
            state = Update();
            Level += 1;
            StdDraw.pause(100);
        }


    }


    public boolean Update() {
        StdDraw.enableDoubleBuffering();
        boolean quit = false;
        //StdOut.println("test");
        time = 0;

        while (!quit) {
            StdDraw.clear();
            //part where we get the Keys pressed
            if (time % 75 == 0 && time < (2 + Level) * 75)
                NewWave();
            PrintBackground();

            if (score != 0 && enemies.size() == 0) {
                return true;
            }

            if (StdDraw.isKeyPressed(KeyEvent.VK_Q))
                quit = true;

            if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
                shooter.Move_right();
            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
                shooter.Move_left();

            if (StdDraw.isKeyPressed(32) && time_shoot == 0) {
                time_shoot = 10;
                missiles.add(new Missile(shooter.getXposition(), Invaders.scaleMin + shooter.size * 2, shooter.angle));
            }

            if (StdDraw.isKeyPressed(KeyEvent.VK_UP))
                shooter.setAngle(10);
            if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
                shooter.setAngle(-10);
            if (StdDraw.isKeyPressed(KeyEvent.VK_C))
                shooter.angle = 0;

            for (int i = 0; i < missiles.size(); i++) {
                if (missiles.get(i).Move()) {
                    missiles.get(i).Print();
                    for (int j = 0; j < enemies.size(); j++) {
                        if (missiles.get(i).contact(enemies.get(j), 2)) {
                            enemies.remove(j);
                            missiles.remove(i);
                            StdAudio.play("../resources/EnemyHit.wav");
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
        return false;

    }

    public static void NewWave() {
        double x = Invaders.scaleMin + 200;
        for (; x < Invaders.scaleXMax - 200; x += 80) {
            enemies.add(new Enemy(x, Invaders.scaleYMax));
        }
    }

    public void PrintBackground() {

        StdDraw.picture(Invaders.scaleYMax / 2, Invaders.scaleYMax / 2, "../resources/BackgroundGame.jpg", Invaders.scaleXMax + 300, Invaders.scaleYMax);
        StdDraw.setFont(new Font("Castellar", Font.BOLD, 25));
        StdDraw.text(Invaders.scaleXMax + 100, Invaders.scaleYMax - 50, "Level " + Level);
        StdDraw.text(Invaders.scaleXMax + 100, Invaders.scaleYMax - 100, "Score: " + score);
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
            StdDraw.pause(20);
        }
    }

}
