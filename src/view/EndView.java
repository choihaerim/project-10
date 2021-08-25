package view;

import java.util.List;

import model.dto.Reservation;

public class EndView {

	public static void showResListView(List<Reservation> reservationList) {
		reservationList.forEach(v -> System.out.println("=============\n" + "- 예약 ID : " + v.getReservationId()
				+ "\n- 놀이기구 이름 : " + v.getAttraction().getName() + "\n- 예약 인원 수 : " + v.getMemberCnt()
				+ "\n- 예약자 이름 : " + v.getCustomer().getName()
				+ "\n- 예약 시간 : " + v.getTime()));
	}

	public static void allView(Object data) {
		if (data != null) {
			System.out.println(data);
		} else {
			System.out.println("해당 정보가 존재하지 않습니다.");
		}
	}

	public static void showError(String msg) {
		System.out.println(msg);
	}
}
