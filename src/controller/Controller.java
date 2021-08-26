/**
 * PROJECT : 놀이기구 사전 예약 프로그램
 * NAME : Controller.java
 * DESC : 사용자로 부터 받은 요청을 처리하는 클래스
 * 		  Model 갱신 및 View 제어
 * 
 * @author  최해림, 정은진(B), 이기환, 방지원
 * @version 1.0
 */
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import model.dao.AttractionDAO;
import model.dao.CustomerDAO;
import model.dao.ReservationDAO;
import model.dto.Attraction;
import model.dto.Customer;
import model.dto.Reservation;
import view.EndView;

public class Controller {
	
	/**
	 * 고객 이름으로 정보 조회
	 * @param name
	 */
	public static void getOneCustomer(String name) {
		List<Customer> all = CustomerDAO.getOneCustomer(name);
		all.stream().forEach(v -> System.out.println(v));
	}

	/**
	 * 고객 정보 수정
	 * @param id, name
	 */
	public static void updateCustomer(Long id, String name) {
		CustomerDAO.updateCustomerName(id, name);
	}

	/**
	 * 고객 정보 삭제
	 * @param id
	 */
	public static void deleteCustomer(Long id) {
		CustomerDAO.deleteCustomer(id);
	}

	/**
	 * 놀이기구 정보 추가
	 * @param name, location, heightLimit, parentYN
	 */
	private static void addAttraction(String name, String location, int heightLimit, String parentYN) {
		Attraction attraction = AttractionDAO.addAttraction(name, location, heightLimit, parentYN);
		System.out.println(attraction + "삽입 완료");
	}

	/**
	 * 모든 놀이기구 조회
	 */
	public static void getAllAttractions() {
		List<Attraction> all = AttractionDAO.getAllAttraction();
		for (Attraction attraction : all) {
			System.out.println("놀이기구 번호 : [" + attraction.getAttractionId() + "] 놀이기구 이름 : [" + attraction.getName() + "] 놀이기구 위치 : ["
					+ attraction.getLocation() + "] 키 제한 : [" + attraction.getHeightLimit() + "] 보호자 동반 여부 : "
					+ attraction.getParentYN());
		}
	}
	
	/**
	 * 놀이기구 이름으로 정보 조회
	 * @param name
	 */
	public static void getOneAttraction(String name) {
		List<Attraction> all = AttractionDAO.getOneAttraction(name);
		all.stream().forEach(v -> System.out.println(v));
	}
	
	/**
	 * 고유 id값을 받아 놀이기구 제한 신장(키) 수정
	 * @param id, heightLimit
	 */
	public static void updateAttraction(Long id,int heightLimit) {
		AttractionDAO.updateAttractionHeightLimit(id, heightLimit);
	}
	
	/**
	 * 고유 id값으로 놀이기구 삭제
	 * @param id
	 */
	public static void deleteAttraction(Long id) {
		AttractionDAO.deleteAttraction(id);
	}
	
	/**
	 * 신규 고객 추가
	 * @param name, height, yn
	 */
	public static void addCustomer(String name, int height, String yn) {
		Customer customer = CustomerDAO.addCustomer(name, height, yn);
		System.out.println(customer + "삽입 완료");
	}

	/**
	 * 모든 고객 정보 조회
	 */
	public static void getAllCustomers() {
		List<Customer> all = CustomerDAO.getAllCustomer();
		for (Customer customer : all) {
			System.out.println("회원번호 : " + customer.getCustomerId() + " 회원이름 : " + customer.getName() + " 키 : "
					+ customer.getHeight() + "cm 알림동의여부 : " + customer.getAlarmYN() + "에약 내역 : "
					+ customer.getReservations());
		}
	}
	
