package org.models;

/**
 * The Rover holds a position until it have a permission to move
 * following an instruction
 * @Ruan
 */
public class Rover {
	private Position positionOnPlateau;
	private String identification;

	public Rover(String identification, Position position) {
		this.positionOnPlateau = position;
		this.identification = identification;
	}

	public void setPositionOnPlateau(Position position) { this.positionOnPlateau = position; }

	public Position getPositionOnPlateau() { return this.positionOnPlateau; }

	public String getIdentification() {	return identification; }

	public void setIdentification(String identification) { this.identification = identification; }

	@Override
	public String toString() {
		return identification + " position On Plateau= " + positionOnPlateau;
	}

}
