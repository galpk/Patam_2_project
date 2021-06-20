package test;

import test.Commands.Command;
import test.Commands.DefaultIO;

import java.util.ArrayList;

public class CLI {

	ArrayList<Command> commands;
	DefaultIO dio;
	Commands c;
	
	public CLI(DefaultIO dio) {
		this.dio=dio;
		c = new Commands(dio);
		commands=new ArrayList<>();
		commands.add(c.new MenuCommand());

	}
	
	public void start() {
		this.commands.get(0).execute();
	}

}
