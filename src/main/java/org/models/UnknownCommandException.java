package org.models;

/**
 * Throws when a Instruction contains commands different from L - R - M
 * @Ruan
 */
public class UnknownCommandException extends RuntimeException {


	private static final long serialVersionUID = -8222799826494332271L;

	public UnknownCommandException(char command) {
		super(command + " is an invalid command! Use 'R' to rotate right"
				+ " 'L' to rotate left or 'M' to move the rover");
	}
	
}
