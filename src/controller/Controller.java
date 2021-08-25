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
		System.out.println("예약번호 : " + r.getReservationId() + "\n예약인원 : " + r.getMemberCnt() + "\n예약시간 : " + r.getTime()
				+ "\n예약 취소 가능 여부 : ");

		ReservationDAO.deleteReservation(r.getReservationId());

		System.out.println("삭제 후 남은 예약리스트 검색해보기");
		List<Reservation> rs = ReservationDAO.getAllReservations();
		rs.stream().forEach(v -> System.out.println("예약번호 : " + v.getReservationId() + "\t예약시간 : " + v.getTime()
				+ "\t예약인원 : " + v.getMemberCnt() + "명"));
	}

	/**
	 * 시작 메뉴
	 */
//	public static void startMenu() {
//		System.out.println("***** 꿈과 환상의 나라 EVERLAND에 오신걸 환영합니다! *****");
//		int choice = -1;
//		Scanner sc = new Scanner(new InputStreamReader(System.in));
//
//		while (true) {
////			System.out.println("\n0.메뉴 종료하기 \t1.모든 예약 조회하기  \t2.모델명으로 검색   \t3.차종으로 검색 \t4.브랜드로 검색"
////					+ "\n5.대여 가능 차량 조회 \t6.차량 대여하기 \t7.차량 반납하기 \t8.예약 조회하기 \t9.고객 등록"
////					+ "\n\n[관리자 메뉴]\n10.차량 추가  \t11.차량 삭제 \t12.모든 대여 내역 조회");
//			System.out.println("\n0.메뉴 종료하기 \t1.모든 예약 조회하기  \t2.예약번호로 검색 \t3.예약자 이름??으로 검색 "
//					+ "\n4.예약 가능 놀이기구 조회 \t5.예약 추가하기 \t6.예약 수정하기 \t7.예약 취소하기 "
//					+ "\n\n[관리자 메뉴]\n8.모든 예약 정보 조회  \t9.모든 놀이기구 정보 조회 \t10.모든 고객 정보 조회");
//			System.out.println("\n메뉴를 선택해주세요.");
//
//			try {
//				choice = Integer.parseInt(sc.next());
//			} catch (NumberFormatException e) {
//				System.out.println("숫자로 입력해주세요.");
//			}
//
//			if (choice == 0) {
//				System.out.println("\n이용해 주셔서 감사합니다.");
//				break;
//
//			} else if (choice == 1) {
////				getAllCar();
//
//			} else if (choice == 2) {
//				System.out.println("검색하실 예약번호를 입력해주세요. ex) 예약번호L");
////				Long reservationId = sc.next();
////				getCarModelList(reservationId);
//
//			} else if (choice == 3) {
//				System.out.println("검색하실 예약자 성함을 입력해주세요.");
//				String name = sc.next();
////				getCarTypeList(name);
//
//			} else if (choice == 4) {
////				getCarisRentList();
//
//			} else if (choice == 6) {
//				String endDay = null;
//				int customerId = 0;
//				int carId = 0;
//				System.out.println("반납할 날짜를 입력하세요. (yyyy-mm-dd 형태로 입력)");
//				endDay = sc.next();
//				System.out.println("고객번호를 입력하세요.");
//				try {
//					customerId = Integer.parseInt(sc.next());
//					System.out.println("차량번호를 입력하세요.");
//					try {
//						carId = Integer.parseInt(sc.next());
////						addRentList(new RentDTO(endDay, customerId, carId));
//					} catch (NumberFormatException e) {
//						e.printStackTrace();
////						RunningEndView.showError("차량 번호를 다시 확인해주세요.");
//					}
//
//				} catch (NumberFormatException e) {
//					e.printStackTrace();
////					RunningEndView.showError("고객 번호를 다시 확인해주세요.");
//				}
//
//			} else if (choice == 7) {
//				System.out.println("예약번호를 기억하시나요? y/n");
//				String answer = sc.next();
//				if (answer.equals("n")) {
//					System.out.println("예약내역을 조회하실 고객 이름을 입력해주세요.");
//					answer = sc.next();
////					getRentList(answer);
//				} else {
//					System.out.println("예약번호를 입력해주세요.");
//					int rentId = 0;
//					try {
//						rentId = Integer.parseInt(sc.next());
//					} catch (NumberFormatException e) {
//						System.out.println("숫자로 입력해주세요");
//					}
////				returnRent(rentId);
//				}
//
//			} else if (choice == 8) {
//				System.out.println("이름을 입력해주세요");
//				String name = sc.next();
////				getRentList(name);
//
//			} else if (choice == 9) {
//				System.out.println("이름을 입력하세요.");
//				String name = sc.next();
//				System.out.println("핸드폰 번호를 입력하세요.");
//				String phone = sc.next();
//				System.out.println("면허증 번호를 입력하세요.");
//				String license = sc.next();
////				try {
////					addCustomer(new CustomerDTO(name, phone, license));
////				} catch (SQLException e){
////					e.printStackTrace();
////					RunningEndView.showError("정확한 번호가 맞는지 확인해주세요.");
////				}
//
//			} else if (choice == 10) {
//				System.out.println("차량 모델을 입력하세요.");
//				String model = sc.next();
//				System.out.println("차량 브랜드를 입력하세요.");
//				String brand = sc.next();
//				System.out.println("차종을 입력하세요.");
//				String carType = sc.next();
//				System.out.println("대여료를 입력하세요.");
//				int price = 0;
//				try {
//					price = Integer.parseInt(sc.next());
//				} catch (NumberFormatException e) {
//					e.printStackTrace();
//					System.out.println("숫자를 입력하지 않아 기본값 0으로 저장됩니다.");
//				}
////				addCar(new CarDTO(model, brand, carType, price));
//
//			} else if (choice == 11) {
//				int carId = 0;
//				try {
//					System.out.println("삭제할 차량 번호를 입력하세요.");
//					carId = Integer.parseInt(sc.next());
//				} catch (NumberFormatException e) {
//					e.printStackTrace();
//					System.out.println("숫자를 입력하지 않아 조회할 수 없습니다.");
//				}
////				deleteCar(carId);
//
//			} else if (choice == 12) {
////				getAllRentList();
//			}
//
//		}
//		sc.close();
//		sc = null;
//	}
//	// ���ο� ���� ����
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
	
	public static void startView() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("기능 선택 : 1(해림-Attraction)/2(기환-Customer)/3(지원-Reservation)/4(은진-Reservation))");
			int a = Integer.parseInt(br.readLine());
			String str = "";
			switch (a) {
			case 1://해림영역
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
			System.out.println("회원번호 : [" + customer.getCustomerId() + "] 회원이름 : [" + customer.getName() + "] 키 : ["
					+ customer.getHeight() + "cm] 알림동의여부 : [" + customer.getAlarmYN() + "] 예약 내역 : "
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
