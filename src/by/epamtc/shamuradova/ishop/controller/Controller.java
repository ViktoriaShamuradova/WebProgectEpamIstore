package by.epamtc.shamuradova.ishop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.shamuradova.ishop.controller.command.Command;
import by.epamtc.shamuradova.ishop.controller.command.CommandProvider;

public class Controller extends HttpServlet{
	
	private final CommandProvider provider = new CommandProvider();
	private final static String COMMAND_NAME = "command";
	
	public Controller() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String current_command = req.getParameter(COMMAND_NAME);
		Command command = provider.getCommand(current_command);
		command.execute(req, resp);

	}

}
