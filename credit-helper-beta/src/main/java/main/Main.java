package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.login.LoginController;

public class Main {
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		logger.info("Application started");
		
		LoginController login = new LoginController();
		login.init();
		
		logger.info("Application finished");
	}

}
