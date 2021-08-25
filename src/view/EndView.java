package view;

import java.util.List;

import model.dto.Reservation;

public class EndView {
	public static void showResListView(List<Reservation> reservationList) {
		reservationList.forEach(v -> System.out.println("=============\n" + "- ���� ID : " + v.getReservationId()
				+ "\n- ���̱ⱸ �̸� : " + v.getAttraction().getName() + "\n- �ο��� : " + v.getMemberCnt()
				+ "\n- ��� ���� ���� : " + v.getCancelYN() + "\n- ������ �̸�: " + v.getCustomer().getName()
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
