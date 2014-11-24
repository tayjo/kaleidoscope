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
    private int[] xPosition;
    private int[] yPosition;
    private int xLimit, yLimit;
    private int[] xDelta;
    private int[] yDelta;
    private Timer timer;
    private Figure[] figures;

    
    Model () {
    	xPosition = new int[numObjects];
    	yPosition = new int[numObjects];
    	xDelta = new int[numObjects];
    	yDelta = new int[numObjects];
    	for (int i = 0; i < xDelta.length; i++) {
    		xDelta[i] = 6;
    		yDelta[i] = 4;
    	}
    	setInitialPositions();
    }
    
    /**
     * Sets the initial positions of the objects
     */
    private void setInitialPositions() {
    	xPosition[0] = 0;
    	xPosition[1] = 50;
    	yPosition[0] = 0;
    	yPosition[1] = 100;
    }
    
    /**
     * Sets the "walls" that the ball should bounce off from.
     * 
     * @param xLimit The position (in pixels) of the wall on the right.
     * @param yLimit The position (in pixels) of the floor.
     */
    public void setLimits(int xLimit, int yLimit) {
        this.xLimit = xLimit - BALL_SIZE;
        this.yLimit = yLimit - BALL_SIZE;
        for (int x = 0; x < xPosition.length; x++) {
        	xPosition[x] = Math.min(xPosition[x], xLimit);
        }
        for (int y = 0; y < yPosition.length; y++) {
        	yPosition[y] = Math.min(yPosition[y], yLimit);
        }
    }

    /**
     * @return The balls X position.
     */
    public int getX(int element) {
        return xPosition[element];
    }

    /**
     * @return The balls Y position.
     */
    public int getY(int element) {
        return yPosition[element];
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
     * Tells the ball to advance one step in the direction that it is moving.
     * If it hits a wall, its direction of movement changes.
     */
    public void makeOneStep() {
        // Do the work
    	for (int i = 0; i < xPosition.length; i++) {
    		xPosition[i] += xDelta[i];
        	if (xPosition[i] < 0 || xPosition[i] >= xLimit) {
        		xDelta[i] = -xDelta[i];
        		xPosition[i] += xDelta[i];
        	}
        	yPosition[i] += yDelta[i];
        	if (yPosition[i] < 0 || yPosition[i] >= yLimit) {
        		yDelta[i] = -yDelta[i];
        		yPosition[i] += yDelta[i];
        	}
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