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
import model.Category;
import model.Menu;

/**
 *
 * @author nguyendatdev
 */
public class MenuDao {

    public static ArrayList<Menu> getMenus() {
        ArrayList<Menu> result = new ArrayList<>();

        try {
            Connection connection = DatabaseDao.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from menus");

            while (rs.next()) {
                int menuId = rs.getInt(1);
                String menuName = rs.getString(2);
                String menuDescription = rs.getString(3);

                Menu menu = new Menu(menuId, menuName, menuDescription);

                result.add(menu);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static ArrayList<Menu> getMenusLimit(int recordsPerPage, int page) {
        ArrayList<Menu> result = new ArrayList<>();
        int startIndex = (page - 1) * recordsPerPage;

        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from menus limit ? offset ?");

            st.setInt(1, recordsPerPage);
            st.setInt(2, startIndex);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int menuId = rs.getInt(1);
                String menuName = rs.getString(2);
                String menuDescription = rs.getString(3);

                Menu menu = new Menu(menuId, menuName, menuDescription);

                result.add(menu);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return result;
    }
    
    
    public static ArrayList<Menu> searchMenu(String searchValue, int recordsPerPage, int page) {
        ArrayList<Menu> result = new ArrayList<>(); 
        int startIndex = (page - 1) * recordsPerPage;
        

        try {
            Connection connection = DatabaseDao.getConnection();
            // Sử dụng điều kiện OR để tìm theo category_id hoặc category_name
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM menus WHERE menu_id = ? OR menu_name LIKE ? limit ? offset ?");
            // Kiểm tra xem searchValue có thể chuyển đổi thành số không
            try {
                int menuId = Integer.parseInt(searchValue);
                st.setInt(1, menuId);
                st.setString(2, "%" + searchValue + "%"); // %searchValue% để tìm kiếm theo phần của category_name
            } catch (NumberFormatException e) {
                // Nếu không thể chuyển đổi thành số, giả sử là tìm theo category_name
                st.setInt(1, 0); 
                st.setString(2, "%" + searchValue + "%");
            }
            
            st.setInt(3, recordsPerPage);
            st.setInt(4, startIndex);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int menuId = rs.getInt(1);
                String menuName = rs.getString(2);
                String menuDescription = rs.getString(3);

                Menu menu = new Menu(menuId, menuName, menuDescription);

                result.add(menu);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }
    
    
    public static int getAmoutOfMenuInDatabase() {
        int length = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select count(*) from menus");
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

    public static ArrayList<Menu> getMenuById(int menuId) {
        ArrayList<Menu> result = new ArrayList<>();

        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from menus where menu_id = ?");
            st.setInt(1, menuId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int menuIdData = rs.getInt(1);
                String menuName = rs.getString(2);
                String menuDescription = rs.getString(3);

                Menu menu = new Menu(menuIdData, menuName, menuDescription);

                result.add(menu);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }
//Continute

    public static int editMenu(int id, String menuName, String menuDescription) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("update menus set menu_name = ?, menu_description = ? where menu_id = ?");
            st.setString(1, menuName);
            st.setString(2, menuDescription);
            st.setInt(3, id);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public static int addMenu(String menuName, String menuDescription) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("insert into menus (menu_name, menu_description) value (?,?)");
            st.setString(1, menuName);
            st.setString(2, menuDescription);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public static int deleteMenu(int menuId) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("delete from menus where menu_id = ?");
            st.setInt(1, menuId);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }
}
