package org.models;

import java.util.ArrayList;
import java.util.List;

/**
 * An Instruction is a series of commands
 * 
 * @Ruan
 */
public class Instruction {

	private List<Command> commands = new ArrayList<Command>();

	public Instruction(List<Command> commands) {
		this.commands = commands;
	}

	public List<Command> getCommands() {
		return commands;
	}

	@Override
	public String toString() {
		return "Instruction =" + commands;
	}

}
