package org.models;

/**
 * The position occupied by a rover in the plateau
 * 
 * @Ruan
 */
public class Position {
	private int x;
	private int y;
	private Facing facing;

	public Position(int x, int y, Facing facing) {
		this.x = x;
		this.y = y;
		this.facing = facing;
	}

	public boolean equals(Position otherPosition) {	return x == otherPosition.x && y == otherPosition.y; }

	public int getX() {	return x; }

	public void setX(int x) { this.x = x; }

	public int getY() {	return y; }

	public void setY(int y) { this.y = y; }

	public Facing getFacing() {	return facing; }

	public void setFacing(Facing facing) { this.facing = facing; }

	@Override
	public String toString() { return String.format("%s %s %s", x, y, facing); }

}
