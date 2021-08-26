package view;

import java.util.List;

import model.dto.Attraction;
import model.dto.Customer;
import model.dto.Reservation;

public class EndView {
	public static void showAttrListView(List<Attraction> attractionList) {
		
	}
	

	public static void showResListView(List<Reservation> reservationList) {
		reservationList.forEach(v -> System.out.println("=============\n" + "- 예약 번호 : " + v.getReservationId()
				+ "\n- 놀이기구 이름 : " + v.getAttraction().getName() + "\n- 예약 인원 수 : " + v.getMemberCnt()
				+ "\n- 예약자 이름 : " + v.getCustomer().getName()
				+ "\n- 예약 시간 : " + v.getTime()));
	}
	
	public static void showCusListView(List<Customer> customerList) {
		for (Customer customer : customerList) {
			System.out.println("- 회원번호 : " + customer.getCustomerId() + "\n- 회원이름 : " + customer.getName() + "\n- 키 : "
					+ customer.getHeight() + "\n- cm 알림동의여부 : " + customer.getAlarmYN() + "\n- 예약 내역 : "
					+ customer.getReservations());
		}
	}
	
	public static void showAttListView(List<Attraction> attractionList) {
		for (Attraction attraction : attractionList) {
			System.out.println("\n- 놀이기구 번호 : " + attraction.getAttractionId() + "\n- 놀이기구 이름 : " + attraction.getName() + "\n- 놀이기구 위치 : "
					+ attraction.getLocation() + "\n- 키 제한 : " + attraction.getHeightLimit() + "\n- 보호자 동반 여부 : "
					+ attraction.getParentYN());
		}
	}

	public static void allView(Object data) {
		if (data != null) {
			System.out.println(data);
		} else {
			System.out.println("해당 정보가 존재하지 않습니다.");
		}
	}
	
	public static void msgView(String msg) {
		System.out.println(msg);
	}

	public static void showError(String msg) {
		System.out.println(msg);
	}
}
