package java;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.models.Facing;
import org.models.Instruction;
import org.models.Plateau;
import org.models.Position;
import org.models.PositionAlreadyFilledException;
import org.models.PositionOutOfBoundsException;
import org.models.Rover;
import org.models.UnknownCommandException;

public class JUnitTest {

	private Plateau plateau = new Plateau(5, 5);

	@SuppressWarnings("deprecation")
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void moving_rover_one_success_expected() {

		String receivedMessage = "LMLMLMLMM";
		Instruction instruction = new Instruction(receivedMessage);

		Position initialPosition = new Position(1, 2, Facing.N);

		Rover rover1 = new Rover("Rover1", initialPosition);

		plateau.addRover(rover1);

		rover1.executeInstruction(instruction, plateau);

		assertEquals("1 3 N", rover1.getPosition().getActualPosition());
	}

	@Test
	public void moving_rover_two_success_expected() {
		String receivedMessage = "MMRMMRMRRM";
		Instruction instruction = new Instruction(receivedMessage);

		Position initialPosition = new Position(3, 3, Facing.E);

		Rover rover2 = new Rover("Rover2", initialPosition);

		plateau.addRover(rover2);

		rover2.executeInstruction(instruction, plateau);

		assertEquals("5 1 E", rover2.getPosition().getActualPosition());
	}

	@Test
	public void moving_rover_out_plateau_bounds_throw_expected() {

		thrown.expect(PositionOutOfBoundsException.class);
		thrown.expectMessage("1 6 N is out of the plateau bounds");

		String receivedMessage = "MMMMMMMMM";
		Instruction instruction = new Instruction(receivedMessage);

		Position initialPosition = new Position(1, 2, Facing.N);

		Rover rover1 = new Rover("Rover1", initialPosition);

		rover1.executeInstruction(instruction, plateau);
		;
	}

	@SuppressWarnings("unused")
	@Test
	public void unknown_command_throw_expected() {

		thrown.expect(UnknownCommandException.class);
		thrown.expectMessage(
				"X is an invalid command! Use 'R' to rotate right 'L' to rotate left or 'M' to move the rover");

		String receivedMessage = "XMMMMMMMM";
		Instruction instruction = new Instruction(receivedMessage);

	}

	//
	@Test
	public void moving_rover_over_position_occupied_throw_expected() {
		String receivedMessage = "LMLMLMLMM";
		Instruction instruction = new Instruction(receivedMessage);

		Position initialPosition = new Position(1, 2, Facing.N);

		Rover rover1 = new Rover("Rover1", initialPosition);

		initialPosition = new Position(0, 2, Facing.N);
		Rover rover2 = new Rover("Rover2", initialPosition);

		plateau.removeAllRovers();
		plateau.addRover(rover1).addRover(rover2);

		thrown.expect(PositionAlreadyFilledException.class);
		thrown.expectMessage("0 2 W is already filled by a rover");

		rover1.executeInstruction(instruction, plateau);
	}
}