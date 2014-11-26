package kaleidoscope;

public class Figure {
	private int xPosition;
	private int yPosition;
	private int xLimit;
	private int yLimit;
	private int xDelta;
	private int yDelta;
	private int xDelta0;
	private int yDelta0;
	private int width;
	private int height;
	private String type;
	
	Figure(int width, int height, String type) {
		this.width = width;
		this.height = height;
		this.type = type;
	}
	
    /**
     * Setter for xPosition
     * @param xPos The x coordinate of the left side of the figure.
     */
	public void setXPosition(int xPos) {
		xPosition = xPos;
	}
	
	/**
     * Getter for xPosition
     * @return The x coordinate of the left side of the figure.
     */
	public int getXPosition(){
		return xPosition;
	}

    /**
     * Setter for yPosition
     * @param yPos The y coordinate of the top of the figure.
     */
	public void setYPosition(int yPos) {
		yPosition = yPos;
	}

	/**
     * Getter for yPosition
     * @return The y coordinate of the top of the figure.
     */
	public int getYPosition(){
		return yPosition;
	}

    /**
     * Setter for xDelta
     * @param xDel The x velocity of the figure.
     */
	public void setXDelta(int xDel) {
		xDelta = xDel;
	}
	
	/**
     * Getter for xDelta
     * @return The x velocity of the figure.
     */
	public int getXDelta(){
		return xDelta;
	}

    /**
     * Setter for xDelta0
     * @param xDel The initial x velocity of the figure.
     */
	public void setInitXDelta(int xDel0) {
		xDelta0 = xDel0;
	}

    /**
     * Getter for xDelta0
     * @return The initial x velocity of the figure.
     */
	public int getInitXDelta() {
		return xDelta0;
	}

    /**
     * Setter for yDelta
     * @param yDel The y velocity of the figure.
     */
	public void setYDelta(int yDel) {
		yDelta = yDel;
	}

	/**
     * Getter for yDelta
     * @return The y velocity of the figure.
     */
	public int getYDelta(){
		return yDelta;
	}

    /**
     * Setter for yDelta0
     * @param yDel The initial y velocity of the figure.
     */
	public void setInitYDelta(int yDel0) {
		yDelta0 = yDel0;
	}

    /**
     * Getter for yDelta0
     * @return The initial y velocity of the figure.
     */
	public int getInitYDelta() {
		return yDelta0;
	}

    /**
     * Setter for xLimit
     * @param xLim The x limit of the figure.
     */
	public void setXLimit(int xLim) {
		xLimit = xLim;
	}
	
	/**
     * Getter for xLimit
     * @return The x limit of the figure.
     */
	public int getXLimit(){
		return xLimit;
	}

    /**
     * Setter for yLimit
     * @param yLim The y limit of the figure.
     */
	public void setYLimit(int yLim) {
		yLimit = yLim;
	}

	/**
     * Getter for yLimit
     * @return The y limit of the figure.
     */
	public int getYLimit() {
		return yLimit;
	}
	
    /**
     * Tells the figure to advance one step in the direction that it is moving.
     * If it hits a wall, its direction of movement changes.
     */
	public void makeOneStep() {
		setXPosition(getXPosition() + getXDelta());
    	if (getXPosition() < 0 || getXPosition() >= getXLimit()) {
    		setXDelta(-1 * getXDelta());
    		setXPosition(getXPosition() + getXDelta());
    	}
    	setYPosition(getYPosition() + getYDelta());
    	if (getYPosition() < 0 || getYPosition() >= getYLimit()) {
    		setYDelta(-1 * getYDelta());
    		setYPosition(getYPosition() + getYDelta());
    	}
	}
	
	/**
     * Sets the appropriate x and y limits for the figure based on its size
     * @param xLimit The maximum allowed x value for the display
     * @param yLimit The maximum alloewd y value for the display
     */
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
	
	/**
     * Returns the figure's type as a String
     * @return The figure's type
     */
	public String getType() {
		return type;
	}
}
