package models;

/**
 * Throws when the rover try to overtake the plateau bound
 * @Ruan
 */
public class PositionOutOfBoundsException extends RuntimeException{

	private static final long serialVersionUID = -6454392663892861202L;

	public PositionOutOfBoundsException(Position position) {
	
		super(position.getActualPosition() + " is out of the plateau bounds");	
	
	}
	
	
}
