package kaleidoscope;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This is the Model class for a bouncing ball. It is an Observable,
 * which means that it can notifyObservers that something in the
 * model has changed, and they should take appropriate actions.
 * 
 * @author David Matuszek
 * @author Josh Taylor
 * @author Ted Fujimoto
 */
public class Model extends Observable {
    public final int BALL_SIZE = 20;
    public int numObjects = 2;
    private Timer timer;
    private Figure[] figures;

    
    Model () {
    	figures = new Figure[2];
    	for (int i = 0; i < numObjects; i++) {
    		figures[i] = new Ball(BALL_SIZE);
    	}
    	setInitialPositions();
    	setInitialVelocities();
    }
    
    /**
     * Sets the initial positions of the objects
     */
    private void setInitialPositions() {
    	figures[0].setXPosition(0);
    	figures[1].setXPosition(50);
    	figures[0].setYPosition(0);
    	figures[1].setYPosition(100);
    }
    
    private void setInitialVelocities() {
    	figures[0].setXDelta(6);
    	figures[0].setYDelta(4);
    	figures[1].setXDelta(2);
    	figures[1].setYDelta(8);
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
     * @return The balls X position.
     */
    public int getX(int element) {
        return figures[element].getXPosition();
    }

    /**
     * @return The balls Y position.
     */
    public int getY(int element) {
        return figures[element].getYPosition();
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
}