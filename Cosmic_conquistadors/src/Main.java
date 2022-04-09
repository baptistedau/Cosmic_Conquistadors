import java.awt.*;

public class Main {
    public static void main(String[] args) {

        StdDraw.setCanvasSize(1200, 700);
        StdDraw.setYscale(-350, 350);
        StdDraw.setXscale(-600, 600);
        //StdAudio.loop("../resources/MenuMusic.wav");
        StdDraw.enableDoubleBuffering();

        Invaders newI = new Invaders();

        boolean quit = false;
        boolean play = false;
        boolean restart = false;

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
                quit = newI.test();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        System.exit(0);
    }
}
