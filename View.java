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
@SuppressWarnings("serial")
public class View extends JPanel implements Observer {
    
    /** This is what we will be observing. */
    Model model;
    Color ballColor;
    Color rectColor;
    Color triColor;
    Color bgColor;

    /**
     * Constructor.
     * @param model The Model whose working is to be displayed.
     */
    View(Model model) {
        this.model = model;
        ballColor = Color.green;
        rectColor = Color.green;
        triColor = Color.green;
        bgColor = Color.WHITE;
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
        g.setColor(bgColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (int i = 0; i < model.numObjects; i++) {
			thisFigure = model.getFigure(i);
			if (thisFigure.getType().equals("ball")) {
				g.setColor(ballColor);
				paintBalls(g, thisFigure);
			} else if (thisFigure.getType().equals("rect")) {
				g.setColor(rectColor);
				paintRectangle(g, thisFigure);
			} else if (thisFigure.getType().equals("tri")) {
				g.setColor(triColor);
				paintTriangles(g, thisFigure);

			}
		}
    }

	/**
	 * Paints the 8 different copies of the ball
	 *
	 * @param g The Graphics on which to paint things.
	 * @param ball The Ball object to be painted (and reflected)
	 */
	private void paintBalls(Graphics g, Figure ball) {
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
	 * Paints the 8 different copies of the rectangle
	 *
	 * @param g The Graphics on which to paint things.
	 * @param rectangle The Rectangle object to be painted (and reflected)
	 */
	private void paintRectangle(Graphics g, Figure rectangle) {
		int x = rectangle.getXPosition();
		int y = rectangle.getYPosition();
		int negX = getNegativeX(x);
		int negY = getNegativeY(y);
		int width = model.RECT_WIDTH;
		int height = model.RECT_HEIGHT;
		g.fillRect(x, y, width, height);
		g.fillRect(negX - width, y, width, height);
		g.fillRect(x, negY - height, width, height);
		g.fillRect(negX - width, negY - height, width, height);
		g.fillRect(y - height, x - width, height, width);
		g.fillRect(negY, x - width, height, width);
		g.fillRect(y - height, negX, height, width);
		g.fillRect(negY, negX, height, width);
	}

	/**
	 * Paints the 8 different copies of the triangle
	 *
	 * @param g The Graphics on which to paint things.
	 * @param tri The Triangle object to be painted (and reflected)
	 */
	private void paintTriangles(Graphics g, Figure tri) {
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
		if (dir.equals("up")) {
			getUpCoords(x, y, xCoords, yCoords);
		}
		else if (dir.equals("down")) {
			getDownCoords(x, y, xCoords, yCoords);
		}
		else if (dir.equals("left")) {
			getLeftCoords(x, y, xCoords, yCoords);
		}
		else if (dir.equals("right")) {
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
	
	/**
	 * Changes the color to use when painting balls
	 *
	 * @param color The color to use for balls.
	 */
	public void setBallColor(Color color) {
		ballColor = color;
	}
	
	/**
	 * Changes the color to use when painting rectangles
	 *
	 * @param color The color to use for rectangles.
	 */
	public void setRectColor(Color color) {
		rectColor = color;
	}
	
	/**
	 * Changes the color to use when painting triangles
	 *
	 * @param color The color to use for triangles.
	 */
	public void setTriColor(Color color) {
		triColor = color;
	}
	
	/**
	 * Changes the background color
	 *
	 * @param color The color to use for background.
	 */
	public void setBgColor(Color color) {
		bgColor = color;
	}
}