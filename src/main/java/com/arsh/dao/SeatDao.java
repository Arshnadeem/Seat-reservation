package com.arsh.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.arsh.entities.Seat;
import com.arsh.helper.FactoryProvider;

import java.util.List;

public class SeatDao {

    // Find row with enough available seats
	public List<Seat> findAvailableSeatsInRow(int numberOfSeats) {
	    try (Session session = FactoryProvider.getFactory().openSession()) {
	        //query to fetch the exact number of consecutive available seats
	    	String hql = "SELECT s FROM Seat s " +
	                "WHERE s.isBooked = false " +
	                "AND s.rowNum IN ( " +
	                "    SELECT s2.rowNum " +
	                "    FROM Seat s2 " +
	                "    WHERE s2.isBooked = false " +
	                "    GROUP BY s2.rowNum " +
	                "    HAVING COUNT(s2) >= :numberOfSeats " +
	                ") " +
	                "ORDER BY s.rowNum, s.seatNumber";


	        Query query = session.createQuery(hql);
	        query.setParameter("numberOfSeats", Long.valueOf(numberOfSeats));  // Ensure Long type
	        query.setMaxResults(numberOfSeats);  // Limit the result to the number of seats needed
	        return query.list();
	    }
	}


    // find nearby  seats available across rows
    public List<Seat> findNearbyAvailableSeats(int numberOfSeats) {
        try (Session session = FactoryProvider.getFactory().openSession()) {
        	String hql = "FROM Seat WHERE isBooked = false ORDER BY rowNum, seatNumber";
            Query query = session.createQuery(hql);
            query.setMaxResults(numberOfSeats);
            return query.list();
        }
    }

    // Booking the selected seats
    public void bookSeats(List<Seat> seats) {
        Transaction transaction = null;
        try (Session session = FactoryProvider.getFactory().openSession()) {
            transaction = session.beginTransaction();
            for (Seat seat : seats) {
                seat.setBooked(true);
                session.update(seat);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Displaying the seating arrangement
    public List<Seat> getAllSeats() {
        try (Session session = FactoryProvider.getFactory().openSession()) {
            return session.createQuery("FROM Seat ORDER BY row_num, seatNumber", Seat.class).list();
        }
    }

}
