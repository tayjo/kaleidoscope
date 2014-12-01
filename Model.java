package kaleidoscope;

import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This is the Model class for a kaleidoscope. It is an Observable,
 * which means that it can notifyObservers that something in the
 * model has changed, and they should take appropriate actions. It
 * contains an array of objects that have varying positions, speeds
 * and types.
 * 
 * @author David Matuszek
 * @author Josh Taylor
 * @author Ted Fujimoto
 */
public class Model extends Observable {
    public final int BALL_SIZE = 100;
    public final int RECT_WIDTH = 200;
    public final int RECT_HEIGHT = 100;
    public final int TRI_WIDTH = 150;
    public final int TRI_HEIGHT = 150;
    public int numObjects = 15;
    private Timer timer;
    private Figure[] figures;
    private Random rand;
    
    /**
     * Constructor
     */
    Model () {
    	rand = new Random();
    	figures = new Figure[numObjects];
    	for (int i = 0; i < numObjects; i++) {
    		if (i % 3 == 2) {
    			figures[i] = new Figure(BALL_SIZE, BALL_SIZE, "ball");
    		}
    		else if (i % 3 == 1) {
    			figures[i] = new Figure(RECT_WIDTH, RECT_HEIGHT, "rect");
    		}
    		else {
    			figures[i] = new Figure(TRI_WIDTH, TRI_HEIGHT, "tri");
    		}
    	}
    	setInitialPositions();
    	setInitialVelocities();
    }
    
    /**
     * Randomly sets the initial positions of the objects
     */
    private void setInitialPositions() {
    	for (int i = 0; i < numObjects; i++) {
    		figures[i].setXPosition(rand.nextInt(600));
    		figures[i].setYPosition(rand.nextInt(600));
    	}
    }
    
    /**
     * Randomly sets the initial velocities of the objects
     */
    private void setInitialVelocities() {
    	for (int i = 0; i < numObjects; i++) {
    		figures[i].setInitXDelta(rand.nextInt(5) + 1);
    		figures[i].setXDelta(figures[i].getInitXDelta());
    		figures[i].setInitYDelta(rand.nextInt(5) + 1);
    		figures[i].setYDelta(figures[i].getInitYDelta());
    	}
    }
    
    /**
     * Sets the "walls" that the ball should bounce off from.
     * 
     * @param xLimit The position (in pixels) of the wall on the right.
     * @param yLimit The position (in pixels) of the floor.
     */
    public void setLimits(int xLimit, int yLimit) {
        for (int i = 0; i < numObjects; i++) {
        	figures[i].setLimits(xLimit, yLimit);
        }
    }

    /**
     * Accepts an element index number and returns the
     * figure corresponding to that index in the figures array
     * @param element The index number of the desired figure
     * @return The requested figure object
     */
    public Figure getFigure(int element) {
    	return figures[element];
    }
    
    /**
     * Tells the ball to start moving. This is done by starting a Timer
     * that periodically executes a TimerTask. The TimerTask then tells
     * the ball to make one "step."
     */
    public void start() {
        timer = new Timer(true);
        timer.schedule(new Strobe(), 0, 40); // 25 times a second        
    }
    
    /**
     * Tells the ball to stop where it is.
     */
    public void pause() {
        timer.cancel();
    }
    
    /**
     * Tells the figures to advance one step in the direction that they are moving.
     * If they hit a wall, their direction of movement changes.
     */
    public void makeOneStep() {
        // Do the work
		for (int i = 0; i < numObjects; i++) {
			figures[i].makeOneStep();
		}
        // Notify observers
        setChanged();
        notifyObservers();
    }
    
    /**
     * Tells the model to advance one "step."
     */
    private class Strobe extends TimerTask {
        @Override
        public void run() {
            makeOneStep();
        }
    }
    
    /**
     * Sets new velocities
     */
    public void changeVelocity(float speedFactor) {
    	int xDelta, yDelta, xDelta0, yDelta0;
    	for (int i = 0; i < numObjects; i++) {
    		xDelta = figures[i].getXDelta();
    		yDelta = figures[i].getYDelta();
    		xDelta0 = figures[i].getInitXDelta();
    		yDelta0 = figures[i].getInitYDelta();
    		figures[i].setXDelta((int) (Math.signum(xDelta) * Math.ceil(speedFactor * xDelta0)));
    		figures[i].setYDelta((int) (Math.signum(yDelta) * Math.ceil(speedFactor * yDelta0)));
    	}
    }
}