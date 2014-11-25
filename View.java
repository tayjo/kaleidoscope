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
 * @author Josh Taylor
 * @author Ted Fujimoto
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
	        	paintBalls(g, ball);
	        }
	        else if (thisFigure instanceof Rectangle) {
	        	g.fillRect(model.getX(i), model.getY(i),
	        			model.RECT_WIDTH, model.RECT_HEIGHT);
	        }
	        else if (thisFigure instanceof Triangle) {
	        	tri = (Triangle) thisFigure;
	        	paintTriangles(g, tri);
	        }
        }
    }
    
    /**
     * Paints the 8 different copies of the ball
     * 
     * @param g The Graphics on which to paint things.
     * @param ball The Ball object to be painted (and reflected)
     */
    private void paintBalls(Graphics g, Ball ball) {
    	int x = ball.getXPosition();
    	int y = ball.getYPosition();
    	int negX = getNegativeX(x);
    	int negY = getNegativeY(y);
    	int size = model.BALL_SIZE;
    	g.fillOval(x, y, size, size);
    	g.fillOval(negX - size, y, size, size);
    	g.fillOval(x, negY - size, size, size);
    	g.fillOval(negX - size, negY - size, size, size);
    	g.fillOval(y - size, x - size, size, size);
    	g.fillOval(negY, x - size, size, size);
    	g.fillOval(y - size, negX, size, size);
    	g.fillOval(negY, negX, size, size);
    }
   
    /**
     * Paints the 8 different copies of the triangle
     * 
     * @param g The Graphics on which to paint things.
     * @param tri The Triangle object to be painted (and reflected)
     */
    private void paintTriangles(Graphics g, Triangle tri) {
    	int x = tri.getXPosition();
    	int y = tri.getYPosition();
    	int negX = getNegativeX(x);
    	int negY = getNegativeY(y);
    	int width = model.TRI_WIDTH;
    	int height = model.TRI_HEIGHT;
    	drawTriangle(g, x, y, "up");
    	drawTriangle(g, negX - width, y, "up");
    	drawTriangle(g, x, negY - height, "down");
    	drawTriangle(g, negX - width, negY - height, "down");
    	drawTriangle(g, y - height, x - width, "left");
    	drawTriangle(g, negY, x - width, "right");
    	drawTriangle(g, y - height, negX, "left");
    	drawTriangle(g, negY, negX, "right");
    }

    /**
     * Calculates the coordinates of a triangle based on the x & y
     * position of the triangle and the direction that it is pointing,
     * then paints the triangle. Throws an exception if the direction
     * provided is not valid.
     * 
     * @param g The Graphics on which to paint things.
     * @param x The x position of the triangle
     * @param y The y position of the triangle
     * @param dir The direction that the triangle is pointing
     * ("up", "down", "left", or "right")
     */
    private void drawTriangle(Graphics g, int x, int y, String dir) {
    	int[] xCoords = new int[3];
    	int[] yCoords = new int[3];
    	if (dir == "up") {
    		getUpCoords(x, y, xCoords, yCoords);
    	}
    	else if (dir == "down") {
    		getDownCoords(x, y, xCoords, yCoords);
    	}
    	else if (dir == "left") {
    		getLeftCoords(x, y, xCoords, yCoords);
    	}
    	else if (dir == "right") {
    		getRightCoords(x, y, xCoords, yCoords);
    	}
    	else {
    		throw new IllegalArgumentException();
    	}
    	g.fillPolygon(xCoords, yCoords, 3);
    }
    
    /**
     * Calculates the x & y coordinates of the
     * vertices of an upwards pointing triangle
     * 
     * @param x The x position of the triangle.
     * @param y The y position of the triangle.
     * @param xCoords An array of size 3 that is loaded
     * with the x coordinates of the triangle's vertices
     * @param yCoords An array of size 3 that is loaded
     * with the y coordinates of the triangle's vertices
     */
    private void getUpCoords(int x, int y, int[] xCoords, int[] yCoords) {
		xCoords[0] = x;
		xCoords[1] = x + model.TRI_WIDTH / 2;
		xCoords[2] = x + model.TRI_WIDTH;
		yCoords[0] = y + model.TRI_HEIGHT;
		yCoords[1] = y;
		yCoords[2] = y + model.TRI_HEIGHT;
    }
    
    /**
     * Calculates the x & y coordinates of the
     * vertices of a downwards pointing triangle
     * 
     * @param x The x position of the triangle.
     * @param y The y position of the triangle.
     * @param xCoords An array of size 3 that is loaded
     * with the x coordinates of the triangle's vertices
     * @param yCoords An array of size 3 that is loaded
     * with the y coordinates of the triangle's vertices
     */
    private void getDownCoords(int x, int y, int[] xCoords, int[] yCoords) {
    	xCoords[0] = x;
		xCoords[1] = x + model.TRI_WIDTH;
		xCoords[2] = x + model.TRI_WIDTH / 2;
		yCoords[0] = y;
		yCoords[1] = y;
		yCoords[2] = y + model.TRI_HEIGHT;
    }
    
    /**
     * Calculates the x & y coordinates of the
     * vertices of a leftwards pointing triangle
     * 
     * @param x The x position of the triangle.
     * @param y The y position of the triangle.
     * @param xCoords An array of size 3 that is loaded
     * with the x coordinates of the triangle's vertices
     * @param yCoords An array of size 3 that is loaded
     * with the y coordinates of the triangle's vertices
     */
    private void getLeftCoords(int x, int y, int[] xCoords, int[] yCoords) {
    	xCoords[0] = x;
		xCoords[1] = x + model.TRI_HEIGHT;
		xCoords[2] = x + model.TRI_HEIGHT;
		yCoords[0] = y + model.TRI_WIDTH / 2;
		yCoords[1] = y;
		yCoords[2] = y + model.TRI_WIDTH;
    }
    
    /**
     * Calculates the x & y coordinates of the
     * vertices of a rightwards pointing triangle
     * 
     * @param x The x position of the triangle.
     * @param y The y position of the triangle.
     * @param xCoords An array of size 3 that is loaded
     * with the x coordinates of the triangle's vertices
     * @param yCoords An array of size 3 that is loaded
     * with the y coordinates of the triangle's vertices
     */
    private void getRightCoords(int x, int y, int[] xCoords, int[] yCoords) {
    	xCoords[0] = x;
		xCoords[1] = x + model.TRI_HEIGHT;
		xCoords[2] = x;
		yCoords[0] = y;
		yCoords[1] = y + model.TRI_WIDTH / 2;
		yCoords[2] = y + model.TRI_WIDTH;
    }
        
    /**
     * Returns the x coordinate on the view that 
     * corresponds to the negative of the given x
     * coordinate in a center-origin coordinate system
     * 
     * @param x The x coordinate
     * @return The coordinate corresponding to negative x
     */
    private int getNegativeX(int x) {
    	return getWidth() - x;
    }
    
    /**
     * Returns the y coordinate on the view that 
     * corresponds to the negative of the given y
     * coordinate in a center-origin coordinate system
     * 
     * @param y The y coordinate
     * @return The coordinate corresponding to negative y
     */
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