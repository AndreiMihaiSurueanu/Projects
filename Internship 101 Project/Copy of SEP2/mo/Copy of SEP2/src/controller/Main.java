package controller;

import java.sql.Connection;
import java.sql.SQLException;

import storage.DB;
import view.FRAMEopenDownloadFilesJ;
import view.FrameForApply;

public class Main {
	public static void main(String[] args) throws SQLException {

		DB dataBaseConnection = new DB(); // first the server

		Controller startProgram = new Controller(dataBaseConnection);
		startProgram.startProgram();

	}

}
