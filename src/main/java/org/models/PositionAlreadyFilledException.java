package org.models;

/**
 * Throws when a rover try to move into a place already occupied
 * @Ruan
 */
public class PositionAlreadyFilledException extends RuntimeException{

	private static final long serialVersionUID = -5364744104552614385L;

	public PositionAlreadyFilledException(Position position) {
		super(position.getActualPosition() + " is already filled by a rover");

	}

}
