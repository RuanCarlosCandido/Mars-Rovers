package org;

import org.apache.log4j.LogManager;
import org.controllers.RoverController;
import org.util.Util;

public class Application {
	private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(Application.class.getName());

	public static void main(String[] args) throws Exception {

		try {
			args = Util.readFile().toArray(new String[] {});
			new RoverController().run(args);
		} catch (Exception e) {
			LOGGER.fatal("ERROR: " + e.getMessage());
			throw e;
		}

	}

}
