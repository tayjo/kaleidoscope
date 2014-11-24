package kaleidoscope;

public abstract class Figure {
	private int xPosition;
	private int yPosition;
	private int xLimit;
	private int yLimit;
	private int xDelta;
	private int yDelta;
	
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
     * Abstract method for setLimits
     */
	public void setLimits(int xLimit, int yLimit){}	
}
