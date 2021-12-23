package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Exploreable area for rovers
 * @Ruan
 */
public class Plateau {

	public int limitX;
	public int limitY;
	
	// List containing all added rovers
	private List<Rover> rovers = new ArrayList<Rover>();
	
	public Plateau(int limitX, int limitY) {
		this.limitX = limitX;
		this.limitY = limitY;
	}
	
	public Plateau addRover(Rover rover) {
		rovers.add(rover);
		return this;
	}

	public List<Rover> getRovers() {
		return rovers;
	}

	public void removeAllRovers() {
		rovers = new ArrayList<Rover>();
	}
	
}
