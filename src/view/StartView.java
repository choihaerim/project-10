/**
 * PROJECT : 놀이기구 사전 예약 프로그램
 * NAME : StartView.java
 * DESC : 실행 클래스
 * 
 * @author  최해림, 정은진(B), 이기환, 방지원
 * @version 1.0
 */
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