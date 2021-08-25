package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import model.dao.AttractionDAO;
import model.dao.CustomerDAO;
import model.dao.ReservationDAO;
import model.dto.Attraction;
import model.dto.Customer;
import model.dto.Reservation;
import util.PublicCommon;
import view.EndView;

public class Controller {

	/**
	 * 예약정보 하나 불러오기
	 */
	public void getOneReservation() throws SQLException {
		EndView.getReservationList(ReservationDAO.getOneReservation(2l));
	}

	/**
	 * 모든 예약정보 가져오기
	 */
	public void getAllReservation() throws SQLException {
		EndView.getReservationAllList(ReservationDAO.getAllReservations());
	}

	/**
	 * 예약정보 취소하기
	 */
	public void deleteReservation() throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();

		System.out.println("삭제 전 검색해보기");
		Reservation r = em.find(Reservation.class, 1l);
		System.out.println("예약번호 : " + r.getReservationId() + "\n예약인원 : " + r.getMemberCnt() + "\n예약시간 : " + r.getTime());

		ReservationDAO.deleteReservation(r.getReservationId());

		System.out.println("삭제 후 남은 예약리스트 검색해보기");
		List<Reservation> rs = ReservationDAO.getAllReservations();
		rs.stream().forEach(v -> System.out.println("예약번호 : " + v.getReservationId() + "\t예약시간 : " + v.getTime()
				+ "\t예약인원 : " + v.getMemberCnt() + "명"));
	}


	public void insertReservation(Reservation reservation) {
		try {
			ReservationDAO.addReservation(reservation);
		} catch (Exception e) {
			EndView.showError("���ο� ���� ���� ���� �߻�");
		}
	}

	// ��� ���� ��ȸ
	public void getAllReservation2() {
		try {
			EndView.showResListView(ReservationDAO.getAllReservations());
		} catch (Exception e) {
			EndView.showError("��� ���� ��ȸ ���� �߻�");
		}
	}

	// �ϳ��� ���� ��ȸ
	public void getOneReservation(Long reservationId) {
		try {
			EndView.allView(ReservationDAO.getOneReservation(reservationId));
		} catch (Exception e) {
			EndView.showError("���� �˻� ���� �߻�");
		}
	}

	public void updateReservation(Long reservationId, Attraction attraction) {
		try {
			ReservationDAO.updateReservation(reservationId, attraction);

		} catch (Exception e) {
			EndView.showError("��������;��");

		}
	}
	
	public static void startView() {
		try {
			System.out.println("기능 선택 : 1(해림-Attraction)/2(기환-Customer)/3(지원-Reservation)/4(은진-Reservation))");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int a = Integer.parseInt(br.readLine());
			String str = "";
			switch (a) {
			case 1://해림영역
				System.out.println("============Attraction CRUD============");
				System.out.println("C:1/R:2/U:3/D:4");
				a = Integer.parseInt(br.readLine());
				switch (a) {
				case 1:
					System.out.println("놀이기구 이름/놀이기구 위치/키 제한/ 보호자 동반 여부(y or n)");
					str = br.readLine();
					String[] str2 = str.split("/");
					insertAttraction(str2[0], str2[1],Integer.parseInt(str2[2]), str2[3]);
					break;
				case 2:
					selectAllAttraction();
					System.out.println("이름을 입력하면 해당 이름을 가진 놀이기구를 모두 찾음.");
					str = br.readLine();
					findByName(str);
					break;
				case 3:
					System.out.println("id/놀이기구 이름");
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
			case 2://기환영역
				System.out.println("============Customer CRUD============");
				System.out.println("C:1/R:2/U:3/D:4");
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
					System.out.println("이름을 입력하면 해당 이름을 가진 회원 모두 찾음.");
					str = br.readLine();
					findByName(str);
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
			case 3://지원영역
				break;
			case 4://은진영역
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void selectAllAttraction() {
		List<Attraction> all = AttractionDAO.selectAllAttraction();

		for (Attraction attraction : all) {
			System.out.println("놀이기구 번호 : [" + attraction.getAttractionId() + "] 놀이기구 이름 : [" + attraction.getName() + "] 놀이기구 위치 : ["
					+ attraction.getLocation() + "] 키 제한 : [" + attraction.getHeightLimit() + "] 보호자 동반 여부 : "
					+ attraction.getParentYN());
		}
	}

	
	private static void insertAttraction(String name, String location, int heightLimit, String parentYN) {
		Attraction attraction = AttractionDAO.insertAttraction(name, location, heightLimit, parentYN);
		System.out.println(attraction + "삽입 완료");
		
		
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
