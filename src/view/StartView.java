package view;

import java.io.IOException;

import controller.Controller;

public class StartView {

	public static void main(String[] args) {
		try {
			Controller.startView();
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
			EndView.showError("입력을 확인해주세요");
		}
	}

}