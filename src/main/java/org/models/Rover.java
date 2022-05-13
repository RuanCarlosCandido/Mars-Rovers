package org.models;

/**
 * The Rover holds a position until it have a permission to move
 * following an instruction
 * @Ruan
 */
public class Rover {
	private Position position;
	private String identification;

	public Rover(String identification, Position position) {
		this.position = position;
		this.identification = identification;
	}

	/**
	 * Make the rover move for each command in the instruction 
	 * @param instruction
	 */
	public void executeInstruction(Instruction instruction, Plateau plateau) {

		instruction
		.getCommands()
		.forEach(command -> {
			if(	position.isOutOfBounds(plateau) ) 
				throw new PositionOutOfBoundsException(position);
			if(	position.isPositionFilled(plateau, identification)	)
				throw new PositionAlreadyFilledException(position);
			else
				this.position.move(command);
		});    	
	}

	public void setPosition(Position position) { this.position = position; }

	public Position getPosition() { return this.position; }

	public String getIdentification() {	return identification; }

	public void setIdentification(String identification) { this.identification = identification; }

	public void showPosition() {

		System.out.println(this.getIdentification() + " position = " +this.getPosition().getActualPosition());
	}

}
