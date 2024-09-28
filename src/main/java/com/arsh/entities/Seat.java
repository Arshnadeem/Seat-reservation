package com.arsh.entities;

import javax.persistence.*;

//this will create a table in my local database
@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;

    @Column(name = "row_num")
    private int rowNum;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "is_booked")
    private boolean isBooked;

    // Getter and setter

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getRowNumber() {
        return rowNum;
    }

    public void setRowNumber(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
