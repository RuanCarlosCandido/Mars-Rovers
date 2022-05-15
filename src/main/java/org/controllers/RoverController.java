package org.controllers;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.models.Command;
import org.models.Facing;
import org.models.Instruction;
import org.models.Plateau;
import org.models.Position;
import org.models.Rover;
import org.models.exceptions.PositionAlreadyFilledException;
import org.models.exceptions.PositionOutOfBoundsException;

public class RoverController {

	private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(RoverController.class.getName());

	private Plateau plateau;

	public RoverController() {}
	
	public RoverController(Plateau plateau) {this.plateau = plateau;}

	/*
	 * Expected outPut: Rover1 position = 1 3 N Rover2 position = 5 1 E
	 */
	public void run(String[] args) throws Exception {

		String[] uppRightCoord = args[0].split("");

		String[] rover1Position = args[1].split("");

		String rover1Instruction = args[2];

		String[] rover2Position = args[3].split("");

		String rover2Instruction = args[4];

		validateFirstLine(uppRightCoord);
		validateSecondLine(rover1Position);
		validateThirdLine(rover1Instruction);
		validateFourthhLine(rover2Position);
		validateFifthLine(rover2Instruction);

		buildPlateau(uppRightCoord);

		Rover rover1 = new Rover("Rover1", buildPosition(rover1Position));

		Rover rover2 = new Rover("Rover2", buildPosition(rover2Position));

		plateau.addRover(rover1).addRover(rover2);

		executeInstruction(buildInstruction(rover1Instruction), rover1);

		executeInstruction(buildInstruction(rover2Instruction), rover2);

		System.out.println(plateau.toString());

	}

	public Instruction buildInstruction(String message) {
		LOGGER.info("building a instruction from message: " + message);

		return new Instruction(message.chars().mapToObj(c -> Command.of((char) c)).collect(Collectors.toList()));
	}

	/**
	 * @param roverPosition
	 * @return
	 * @throws NumberFormatException
	 */
	public Position buildPosition(String[] roverPosition) throws NumberFormatException {
		LOGGER.info("building a position from rover position: " + Arrays.asList(roverPosition).toString());

		return new Position(Integer.valueOf(roverPosition[0]), Integer.valueOf(roverPosition[1]),
				Facing.valueOf(roverPosition[2]));
	}

	/**
	 * @param uppRightCoord
	 * @throws NumberFormatException
	 */
	public void buildPlateau(String[] uppRightCoord) throws NumberFormatException {
		LOGGER.info("building the plateau from the upper Right Coordinate: " + Arrays.asList(uppRightCoord).toString());

		plateau = new Plateau(Integer.valueOf(uppRightCoord[0]), Integer.valueOf(uppRightCoord[1]));
	}

	/**
	 * Make the rover move for each command in the instruction
	 * 
	 * @param instruction
	 */
	public void executeInstruction(Instruction instruction, Rover rover) {

		LOGGER.info("executing Instruction: " + instruction + " from " + rover.getIdentification());

		Position positionOnPlateau = rover.getPositionOnPlateau();

		instruction.getCommands().forEach(command -> {

			if (positionOnPlateau.isOutOfBounds(plateau))
				throw new PositionOutOfBoundsException(positionOnPlateau);

			if (positionOnPlateau.isPositionFilled(plateau, rover.getIdentification()))
				throw new PositionAlreadyFilledException(positionOnPlateau);

			else
				rover.getPositionOnPlateau().move(command);
		});
	}

	public void showPosition(Rover rover) {
		LOGGER.info(rover.getIdentification() + " position = " + rover.getPositionOnPlateau().getActualPosition());
	}

	private void validateFifthLine(String rover2Instruction) {
		// TODO Auto-generated method stub

	}

	private void validateFourthhLine(String[] rover1Position) {
		// TODO Auto-generated method stub

	}

	private void validateThirdLine(String rover1Instruction) {
		// TODO Auto-generated method stub

	}

	private void validateSecondLine(String[] rover1Position) {
		// TODO Auto-generated method stub

	}

	private void validateFirstLine(String[] uppRightCoord) throws Exception {
		if (!Character.isDigit((uppRightCoord[0].charAt(0))) || !Character.isDigit((uppRightCoord[0].charAt(0))))
			throw new Exception(
					"Invalid upperRightCoordinates. The first line of input is the upper-right coordinates of the plateau");
	}
}
