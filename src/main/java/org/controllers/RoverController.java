package org.controllers;

import java.io.IOException;

import org.services.RoverService;

public class RoverController {
	/*
	 * Expected outPut: Rover1 position = 1 3 N Rover2 position = 5 1 E
	 */
	public void run(String[] args) throws Exception {

		validateInput(args);

		new RoverService().run(args);
	}

	private void ValidateRoverInstruction(char[] rover1Instruction) throws IOException {
		for (char c : rover1Instruction)
			if (!isLetter(c))
				throw new IOException("Invalid rover instruction: " + String.valueOf(rover1Instruction)
						+ ". Character: " + c + " is invalid, only letters (L, R and M) are accepted");
	}

	private void validateRoverPosition(char[] rover1Position) throws IOException {
		if (!isDigit(rover1Position[0]) || !isDigit(rover1Position[1]) || !isLetter(rover1Position[2]))
			throw new IOException("Invalid rover position: " + String.valueOf(rover1Position)
					+ ". The rover position on the plateau and must have only numeric values and rover orientation");

	}

	/**
	 * @param c
	 * @return
	 */
	private boolean isLetter(char c) {
		return Character.isLetter(c);
	}

	private void validateUpperRightCoordinates(char[] uppRightCoord) throws IOException {
		if (!isDigit(uppRightCoord[0]) || !isDigit(uppRightCoord[1]))
			throw new IOException("Invalid upper Right Coordinates: " + String.valueOf(uppRightCoord)
					+ ". The first line of input is the upper-right coordinates of the plateau and must have only numeric values");
	}

	/**
	 * @param char
	 * @return
	 */
	private boolean isDigit(char c) {
		return Character.isDigit(c);
	}

	/**
	 * @param uppRightCoord
	 * @throws Exception
	 */
	private void validateInput(String[] args) throws Exception {

		try {
			validateUpperRightCoordinates(args[0].trim().toCharArray());
			validateRoverPosition(args[1].trim().toCharArray());
			ValidateRoverInstruction(args[2].trim().toCharArray());
			validateRoverPosition(args[3].trim().toCharArray());
			ValidateRoverInstruction(args[4].trim().toCharArray());
		} catch (Exception e) {
			if (e instanceof IOException)
				throw e;

			throw new Exception("Input file is invalid, please read the documentation to send a correct information.");
		}
	}
}
