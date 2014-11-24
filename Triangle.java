package kaleidoscope;

public class Triangle extends Figure {
	private int width;
	private int height;
	private int[] xCoords;
	private int[] yCoords;
	
	Triangle(int width, int height) {
		xCoords = new int[3];
		yCoords = new int[3];
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void setLimits(int xLimit, int yLimit) {
        setXLimit(xLimit - width);
        setYLimit(yLimit - height);
        for (int x = 0; x < getXPosition(); x++) {
        	setXPosition(Math.min(getXPosition(), getXLimit()));
        }
        for (int y = 0; y < getYPosition(); y++) {
        	setYPosition(Math.min(getYPosition(), getYLimit()));
        }
	}
	
	public int[] getXCoords() {
		xCoords[0] = getXPosition();
		xCoords[1] = getXPosition() + this.width / 2;
		xCoords[2] = getXPosition() + this.width;
		return xCoords;
	}
	
	public int[] getYCoords() {
		yCoords[0] = getYPosition() + this.height;
		yCoords[1] = getYPosition();
		yCoords[2] = getYPosition() + this.height;
		return yCoords;
	}
}
