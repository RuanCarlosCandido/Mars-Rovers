package roverTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.controllers.RoverController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.models.Facing;
import org.models.Instruction;
import org.models.Plateau;
import org.models.Position;
import org.models.Rover;
import org.models.exceptions.PositionAlreadyFilledException;
import org.models.exceptions.PositionOutOfBoundsException;
import org.models.exceptions.UnknownCommandException;
import org.services.RoverService;

public class JUnitTest {

	private Plateau plateau = new Plateau(5, 5);

	private RoverService roverService = new RoverService(plateau);
	
	private RoverController roverController = new RoverController();

	@Test
	public void moving_rover_one_success_expected() {

		String receivedMessage = "LMLMLMLMM";
		Instruction instruction = roverService.buildInstruction(receivedMessage);

		Position initialPosition = new Position(1, 2, Facing.N);

		Rover rover1 = new Rover("Rover1", initialPosition);

		plateau.addRover(rover1);

		roverService.executeInstruction(instruction, rover1);

		assertEquals("1 3 N", rover1.getPositionOnPlateau().toString());
	}

	@Test
	public void moving_rover_two_success_expected() {
		String receivedMessage = "MMRMMRMRRM";

		Instruction instruction = roverService.buildInstruction(receivedMessage);

		Position initialPosition = new Position(3, 3, Facing.E);

		Rover rover2 = new Rover("Rover2", initialPosition);

		plateau.addRover(rover2);

		roverService.executeInstruction(instruction, rover2);

		assertEquals("5 1 E", rover2.getPositionOnPlateau().toString());
	}

	@Test
	public void moving_rover_out_plateau_bounds_throw_expected() {

		String receivedMessage = "MMMMMMMMM";
		Instruction instruction = roverService.buildInstruction(receivedMessage);

		Position initialPosition = new Position(1, 2, Facing.N);

		Rover rover1 = new Rover("Rover1", initialPosition);

		assertThrows(PositionOutOfBoundsException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				roverService.executeInstruction(instruction, rover1);
			}
		});

	}

	@Test
	public void unknown_command_throw_expected() {

		String receivedMessage = "XMMMMMMMM";

		assertThrows(UnknownCommandException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				roverService.buildInstruction(receivedMessage);
			}
		});

	}

	//
	@Test
	public void moving_rover_over_position_occupied_throw_expected() {
		String receivedMessage = "LMLMLMLMM";
		Instruction instruction = roverService.buildInstruction(receivedMessage);

		Position initialPosition = new Position(1, 2, Facing.N);

		Rover rover1 = new Rover("Rover1", initialPosition);

		initialPosition = new Position(0, 2, Facing.N);
		Rover rover2 = new Rover("Rover2", initialPosition);

		plateau.removeAllRovers();
		plateau.addRover(rover1).addRover(rover2);

		assertThrows(PositionAlreadyFilledException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				roverService.executeInstruction(instruction, rover1);
			}
		});

	}
	
	@Test
	public void invalid_upper_right_coordinates_throw_expected() {

		try {
			roverController.run(new String[] {"5T"});
		}catch(Exception e) {
			assertEquals(true, e instanceof IOException);
			assertEquals("Invalid upper Right Coordinates: 5T. The first line of input is the upper-right coordinates of the plateau and must have only numeric values", e.getMessage());
		}

	}
	
	@Test
	public void invalid_rover_position_throw_expected() {

		try {
			roverController.run(new String[] {"55","1TT"});
		}catch(Exception e) {
			assertEquals(true, e instanceof IOException);
			assertEquals("Invalid rover position: 1TT. The rover position on the plateau and must have only numeric values and rover orientation", e.getMessage());
		}
	}
	
	@Test
	public void invalid_rover_instruction_throw_expected() {

		try {
			roverController.run(new String[] {"55","12N","MMLL%R"});
		}catch(Exception e) {
			assertEquals(true, e instanceof IOException);
			assertEquals("Invalid rover instruction: MMLL%R. Character: % is invalid, only letters (L, R and M) are accepted", e.getMessage());
		}
	}

}