	/**
	 * 신규 예약 추가
	 * @param reservation
	 */
	public static void addReservation(Long attractionId, Long customerId, String time, int cnt) {
		try {
			boolean r = ReservationDAO.addReservation(attractionId, customerId, time, cnt);
			if (r == true) {
				EndView.msgView("새로운 예약이 저장되었습니다");
			} else {
				EndView.msgView("키 제한을 확인해주세요");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			EndView.showError("오류가 발생했습니다");
		}
	}

	/**
	 * 모든 예약 정보 조회하기
	 */
	public static void getAllReservations() {
		List<Reservation> resList = ReservationDAO.getAllReservation();
		if (resList.size() != 0) {
			try {
				EndView.showResListView(resList);
			} catch (NullPointerException e) {
				e.printStackTrace();
				EndView.showError("오류가 발생했습니다");
			}
		} else {
			System.out.println("리스트가 비어있습니다.");
		}
	}

	/**
	 * 고유 id로 예약정보 조회
	 * @param id
	 */
	public static void getOneReservation(Long reservationId) {
		Reservation reservation = ReservationDAO.getOneReservation(reservationId);
		if (reservation != null) {
			try {
				EndView.allView(ReservationDAO.getOneReservation(reservationId));
			} catch (Exception e) {
				e.printStackTrace();
				EndView.showError("오류가 발생했습니다");
			}
		} else {
			EndView.msgView("해당 예약은 존재하지 않습니다");
		}
	}

	/**
	 * 고유 id로 예약시간 수정하기
	 * @param id, attraction
	 */
	public static void updateReservation(Long reservationId, String time) {
		Reservation reservation = ReservationDAO.getOneReservation(reservationId);
		if (reservation != null) {
			try {
				ReservationDAO.updateReservation(reservationId, time);
			} catch (Exception e) {
				e.printStackTrace();
				EndView.showError("오류가 발생했습니다");
			}
		} else {
			EndView.msgView("해당 예약은 존재하지 않습니다.");
		}
	}

	/**
	 * 고유 id로 예약 취소하기
	 * @param id
	 */
	public static void deleteReservation(Long reservId) {
		System.out.println("삭제 전 검색해보기");
		EndView.allView(ReservationDAO.getOneReservation(reservId));
		ReservationDAO.deleteReservation(reservId);

		System.out.println("삭제 후 남은 예약리스트 검색해보기");
		EndView.showResListView(ReservationDAO.getAllReservation());
	}

	
	/**
	 * 실행 메뉴
	 * @throws IOException
	 */
	public static void startView() throws IOException, NumberFormatException {
		System.out.println("기능 선택 : 놀이기구 관련(1) / 고객 관련(2) / 예약 관련(3)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			int a = Integer.parseInt(br.readLine());
			String str = "";

			switch (a) {
			case 1:
				System.out.println("============Attraction CRUD============");
				System.out.println("신규 놀이기구 추가(1)/특정 놀이기구 정보조회(2)/놀이기구  제한 신장(키) 수정(4)/놀이기구 정보 삭제(5)");
				a = Integer.parseInt(br.readLine());
				switch (a) {
				case 1:
					System.out.println("놀이기구 이름/놀이기구 위치/키 제한/ 보호자 동반 여부(y or n)");
					str = br.readLine();
					String[] str2 = str.split("/");
					addAttraction(str2[0], str2[1], Integer.parseInt(str2[2]), str2[3]);
					break;
				case 2:
					getAllAttractions();
					System.out.println("이름을 입력하면 해당 이름을 가진 놀이기구를 모두 찾음.");
					str = br.readLine();
					getOneAttraction(str);
					break;
				case 3:
					System.out.println("id/수정 후 제한 키");
					str = br.readLine();
					String[] str3 = str.split("/");
					updateAttraction(Long.parseLong(str3[0]), Integer.parseInt(str3[1]));
					break;
				case 4:
					System.out.println("id");
					Long id = Long.parseLong(br.readLine());
					deleteAttraction(id);
					break;
				}
				break;
			case 2:
				System.out.println("신규 고객 추가(1)/특정 고객 정보조회(2)/고객 이름 수정(4)/고객 정보 삭제(5)");
				a = Integer.parseInt(br.readLine());
				switch (a) {
				case 1:
					System.out.println(" 이름 / 키 / 알람여부(y or n) ");
					str = br.readLine();
					String[] str2 = str.split("/");
					addCustomer(str2[0], Integer.parseInt(str2[1]), str2[2]);
					break;
				case 2:
					getAllCustomers();
					System.out.println("이름을 입력하면 해당 이름을 가진 회원 정보 모두 조회");
					str = br.readLine();
					getOneCustomer(str);
					break;
				case 3:
					System.out.println("id / 수정 후 이름");
					str = br.readLine();
					String[] str3 = str.split("/");
					updateCustomer(Long.parseLong(str3[0]), str3[1]);
					break;
				case 4:
					System.out.println("id");
					Long id = Long.parseLong(br.readLine());
					deleteCustomer(id);
					break;
				}
				break;
			case 3:
				System.out.println("예약 신청(1)/모든 예약조회(2)/특정 예약 정보조회(3)/예약 시간 수정(4)/예약 취소(5)");
				a = Integer.parseInt(br.readLine());
				switch (a) {
				case 1: 
					System.out.println("놀이기구 id/ 고객 id/ 시간/ 인원 수");
					str = br.readLine();
					String[] str1 = str.split("/");
					addReservation(Long.parseLong(str1[0]), Long.parseLong(str1[1]), str1[2],
							Integer.parseInt(str1[3]));
					break;
				case 2:
					getAllReservations();
					break;
				case 3:
					System.out.println("예약번호입력");
					str = br.readLine();
					String[] str2 = str.split("/");
					getOneReservation(Long.parseLong(str2[0]));
					break;
				case 4:
					System.out.println("id / 수정 후 예약 시간");
					str = br.readLine();
					String[] str3 = str.split("/");
					updateReservation(Long.parseLong(str3[0]), str3[1]);
					break;
				case 5: 
					System.out.println("id");
					Long id = Long.parseLong(br.readLine());
					deleteReservation(id);
					break;
				}
			}
		} catch (IOException e) {
			EndView.showError("호에에에엥");
		} finally {
			br.close();
		}
	}

}