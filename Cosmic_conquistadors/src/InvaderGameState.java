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
    public static int Power_UP;

    public static int Missiles_speed;

    public InvaderGameState() {
        enemies = new ArrayList<>();
        missiles = new ArrayList<>();
        shooter = new Shooter();

        time = 0;
        time_shoot = 10;
        score = 0;
        Level = 1;
        Power_UP = 0;

        //Time Between each shoot
        Missiles_speed = 10;

    }

    public boolean Play() {
        boolean state = true;
        //while the player hasn't destroy 12 level, it will play new levels, but if he died, it will print a game over screen.
        while (state) {
            state = Update();
            Level += 1;
            if (Level % 2 == 0)
                Power_UP += 1;
            if (Level == 12)
                state = false;
            StdDraw.pause(100);
        }
        return GameOver();


    }


    public boolean Update() {
        StdDraw.enableDoubleBuffering();
        boolean quit = false;
        time = 0;


        while (!quit) {
            StdDraw.clear();
            //Create each rows of enemies, the formula is 2 + Levels
            if (time % 75 == 0 && time < (2 + Level) * 75)
                NewWave();

            //print the background + all the information for the player like the score, the level..
            PrintBackground();

            //tell when a level is complete
            if (score != 0 && enemies.size() == 0) {
                return true;
            }

            //When the player press P or M, he can increase the speed or the cadence of shooting
            if (StdDraw.isKeyPressed(KeyEvent.VK_P) && Power_UP > 0 && shooter.getSpeed() < 50) {
                shooter.speed += 10;
                Power_UP -= 1;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_M) && Power_UP > 0 && Missiles_speed > 6) {
                Missiles_speed -= 2;
                Power_UP -= 1;
            }

            //if Q if press it will show the game_over screen
            if (StdDraw.isKeyPressed(KeyEvent.VK_Q))
                quit = true;

            //This is where we get all the player input and we move the shooter in consequences.
            if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
                shooter.Move_right();
            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
                shooter.Move_left();
            if (StdDraw.isKeyPressed(32) && time_shoot == 0) {
                time_shoot = Missiles_speed;
                missiles.add(new Missile(shooter.getXposition(), Invaders.scaleMin + shooter.size * 2, shooter.angle));
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP))
                shooter.setAngle(10);
            if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
                shooter.setAngle(-10);
            if (StdDraw.isKeyPressed(KeyEvent.VK_C))
                shooter.angle = 0;

            //Update for each missile
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

            //Update for each enemies
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
        //Print the galaxy in the background
        StdDraw.picture(Invaders.scaleYMax / 2, Invaders.scaleYMax / 2, "../resources/BackgroundGame.jpg", Invaders.scaleXMax + 300, Invaders.scaleYMax);
        StdDraw.setFont(new Font("Castellar", Font.BOLD, 25));

        //Print the score, the level and the number of Power_Up point.
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(Invaders.scaleXMax + 100, Invaders.scaleYMax - 50, "Level " + Level);
        StdDraw.text(Invaders.scaleXMax + 100, Invaders.scaleYMax - 100, "Score: " + score);
        StdDraw.text(Invaders.scaleXMax + 120, Invaders.scaleYMax - 150, "Power_UP: ");
        StdDraw.text(Invaders.scaleXMax + 100, Invaders.scaleYMax - 180, "" + Power_UP);

        //Print the Player speed Power_up information.
        StdDraw.setFont(new Font("Castellar", Font.BOLD, 10));
        StdDraw.picture(Invaders.scaleXMax + 100, Invaders.scaleYMax - 300, "../resources/Upgrade_arrow.png", 64, 64);
        StdDraw.text(Invaders.scaleXMax + 100, Invaders.scaleYMax - 350, " (P): Player_Speed");
        if (shooter.getSpeed() > 20)
            StdDraw.filledSquare(Invaders.scaleXMax + 150, Invaders.scaleYMax - 325, 5);
        if (shooter.getSpeed() > 30)
            StdDraw.filledSquare(Invaders.scaleXMax + 150, Invaders.scaleYMax - 312, 5);
        if (shooter.getSpeed() > 40)
            StdDraw.filledSquare(Invaders.scaleXMax + 150, Invaders.scaleYMax - 300, 5);

        //Print the Missile cadence Power_up information.
        StdDraw.picture(Invaders.scaleXMax + 100, Invaders.scaleYMax - 500, "../resources/Upgrade_arrow.png", 64, 64);
        StdDraw.text(Invaders.scaleXMax + 100, Invaders.scaleYMax - 550, " (M): Missiles_Cadence");
        if (Missiles_speed < 10)
            StdDraw.filledSquare(Invaders.scaleXMax + 150, Invaders.scaleYMax - 520, 5);
        if (Missiles_speed < 8)
            StdDraw.filledSquare(Invaders.scaleXMax + 150, Invaders.scaleYMax - 500, 5);
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
            StdDraw.text(0, 50, "(UP Arrow) Rotate Left    (DOWN Arrow) Rotate Right   (C) Reset Rotation");
            StdDraw.text(0, 75, "(LEFT Arrow) Move Left    (RIGHT Arrow) Move Right    (SPACEBAR) Shoot");
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

    public boolean GameOver() {
        boolean quit = true;
        while (quit) {
            StdDraw.enableDoubleBuffering();

            StdDraw.clear(StdDraw.BLACK);
            StdDraw.setPenColor(StdDraw.BOOK_RED);

            Font gameover = new Font("Castellar", Font.BOLD, 60);
            Font quitgame = new Font("Castellar", Font.BOLD, 50);
            Font restart = new Font("Castellar", Font.BOLD, 50);

            StdDraw.setFont(gameover);
            StdDraw.text(400, 600, " GAME OVER ");
            StdDraw.setFont(quitgame);
            StdDraw.text(900, 400, "your score: " + score + "");
            StdDraw.text(400, 400, " QUIT (Q)");
            StdDraw.setFont(restart);
            StdDraw.text(400, 200, "RESTART (R)");

            StdDraw.show();
            StdDraw.pause(10);

            if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
                return true;
            }

            if (StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                quit = false;
            }
        }
        return false;

    }

}
