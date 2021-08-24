package controller;

import model.dao.ReservationDAO;
import model.dto.Attraction;
import model.dto.Reservation;
import view.EndView;

public class Controller {

	// ���ο� ���� ����
	public void insertReservation(Reservation reservation) {
		try {
			ReservationDAO.addReservation(reservation);
		} catch (Exception e) {
			EndView.showError("���ο� ���� ���� ���� �߻�");
		}
	}

	// ��� ���� ��ȸ
	public void getAllReservation() {
		try {
			EndView.showResListView(ReservationDAO.getAllReservations());
		} catch (Exception e) {
			EndView.showError("��� ���� ��ȸ ���� �߻�");
		}
	}

	// �ϳ��� ���� ��ȸ
	public void getOneReservation(int reservationId) {
		try {
			EndView.allView(ReservationDAO.getReservation(reservationId));
		} catch (Exception e) {
			EndView.showError("���� �˻� ���� �߻�");
		}
	}

	public void updateReservation(int reservationId, Attraction attraction) {
		try {
			ReservationDAO.updateReservation(reservationId, attraction);

		} catch (Exception e) {
			EndView.showError("��������;��");

		}
	}
}
