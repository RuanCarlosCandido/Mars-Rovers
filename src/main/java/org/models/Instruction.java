package org.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Instruction is a series of commands
 * @Ruan
 */
public class Instruction {

	private List<Command> commands = new ArrayList<Command>();

	public Instruction(String message) {

		this.commands = 
				message
				.chars()
				.mapToObj(c -> Command.of((char) c))
				.collect(Collectors.toList());
	}

	public List<Command> getCommands() {
		return commands;
	}

	@Override
	public String toString() {
		return "Instruction =" + commands;
	}

}
