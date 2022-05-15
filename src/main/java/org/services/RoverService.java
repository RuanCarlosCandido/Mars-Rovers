package org.services;

import static org.models.Command.Left;
import static org.models.Command.Move;
import static org.models.Command.Right;
import static org.models.Facing.E;
import static org.models.Facing.N;
import static org.models.Facing.S;
import static org.models.Facing.W;

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

public class RoverService {
	
	private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(RoverService.class.getName());
	
	private Plateau plateau;
	
	public RoverService(){}
	
	public RoverService(Plateau plateau){this.plateau = plateau;}
	
	public void run(String[] args) {
		
		String[] uppRightCoord = args[0].split("");

		String[] rover1Position = args[1].split("");

		String rover1Instruction = args[2];

		String[] rover2Position = args[3].split("");

		String rover2Instruction = args[4];
		
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

			if (isOutOfBounds(positionOnPlateau))
				throw new PositionOutOfBoundsException(positionOnPlateau);

			if (isPositionFilled(rover))
				throw new PositionAlreadyFilledException(positionOnPlateau);

			else
				move(rover.getPositionOnPlateau(), command);
		});
	}

	public void move(Position position, Command command) {

		Facing facing = position.getFacing();
		int x = position.getX();
		int y = position.getY();

		if (command.equals(Left)) {
			if (facing.equals(N))
				position.setFacing(W);
			else if (facing.equals(E))
				position.setFacing(N);
			else if (facing.equals(W))
				position.setFacing(S);
			else if (facing.equals(S))
				position.setFacing(E);

		}
		if (command.equals(Right)) {
			if (facing.equals(N))
				position.setFacing(E);
			else if (facing.equals(E))
				position.setFacing(S);
			else if (facing.equals(W))
				position.setFacing(N);
			else if (facing.equals(S))
				position.setFacing(W);

		}
		if (command.equals(Move)) {
			if (facing.equals(N))
				y++;
			else if (facing.equals(E))
				x++;
			else if (facing.equals(W))
				x--;
			else if (facing.equals(S))
				y--;

			position.setX(x);
			position.setY(y);

		}
	}

	public boolean isOutOfBounds(Position position) {

		int x = position.getX();
		if (x < 0 || x > plateau.limitX)
			return true;

		int y =  position.getY();
		if (y < 0 || y > plateau.limitY)
			return true;

		return false;
	}

	public boolean isPositionFilled(Rover paramRover) {

		return plateau
				.getRovers()
				.stream()
				.filter(anyRover -> !anyRover
						.getIdentification()
						.equals(paramRover.getIdentification()))
				.anyMatch(anyRover -> anyRover
						.getPositionOnPlateau()
						.equals(paramRover.getPositionOnPlateau()));
	}

	public void showPosition(Rover rover) {
		LOGGER.info(rover.getIdentification() + " position = " + rover.getPositionOnPlateau().toString());
	}
}
