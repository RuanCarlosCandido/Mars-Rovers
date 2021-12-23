package models;

import static models.Facing.*;
import static models.Command.*;

/**
 * The position occupied by a rover in the plateau
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

	public String getActualPosition() {
		return String.format("%s %s %s", this.x, this.y, this.facing);
	}

	public void move(Command command) {

		if (command.equals(Left)) {
			if (facing.equals(N)) 
				facing = W;
			else if (facing.equals(E)) 
				facing = N;
			else if (facing.equals(W)) 
				facing = S;
			else if (facing.equals(S)) 
				facing = E;

		}
		if (command.equals(Right)) {
			if (facing.equals(N)) 
				facing = E;
			else if (facing.equals(E)) 
				facing = S;
			else if (facing.equals(W)) 
				facing = N;
			else if (facing.equals(S)) 
				facing = W;

		}
		if (command.equals(Move)) {
			if (facing.equals(N))
				y++;
			else if (facing.equals(E))
				x++;
			else if (facing.equals(W))
				x--;
			else if (facing.equals(S))
				y--;

		}
	}

	public boolean isOutOfBounds(Plateau plateau) {

		if (x < 0 || x > plateau.limitX) 
			return true;


		if (y < 0 || y > plateau.limitY) 
			return true;


		return false;
	}

	public boolean isPositionFilled(Plateau plateau, String roverId) {

		Position position = new Position(x, y, facing);

		return plateau
				.getRovers()
				.stream()
				.filter(rover -> !rover.getIdentification().equals(roverId))
				.anyMatch(rover -> rover.getPosition().equals(position));
	}

	public boolean equals(Position otherPosition) {
		return x == otherPosition.x && y == otherPosition.y;
	}

}
