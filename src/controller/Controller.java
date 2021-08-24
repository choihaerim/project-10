package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;

import model.dao.CustomerDAO;
import model.dto.Customer;
import model.dto.Reservation;

public class Controller {
	public static void startView() {
		try {
			System.out.println("CRUD(1,2,3,4)");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int a = Integer.parseInt(br.readLine());
			String str = "";
			switch (a) {
			case 1:
				System.out.println("이름/키/알람여부(y or n)");
				str = br.readLine();
				String[] str2 = str.split("/");
				insertCustomer(str2[0], Integer.parseInt(str2[1]), str2[2]);
				break;
			case 2:
				selectAllCustomer();
//				System.out.println("이름");
//				str = br.readLine();
//				findByName(str);
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
		} catch (IOException e) {
			e.printStackTrace();
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
