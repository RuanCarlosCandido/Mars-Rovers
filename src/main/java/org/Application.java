package org;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.models.Facing;
import org.models.Instruction;
import org.models.Plateau;
import org.models.Position;
import org.models.Rover;
import org.util.Util;

/*Expected outPut: 
 * Rover1 position = 1 3 N
 * Rover2 position = 5 1 E
*/
public class Application {

	private static final Logger logger = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) throws IOException {

		String receivedInstructionFromFile = null;

		try {
			receivedInstructionFromFile = Util.readFile();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "cannot read the file" + e.getMessage());
			throw e;
		}

		Plateau plateau = new Plateau(5, 5);

		Instruction instruction = new Instruction(receivedInstructionFromFile.trim());

		Position initialPosition = new Position(1, 2, Facing.N);

		Rover rover1 = new Rover("Rover1", initialPosition);

		initialPosition = new Position(3, 3, Facing.E);

		Rover rover2 = new Rover("Rover2", initialPosition);

		plateau.addRover(rover1).addRover(rover2);

		rover1.executeInstruction(instruction, plateau);

		rover1.showPosition();

		String receivedMessage2 = "MMRMMRMRRM";

		instruction = new Instruction(receivedMessage2);

		rover2.executeInstruction(instruction, plateau);

		rover2.showPosition();

	}
}