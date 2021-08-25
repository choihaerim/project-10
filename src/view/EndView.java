package view;

import java.util.List;

import model.dto.Reservation;

public class EndView {

	/**
	 * 예약정보 하나 불러오기
	 */
	public static void getReservationList(Reservation oneReservation) {
		System.out.println("||" + "예약번호 : " + oneReservation.getReservationId() + "||" + "예약인원 : "
				+ oneReservation.getMemberCnt() + "명" + "||" + "예약시간 : " + oneReservation.getTime() + "||"
				+ "||");
	}

	/**
	 * 모든 예약정보 가져오기
	 */
	public static void getReservationAllList(List<Reservation> allReservation) {
		allReservation.stream().forEach(v -> System.out.println("||" + "예약번호 : " + v.getReservationId() + "||"
				+ "예약시간 : " + v.getTime() + "||" + "예약인원 : " + v.getMemberCnt() + "명" + "||" 
				+ "||" + "예약 고객 이름 : " + v.getCustomer().getName() + "||" + "예약 고객 신장 : "
				+ v.getCustomer().getHeight() + "||" + "예약 고객 알림 허용 여부 : " + v.getCustomer().getAlarmYN() + "||"));
	}

	public static void showResListView(List<Reservation> reservationList) {
		reservationList.forEach(v -> System.out.println("=============\n" + "- ���� ID : " + v.getReservationId()
				+ "\n- ���̱ⱸ �̸� : " + v.getAttraction().getName() + "\n- �ο��� : " + v.getMemberCnt()
				+ "\n- ������ �̸�: " + v.getCustomer().getName()
				+ "\n- ���� �ð�: " + v.getTime()));
	}

	public static void allView(Object data) {
		if (data != null) {
			System.out.println(data);
		} else {
			System.out.println("������ �������� �ʽ��ϴ�");
		}
	}

	public static void showError(String msg) {
		System.out.println(msg);
	}
}