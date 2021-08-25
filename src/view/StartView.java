package view;

import java.io.IOException;

import controller.Controller;

public class StartView {

	public static void main(String[] args) {
		try {
			Controller.startView();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}