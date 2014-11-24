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
	void setXPosition(int xPos) {
		this.xPosition = xPos;
	}
	
	/**
     * Getter for xPosition
     * @return The x coordinate of the left side of the figure.
     */
	int getXPosition(){
		return this.xPosition;
	}

    /**
     * Setter for yPosition
     * @param yPos The y coordinate of the top of the figure.
     */
	void setYPosition(int yPos) {
		this.yPosition = yPos;
	}

	/**
     * Getter for yPosition
     * @return The y coordinate of the top of the figure.
     */
	int getYPosition(){
		return this.yPosition;
	}

    /**
     * Setter for xDelta
     * @param xDel The x velocity of the figure.
     */
	void setXDelta(int xDel) {
		this.xDelta = xDelta;
	}
	
	/**
     * Getter for xDelta
     * @return The x velocity of the figure.
     */
	int getXDelta(){
		return this.xDelta;
	}

    /**
     * Setter for yDelta
     * @param yDel The y velocity of the figure.
     */
	void setYDelta(int yDel) {
		this.yDelta = yDel;
	}

	/**
     * Getter for yDelta
     * @return The y velocity of the figure.
     */
	int getYDelta(){
		return this.yDelta;
	}
	
}
