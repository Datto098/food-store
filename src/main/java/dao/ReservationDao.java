/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Reservation;

/**
 *
 * @author nguyendatdev
 */
public class ReservationDao {

    public static ArrayList<Reservation> getReservations() {
        ArrayList<Reservation> result = new ArrayList<>();

        try {
            Connection connection = DatabaseDao.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from reservation");

            // Sử dụng SimpleDateFormat để định dạng ngày và giờ
            SimpleDateFormat outputDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while (rs.next()) {

                int reservationId = rs.getInt(1);
                java.sql.Timestamp timestamp = rs.getTimestamp(2);
                String formattedDateTime = outputDateTimeFormat.format(timestamp);
                int people = rs.getInt(3);
                String name = rs.getString(4);
                String phone = rs.getString(5);
                String email = rs.getString(6);
                int status = rs.getInt(7);

                Reservation reservation = new Reservation(reservationId, formattedDateTime, people, name, phone, email, status);
                result.add(reservation);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static int getAmoutOfReservationInDatabase() {
        int length = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select count(*) from reservation");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                length = rs.getInt(1);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return length;
    }

    public static ArrayList<Reservation> getReservationsLimit(int recordsPerPage, int page) {
        ArrayList<Reservation> result = new ArrayList<>();
        int startIndex = (page - 1) * recordsPerPage;

        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM reservation ORDER BY reservation_id DESC LIMIT ? OFFSET ?");
            st.setInt(1, recordsPerPage);
            st.setInt(2, startIndex);
            ResultSet rs = st.executeQuery();

            // Sử dụng SimpleDateFormat để định dạng ngày và giờ
            SimpleDateFormat outputDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while (rs.next()) {
                int reservationId = rs.getInt(1);
                java.sql.Timestamp timestamp = rs.getTimestamp(2);
                String formattedDateTime = outputDateTimeFormat.format(timestamp);
                int people = rs.getInt(3);
                String name = rs.getString(4);
                String phone = rs.getString(5);
                String email = rs.getString(6);
                int status = rs.getInt(7);

                Reservation reservation = new Reservation(reservationId, formattedDateTime, people, name, phone, email, status);
                result.add(reservation);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static ArrayList<Reservation> getReservationById(int reservationId) {
        ArrayList<Reservation> result = new ArrayList<>();

        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from reservation where reservation_id = ?");
            st.setInt(1, reservationId);
            ResultSet rs = st.executeQuery();

            // Sử dụng SimpleDateFormat để định dạng ngày và giờ
            SimpleDateFormat outputDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while (rs.next()) {
                int id = rs.getInt(1);
                java.sql.Timestamp timestamp = rs.getTimestamp(2);
                String formattedDateTime = outputDateTimeFormat.format(timestamp);
                int people = rs.getInt(3);
                String name = rs.getString(4);
                String phone = rs.getString(5);
                String email = rs.getString(6);
                int status = rs.getInt(7);

                Reservation reservation = new Reservation(id, formattedDateTime, people, name, phone, email, status);
                result.add(reservation);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static int editReservation(int reservationId, String phone, String name, String email, Date reservationDate, int people) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("update reservation set date = ?, phone = ?, name = ?, email = ?, people = ? where reservation_id = ?");
            st.setDate(1, (java.sql.Date) reservationDate);
            st.setString(2, phone);
            st.setString(3, name);
            st.setString(4, email);
            st.setInt(5, people);
            st.setInt(6, reservationId);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public static int addReservation(String phone, String name, String email, String reservationDate, int people) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("insert into reservation (date, phone, name, email, people) value (?,?,?,?,?)");
            st.setString(1, reservationDate);
            st.setString(2, phone);
            st.setString(3, name);
            st.setString(4, email);
            st.setInt(5, people);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public static int deleteReservation(int idReservation) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("delete from reservation where reservation_id = ?");
            st.setInt(1, idReservation);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public static ArrayList<Reservation> searchReservation(String searchValue, int recordsPerPage, int page) {
        ArrayList<Reservation> result = new ArrayList<>();
        int startIndex = (page - 1) * recordsPerPage;

        try {
            Connection connection = DatabaseDao.getConnection();
            // Sử dụng điều kiện OR để tìm theo category_id hoặc category_name
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM reservation WHERE reservation_id = ? OR name LIKE ? OR phone LIKE ? OR email LIKE ? OR date LIKE ? limit ? offset ?");
            // Kiểm tra xem searchValue có thể chuyển đổi thành số không
            try {
                int reservationId = Integer.parseInt(searchValue);
                st.setInt(1, reservationId);
            } catch (NumberFormatException e) {
                // Nếu không thể chuyển đổi thành số, giả sử là tìm theo category_name
                st.setInt(1, 0);
            }
            st.setString(2, "%" + searchValue + "%");
            st.setString(3, "%" + searchValue + "%");
            st.setString(4, "%" + searchValue + "%");
            st.setString(5, "%" + searchValue + "%");

            st.setInt(6, recordsPerPage);
            st.setInt(7, startIndex);

            // Sử dụng SimpleDateFormat để định dạng ngày và giờ
            SimpleDateFormat outputDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                java.sql.Timestamp timestamp = rs.getTimestamp(2);
                String formattedDateTime = outputDateTimeFormat.format(timestamp);
                int people = rs.getInt(3);
                String name = rs.getString(4);
                String phone = rs.getString(5);
                String email = rs.getString(6);
                int status = rs.getInt(7);

                Reservation reservation = new Reservation(id, formattedDateTime, people, name, phone, email, status);
                result.add(reservation);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static int updateReservationStatus(int reservationId, int statusUp) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("update reservation set reservation_status = ? where reservation_id = ?");

            st.setInt(1, statusUp);
            st.setInt(2, reservationId);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public static void main(String[] args) {
        ArrayList<Reservation> reservations = getReservations();
        for (Reservation reservation : reservations) {
            System.out.println(reservation.getReservationDate());
        }
    }

}
