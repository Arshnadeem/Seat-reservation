package com.arsh.reservation;

import java.util.ArrayList;
import java.util.List;

import com.arsh.dao.SeatDao;
import com.arsh.entities.Seat;
import java.util.Scanner;

public class TrainReservationSystem {

	private SeatDao seatDAO = new SeatDao(); // SeatDAO handles the database interactions

	public static void main(String[] args) {
		TrainReservationSystem reservationSystem = new TrainReservationSystem();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("Enter the number of seats to reserve (1-7) or 0 to exit: ");
			int numberOfSeats = scanner.nextInt();

			if (numberOfSeats == 0) {
				System.out.println("Exiting the system...");
				break;
			} else if (numberOfSeats < 1 || numberOfSeats > 7) {
				System.out.println("You can only book between 1 and 7 seats. Try again.");
				continue;
			}

			// Reserve seats
			List<String> bookedSeats = reservationSystem.reserveSeats(numberOfSeats);
			if (!bookedSeats.isEmpty()) {
				System.out.println("Seats booked: " + bookedSeats);
			} else {
				System.out.println("Not enough seats available. Please try a smaller number.");
			}

			// Display the seating arrangement after booking
			reservationSystem.displaySeats();
		}

		scanner.close();
	}

	// Reserve seats based on user input
	public List<String> reserveSeats(int numberOfSeats) {
		List<String> reservedSeats = new ArrayList<>();

		// Step 1: Try to find a row with enough available seats
		List<Seat> availableSeats = seatDAO.findAvailableSeatsInRow(numberOfSeats);

		if (!availableSeats.isEmpty()) {
			seatDAO.bookSeats(availableSeats);
			for (Seat seat : availableSeats) {
				reservedSeats.add("Row " + seat.getRowNumber() + " Seat " + seat.getSeatNumber());
			}
		} else {
			System.out.println("Not enough available seats in single row , booking nearby seats");
			// Step 2: If no full row available, book nearby seats across rows
			availableSeats = seatDAO.findNearbyAvailableSeats(numberOfSeats);
			if (availableSeats.size() == numberOfSeats) {
				seatDAO.bookSeats(availableSeats);
				for (Seat seat : availableSeats) {
					reservedSeats.add("Row " + seat.getRowNumber() + " Seat " + seat.getSeatNumber());
				}
			} else {
				// If there are not enough seats available, return empty list
				System.out.println("Not enough nearby seats available.");
				System.exit(0); 
			}
		}

		return reservedSeats;
	}

	// Display seating arrangement
	public void displaySeats() {
		List<Seat> seats = seatDAO.getAllSeats();

		System.out.println("\nCurrent Seating Arrangement:");
		for (Seat seat : seats) {
			String status = seat.isBooked() ? "[X] (Booked)" : "[✔️] (Available)";
			System.out.println("Row " + seat.getRowNumber() + " Seat " + seat.getSeatNumber() + ": " + status);
		}
		System.out.println();
	}
}
