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
import java.util.ArrayList;
import model.User;

/**
 *
 * @author nguyendatdev
 */
public class UserDao {

    public static ArrayList<User> getUsers() {
        ArrayList<User> result = new ArrayList<>();

        try {
            Connection connection = DatabaseDao.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from members");

            while (rs.next()) {
                int userIdData = rs.getInt(1);
                String usernameData = rs.getString(2);
                String passwordData = rs.getString(3);
                int role = rs.getInt(4);
                String email = rs.getString(5);

                User user = new User(userIdData, usernameData, passwordData, role, email);

                result.add(user);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static ArrayList<User> getUserByUserId(int userId) {
        ArrayList<User> result = new ArrayList<>();

        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from members where user_id = ?");
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int userIdData = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                int role = rs.getInt(4);
                String email = rs.getString(5);

                User user = new User(userIdData, username, password, role, email);

                result.add(user);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static ArrayList<User> getUserByUsernameAndPassword(String username, String password) {
        ArrayList<User> result = new ArrayList<>();

        try {
            try (Connection connection = DatabaseDao.getConnection()) {
                PreparedStatement st = connection.prepareStatement("select * from members where username = ? and password = ?");
                st.setString(1, username);
                st.setString(2, password);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    int userIdData = rs.getInt(1);
                    String usernameData = rs.getString(2);
                    String passwordData = rs.getString(3);
                    int role = rs.getInt(4);
                    String email = rs.getString(5);

                    User user = new User(userIdData, username, password, role, email);

                    result.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static int editUser(int userId, String username, String password, int role, String email) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("update members set username = ?, password = ?, role = ?, email = ? where user_id = ?");
            st.setString(1, username);
            st.setString(2, password);
            st.setInt(3, role);
            st.setString(4, email);
            st.setInt(5, userId);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public static int addUser(String username, String password, int role, String email) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("insert into members (username, password, role, email) value (?,?,?,?)");
            st.setString(1, username);
            st.setString(2, password);
            st.setInt(3, role);
            st.setString(4, email);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public static int deleteUser(int userId) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("delete from members where userId = ?");
            st.setInt(1, userId);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }
}
