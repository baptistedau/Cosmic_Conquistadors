public class Invaders {

    public static int scaleXMax = 1000;
    public static int scaleYMax = 750;
    public static int scaleMin = 0;

    public static void main(String[] args) {

        StdDraw.setCanvasSize(1200, 700);


        StdAudio.loop("../resources/MenuMusic.wav");
        boolean loop = true;
        while (loop) {
            InvaderGameState Game = new InvaderGameState();
            StdDraw.setXscale(-600, 600);
            StdDraw.setYscale(-350, 350);
            Game.PrintMenu();
            StdDraw.setXscale(scaleMin, scaleXMax + 200);
            StdDraw.setYscale(scaleMin, scaleYMax);
            loop = Game.Play();
        }
        System.exit(0);
    }


}
