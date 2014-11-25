package kaleidoscope;

public class Triangle extends Figure {
	private int width;
	private int height;
	
	Triangle(int width, int height) {
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
}
