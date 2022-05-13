package roverTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
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

		String receivedMessage = "MMMMMMMMM";
		Instruction instruction = new Instruction(receivedMessage);

		Position initialPosition = new Position(1, 2, Facing.N);

		Rover rover1 = new Rover("Rover1", initialPosition);

		assertThrows(PositionOutOfBoundsException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				rover1.executeInstruction(instruction, plateau);
			}
		});

	}

	@Test
	public void unknown_command_throw_expected() {

		String receivedMessage = "XMMMMMMMM";

		assertThrows(UnknownCommandException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				new Instruction(receivedMessage);
			}
		});

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

		assertThrows(PositionAlreadyFilledException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				rover1.executeInstruction(instruction, plateau);
			}
		});

	}
}