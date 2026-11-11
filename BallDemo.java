import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Box box;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     * 
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        box=new Box (100,100,500,400, myCanvas);
        box.draw();

        Box box2 = new Box(myCanvas);
        box2.draw();

    }

    /**
     * boxBounce - simulate 5-50 balls bouncing within a box
     * 
     * @param numOfBalls number of balls to simulate bouncing, clamped between 5-50. 
     */
    public void boxBounce(int numOfBalls)
    {
        // keeping the number of balls between 5 and 30
        if (numOfBalls < 5) 
        {
            numOfBalls = 5;
        }
        if (numOfBalls > 30) 
        {
            numOfBalls = 30;
        }

        Random rand = new Random();
        int diameter = 16;

        myCanvas.setVisible(true);

        //create the balls
        ArrayList<BoxBall> balls = new ArrayList<BoxBall>();
        for (int i = 0; i < numOfBalls; i++) 
        {
            // random position
            int boxWidth = box.getRightWall() - box.getLeftWall();
            int boxHeight = box.getBottomWall() - box.getTopWall();
            int x = box.getLeftWall() + 1 + rand.nextInt(boxWidth - diameter - 1);
            int y = box.getTopWall() + 1 + rand.nextInt(boxHeight - diameter - 1);

            // random color
            int red = rand.nextInt(201);
            int green = rand.nextInt(201);
            int blue = rand.nextInt(201);
            Color ballColor = new Color(red, green, blue);

            BoxBall ball = new BoxBall(x, y, diameter, ballColor, box, myCanvas);
            balls.add(ball);
            ball.draw();
        }

        // run the simulation
        boolean running = true;
        while (running) 
        {
            myCanvas.wait(30);
            for (BoxBall ball : balls) 
            {
                ball.move();
            }
            // redraw the box every tick so the walls never go away
            myCanvas.setForegroundColor(Color.BLACK);
            box.draw();

        }
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
