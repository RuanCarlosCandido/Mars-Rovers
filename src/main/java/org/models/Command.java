package org.models;

import java.util.HashMap;
import java.util.Map;

import org.models.exceptions.UnknownCommandException;

/**
 * A command make the rover do an action
 * @Ruan
 */
public enum Command{ 

	Move('M'),
    Left('L'),
    Right('R');

    private static final Map<Character, Command> LOOKUP = new HashMap<>();

    private final Character key;

    Command(final Character key) {
        this.key = key;
    }

    static {
    	
        for (final Command i : Command.values())
            LOOKUP.put(i.key, i);
    }

    public static Command of(final Character key) throws UnknownCommandException{
        final Command command = LOOKUP.get(key);
       
        if (command == null)
            throw new UnknownCommandException(key);
        
        return command;
    }

}
	
	
