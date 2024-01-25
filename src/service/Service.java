package service;

import domain.Booking;
import repository.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public List<Booking> getAll()  {
        List<Booking> repoo = new ArrayList<>();
        List<Booking> sortedRepooByName = null;
        try (Connection connection = repo.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Booking");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Booking booking = new Booking(
                        resultSet.getString("Scity"),
                        resultSet.getString("Dcity"),
                        resultSet.getString("Departure"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getInt("nr_seats"),
                        resultSet.getInt("price_ticket")
                );
                repoo.add(booking);
                sortedRepooByName = repoo.stream()
                        .sorted(Comparator.comparing(Booking::getDeparture)
                                .thenComparing(Booking::getDate))
                        .collect(Collectors.toList());

            }

        }catch (SQLException e) {
                e.printStackTrace();
            }
            return sortedRepooByName;
}
    public void save(Booking booking) {

        try (Connection connection = repo.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Booking(Scity, Dcity, departure, date, nr_seats, price_ticket) values (?,?,?,?,?,?)")) {

            statement.setString(1, booking.getScity());
            statement.setString(2, booking.getDcity());
            statement.setString(3, booking.getDeparture());
            statement.setDate(4, Date.valueOf(booking.getDate()));
            statement.setInt(5,booking.getNr_seats());
            statement.setInt(6,booking.getPrice_ticket());
            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

}
    public void update(Booking booking) {
        try (Connection connection = repo.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Booking SET  departure = ?, date = ?, nr_seats = ?, price_ticket = ? WHERE Scity = ? and Dcity = ?"
             )) {
            statement.setString(1, booking.getDeparture());
            statement.setDate(2, Date.valueOf(booking.getDate()));
            statement.setInt(3, booking.getNr_seats());
            statement.setInt(4, booking.getPrice_ticket());
            statement.setString(5, booking.getScity());
            statement.setString(6, booking.getDcity());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
