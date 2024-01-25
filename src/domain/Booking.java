package domain;

import java.sql.Date;
import java.time.LocalDate;

public class Booking {
    private String Scity;
    private String Dcity;

    private String departure;

    private LocalDate date;

    private int nr_seats;

    private int price_ticket;

    public Booking(String scity, String dcity, String departure, LocalDate date, int nr_seats, int price_ticket) {
        Scity = scity;
        Dcity = dcity;
        this.departure = departure;
        this.date = date;
        this.nr_seats = nr_seats;
        this.price_ticket = price_ticket;
    }

    public Booking() {

    }

    @Override
    public String toString() {
        return "Booking{" +
                "Scity='" + Scity + '\'' +
                ", Dcity='" + Dcity + '\'' +
                ", departure='" + departure + '\'' +
                ", date='" + date + '\'' +
                ", nr_seats=" + nr_seats +
                ", price_ticket=" + price_ticket +
                '}';
    }

    public String getScity() {
        return Scity;
    }

    public void setScity(String scity) {
        Scity = scity;
    }

    public String getDcity() {
        return Dcity;
    }

    public void setDcity(String dcity) {
        Dcity = dcity;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date =  date;
    }

    public int getNr_seats() {
        return nr_seats;
    }

    public void setNr_seats(int nr_seats) {
        this.nr_seats = nr_seats;
    }

    public int getPrice_ticket() {
        return price_ticket;
    }

    public void setPrice_ticket(int price_ticket) {
        this.price_ticket = price_ticket;
    }
}
