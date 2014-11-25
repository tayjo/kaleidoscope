package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * The View "observes" and displays what is going on in the Model.
 * In this example, the Model contains only a single bouncing ball.
 * 
 * @author David Matuszek
 * @author Your name goes here
 * @author Your name goes here
 */
public class View extends JPanel implements Observer {
    
    /** This is what we will be observing. */
    Model model;

    /**
     * Constructor.
     * @param model The Model whose working is to be displayed.
     */
    View(Model model) {
        this.model = model;
    }

    /**
     * Displays what is going on in the Model. Note: This method should
     * NEVER be called directly; call repaint() instead.
     * 
     * @param g The Graphics on which to paint things.
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
    	Figure thisFigure;
    	Ball ball;
    	Rectangle rect;
    	Triangle tri;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.red);
        for (int i = 0; i < model.numObjects; i++) {
        	thisFigure = model.getFigure(i);
	        if (thisFigure instanceof Ball) {
	        	ball = (Ball) thisFigure;
	        	paintBall(g, ball);
	        }
	        else if (thisFigure instanceof Rectangle) {
	        	g.fillRect(model.getX(i), model.getY(i),
	        			model.RECT_WIDTH, model.RECT_HEIGHT);
	        }
	        else if (thisFigure instanceof Triangle) {
	        	tri = (Triangle) thisFigure;
	        	g.fillPolygon(tri.getXCoords(), tri.getYCoords(), 3);
	        }
        }
    }

    private void paintBall(Graphics g, Ball ball) {
    	int x = ball.getXPosition();
    	int y = ball.getYPosition();
    	int negX = getNegativeX(x);
    	int negY = getNegativeY(y);
    	g.fillOval(x, y, model.BALL_SIZE, model.BALL_SIZE);
    	g.fillOval(negX, y, model.BALL_SIZE, model.BALL_SIZE);
    	g.fillOval(x, negY, model.BALL_SIZE, model.BALL_SIZE);
    	g.fillOval(negX, negY, model.BALL_SIZE, model.BALL_SIZE);
    	g.fillOval(y, x, model.BALL_SIZE, model.BALL_SIZE);
    	g.fillOval(negY, x, model.BALL_SIZE, model.BALL_SIZE);
    	g.fillOval(y, negX, model.BALL_SIZE, model.BALL_SIZE);
    	g.fillOval(negY, negX, model.BALL_SIZE, model.BALL_SIZE);
    }
    
    private int getNegativeX(int x) {
    	return getWidth() - x;
    }
    
    private int getNegativeY(int y) {
    	return getHeight() - y;
    }
    
    /**
     * When an Observer notifies Observers (and this View is an Observer),
     * this is the method that gets called.
     * 
     * @param obs Holds a reference to the object being observed.
     * @param arg If notifyObservers is given a parameter, it is received here.
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable obs, Object arg) {
        repaint();
    }
}