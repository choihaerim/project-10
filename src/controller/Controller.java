package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import model.dao.CustomerDAO;
import model.dao.ReservationDAO;
import model.dto.Attraction;
import model.dto.Customer;
import model.dto.Reservation;
import util.PublicCommon;
import view.EndView;

public class Controller {
	/**
	 * 예약 추가하기
	 * 
	 * @param reservation
	 */
	public static void insertReservation(Long attractionId, Long customerId, String time, int cnt) {
		try {
			ReservationDAO.addReservation(attractionId, customerId, time, cnt);
		} catch (Exception e) {
			EndView.showError("오류가 발생했습니다");
		}
	}

	/**
	 * 모든 예약 정보 조회하기
	 */
	public static void getAllReservation() {
		try {
			EndView.showResListView(ReservationDAO.getAllReservation());
		} catch (Exception e) {
			EndView.showError("오류가 발생했습니다");
		}
	}

	/**
	 * 예약 정보 하나 조회하기
	 * 
	 * @param reservationId
	 */
	public static void getOneReservation(Long reservationId) {
		try {
			EndView.allView(ReservationDAO.getOneReservation(reservationId));
		} catch (Exception e) {
			EndView.showError("오류가 발생했습니다");
		}
	}

	/**
	 * 예약 수정하기
	 * 
	 * @param reservationId, attraction
	 */
	public static void updateReservation(Long reservationId, String time) {
		try {
			ReservationDAO.updateReservation(reservationId, time);
		} catch (Exception e) {
			EndView.showError("오류가 발생했습니다");
		}
	}

	/**
	 * 예약정보 취소하기
	 * 
	 * @throws SQLException
	 */
	public static void deleteReservation(Long reservId) {

		System.out.println("삭제 전 검색해보기");
		Reservation r = ReservationDAO.getOneReservation(reservId);
		System.out
				.println("예약번호 : " + r.getReservationId() + "\n예약인원 : " + r.getMemberCnt() + "\n예약시간 : " + r.getTime());

		ReservationDAO.deleteReservation(reservId);

		System.out.println("삭제 후 남은 예약리스트 검색해보기");
		List<Reservation> rs = ReservationDAO.getAllReservation();
		rs.stream().forEach(v -> System.out.println(
				"예약번호 : " + v.getReservationId() + "\t예약시간 : " + v.getTime() + "\t예약인원 : " + v.getMemberCnt() + "명"));
	}

	/**
	 * 시작 메뉴
	 * 
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	public static void startView() throws IOException{
		System.out.println("기능 선택 : 1(해림-Attraction)/2(기환-Customer)/3(지원,은진-Reservation))");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			int a = Integer.parseInt(br.readLine());
			String str = "";

			switch (a) {
			case 1:// 해림영역
				break;
			case 2:// 기환영역
				a = Integer.parseInt(br.readLine());
				switch (a) {
				case 1:
					System.out.println("이름/키/알람여부(y or n)");
					str = br.readLine();
					String[] str2 = str.split("/");
					insertCustomer(str2[0], Integer.parseInt(str2[1]), str2[2]);
					break;
				case 2:
					selectAllCustomer();
//					System.out.println("이름");
//					str = br.readLine();
//					findByName(str);
					break;
				case 3:
					System.out.println("id/이름");
					str = br.readLine();
					String[] str3 = str.split("/");
					updateName(Long.parseLong(str3[0]), str3[1]);
					break;
				case 4:
					System.out.println("id");
					Long id = Long.parseLong(br.readLine());
					deleteById(id);
					break;
				}
				break;
			case 3:// 은진지원영역
				a = Integer.parseInt(br.readLine());
				switch (a) {
				case 1: // 예약 추가하기??
					System.out.println("놀이기구 id/ 고객 id/ 시간/ 인원 수");
					str = br.readLine();
					String[] str1 = str.split("/");
					insertReservation(Long.parseLong(str1[0]), Long.parseLong(str1[1]), str1[2],
							Integer.parseInt(str1[3]));
					break;
				case 2: // 모든 예약 조회하기 -> 관리자 입장
					getAllReservation();
					break;
				case 3: // 예약 정보 하나 조회하기 -> 사용자 입장
					System.out.println("id/이름");
					str = br.readLine();
					String[] str2 = str.split("/");
					getOneReservation(Long.parseLong(str2[0]));
					break;
				case 4: // 예약 수정하기
					System.out.println("id");
					str = br.readLine();
					String[] str3 = str.split("/");
					updateReservation(Long.parseLong(str3[0]), str3[1]);
					break;
				case 5: // 예약 취소하기
					System.out.println("id");
					Long id = Long.parseLong(br.readLine());
					deleteReservation(id);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
	}

	public static void insertCustomer(String name, int height, String yn) {
		Customer customer = CustomerDAO.insertCustomer(name, height, yn);
		System.out.println(customer + "삽입 완료");
	}

	public static void selectAllCustomer() {
		List<Customer> all = CustomerDAO.selectAllCustomer();

		for (Customer customer : all) {
			System.out.println("회원번호 : " + customer.getCustomerId() + " 회원이름 : " + customer.getName() + " 키 : "
					+ customer.getHeight() + "cm 알림동의여부 : " + customer.getAlarmYN() + "에약 내역 : "
					+ customer.getReservations());
		}
	}

	public static void findByName(String name) {
		List<Customer> all = CustomerDAO.findByName(name);
		all.stream().forEach(v -> System.out.println(v));
	}

	public static void updateName(Long id, String name) {
		CustomerDAO.updateName(id, name);
	}

	public static void deleteById(Long id) {
		CustomerDAO.deleteById(id);
	}

}
