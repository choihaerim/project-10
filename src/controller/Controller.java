package controller;

import model.dao.ReservationDAO;
import model.dto.Attraction;
import model.dto.Reservation;
import view.EndView;

public class Controller {

	// 새로운 예약 저장
	public void insertReservation(Reservation reservation) {
		try {
			ReservationDAO.addReservation(reservation);
		} catch (Exception e) {
			EndView.showError("새로운 예약 저장 오류 발생");
		}
	}

	// 모든 예약 조회
	public void getAllReservation() {
		try {
			EndView.showResListView(ReservationDAO.getAllReservations());
		} catch (Exception e) {
			EndView.showError("모든 예약 조회 오류 발생");
		}
	}

	// 하나의 예약 조회
	public void getOneReservation(int reservationId) {
		try {
			EndView.allView(ReservationDAO.getReservation(reservationId));
		} catch (Exception e) {
			EndView.showError("정보 검색 오류 발생");
		}
	}

	public void updateReservation(int reservationId, Attraction attraction) {
		try {
			ReservationDAO.updateReservation(reservationId, attraction);

		} catch (Exception e) {
			EndView.showError("깨버리고싶어어");

		}
	}
}
