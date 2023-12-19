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

/**
 *
 * @author nguyendatdev
 */
public class CategoryDao {

    public static ArrayList<Category> getCategories() {
        ArrayList<Category> result = new ArrayList<>();

        try {
            Connection connection = DatabaseDao.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from categories");

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int parentId = rs.getInt(3);
                String hrefValue = rs.getString(4);

                Category category = new Category(id, name, parentId, hrefValue);

                result.add(category);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static int getAmoutOfCategoryInDatabase() {
        int length = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select count(*) from categories");
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

    public static ArrayList<Category> getCategoriesLimit(int recordsPerPage, int page) {
        ArrayList<Category> result = new ArrayList<>();
        int startIndex = (page - 1) * recordsPerPage;

        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from categories limit ? offset ?");
            st.setInt(1, recordsPerPage);
            st.setInt(2, startIndex);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int parentId = rs.getInt(3);
                String hrefValue = rs.getString(4);

                Category category = new Category(id, name, parentId, hrefValue);

                result.add(category);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static ArrayList<Category> searchCategory(String searchValue, int recordsPerPage, int page) {
        ArrayList<Category> result = new ArrayList<>(); 
        int startIndex = (page - 1) * recordsPerPage;
        

        try {
            Connection connection = DatabaseDao.getConnection();
            // Sử dụng điều kiện OR để tìm theo category_id hoặc category_name
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM categories WHERE category_id = ? OR category_name LIKE ? limit ? offset ?");
            // Kiểm tra xem searchValue có thể chuyển đổi thành số không
            try {
                int categoryId = Integer.parseInt(searchValue);
                st.setInt(1, categoryId);
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
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int parentId = rs.getInt(3);
                String hrefValue = rs.getString(4);

                Category category = new Category(id, name, parentId, hrefValue);
                result.add(category);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static ArrayList<Category> getCategoryById(int categoryId) {
        ArrayList<Category> result = new ArrayList<>();

        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from categories where category_id = ?");
            st.setInt(1, categoryId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int parentId = rs.getInt(3);
                String hrefValue = rs.getString(4);

                Category category = new Category(id, name, parentId, hrefValue);

                result.add(category);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static int editCategory(int id, String name, int parentId, String hrefValue) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("update categories set category_name = ?, parent_id = ?, href_value = ? where category_id = ?");
            st.setString(1, name);
            st.setInt(2, parentId);
            st.setString(3, hrefValue);
            st.setInt(4, id);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public static int addCategory(String name, int parentId, String hrefValue) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("insert into categories (category_name, parent_id, href_value) value (?,?,?)");
            st.setString(1, name);
            st.setInt(2, parentId);
            st.setString(3, hrefValue);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public static int deleteCategory(int categoryId) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("delete from categories where category_id = ?");
            st.setInt(1, categoryId);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }
}
