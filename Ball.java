package kaleidoscope;

public class Ball extends Figure {
    private int ballSize;
	
    Ball(int size) {
    	ballSize = size;
    }
    
    @Override
	public void setLimits(int xLimit, int yLimit) {
        setXLimit(xLimit - ballSize);
        setYLimit(yLimit - ballSize);
        for (int x = 0; x < getXPosition(); x++) {
        	setXPosition(Math.min(getXPosition(), getXLimit()));
        }
        for (int y = 0; y < getYPosition(); y++) {
        	setYPosition(Math.min(getYPosition(), getYLimit()));
        }
	}
}
