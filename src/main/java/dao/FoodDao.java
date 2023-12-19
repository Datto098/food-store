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
import model.Food;
import model.FoodImage;

/**
 *
 * @author nguyendatdev
 */
public class FoodDao {

    public static ArrayList<Food> getFoods() {
        ArrayList<Food> result = new ArrayList<>();

        try {
            Connection connection = DatabaseDao.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from foods");

            while (rs.next()) {

                int id = rs.getInt(1);
                int menuId = rs.getInt(2);
                String foodImage = rs.getString(3);
                String foodName = rs.getString(4);
                String foodDescription = rs.getString(5);
                int foodPrice = rs.getInt(6);

                Food food = new Food(id, menuId, foodImage, foodName, foodDescription, foodPrice);

                result.add(food);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static ArrayList<Food> getFoodById(int foodId) {
        ArrayList<Food> result = new ArrayList<>();

        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from foods where food_id = ?");
            st.setInt(1, foodId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int menuId = rs.getInt(2);
                String foodImage = rs.getString(3);
                String foodName = rs.getString(4);
                String foodDescription = rs.getString(5);
                int foodPrice = rs.getInt(6);

                Food food = new Food(id, menuId, foodImage, foodName, foodDescription, foodPrice);

                result.add(food);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static ArrayList<Food> searchFood(String searchValue, int recordsPerPage, int page) {
        ArrayList<Food> result = new ArrayList<>();
        int startIndex = (page - 1) * recordsPerPage;

        try {
            Connection connection = DatabaseDao.getConnection();
            // Sử dụng điều kiện OR để tìm theo category_id hoặc category_name
            PreparedStatement st = connection.prepareStatement(
                    "SELECT * FROM foods WHERE food_id = ? OR food_name LIKE ? OR food_description LIKE ? limit ? offset ?");
            // Kiểm tra xem searchValue có thể chuyển đổi thành số không
            try {
                int foodId = Integer.parseInt(searchValue);
                st.setInt(1, foodId);
                st.setString(2, "%" + searchValue + "%");
                st.setString(3, "%" + searchValue + "%");
            } catch (NumberFormatException e) {
                // Nếu không thể chuyển đổi thành số, giả sử là tìm theo 
                st.setInt(1, 0);
                st.setString(2, "%" + searchValue + "%");
                st.setString(3, "%" + searchValue + "%");
            }

            st.setInt(4, recordsPerPage);
            st.setInt(5, startIndex);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int menuId = rs.getInt(2);
                String foodImage = rs.getString(3);
                String foodName = rs.getString(4);
                String foodDescription = rs.getString(5);
                int foodPrice = rs.getInt(6);

                Food food = new Food(id, menuId, foodImage, foodName, foodDescription, foodPrice);

                result.add(food);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static ArrayList<FoodImage> getFoodImage(int foodId) {
        ArrayList<FoodImage> result = new ArrayList<>();

        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from food_images where food_id = ?");
            st.setInt(1, foodId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String imageName = rs.getString(2);
                int foodIdData = rs.getInt(3);

                FoodImage foodImage = new FoodImage(id, imageName, foodIdData);

                result.add(foodImage);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static ArrayList<Food> getFoodByMenuId(int menuId) {
        ArrayList<Food> result = new ArrayList<>();

        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from foods where menu_id = ?");
            st.setInt(1, menuId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int menuIdData = rs.getInt(2);
                String foodImage = rs.getString(3);
                String foodName = rs.getString(4);
                String foodDescription = rs.getString(5);
                int foodPrice = rs.getInt(6);

                Food food = new Food(id, menuIdData, foodImage, foodName, foodDescription, foodPrice);

                result.add(food);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static int getAmoutOfFoodInDatabase() {
        int length = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select count(*) from foods");
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

    public static ArrayList<Food> getFoodByMenuName(String menuName, int recordsPerPage, int page) {
        ArrayList<Food> result = new ArrayList<>();
        int startIndex = (page - 1) * recordsPerPage;

        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from foods join menus on foods.menu_id = menus.menu_id where menu_name = ? limit ? offset ?");
            st.setString(1, menuName);
            st.setInt(2, recordsPerPage);
            st.setInt(3, startIndex);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int menuIdData = rs.getInt(2);
                String foodImage = rs.getString(3);
                String foodName = rs.getString(4);
                String foodDescription = rs.getString(5);
                int foodPrice = rs.getInt(6);

                Food food = new Food(id, menuIdData, foodImage, foodName, foodDescription, foodPrice);

                result.add(food);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static ArrayList<Food> getFoodByLimit(int recordsPerPage, int page) {
        ArrayList<Food> result = new ArrayList<>();
        int startIndex = (page - 1) * recordsPerPage;

        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from foods order by food_id desc limit ? offset ?");
            st.setInt(1, recordsPerPage);
            st.setInt(2, startIndex);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int menuIdData = rs.getInt(2);
                String foodImage = rs.getString(3);
                String foodName = rs.getString(4);
                String foodDescription = rs.getString(5);
                int foodPrice = rs.getInt(6);

                Food food = new Food(id, menuIdData, foodImage, foodName, foodDescription, foodPrice);

                result.add(food);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static int editFood(int foodId, String foodName, String foodDescription, int foodPrice, int menuId, String foodImage) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("update foods set food_name = ?, food_description = ?, food_price = ?, menu_id = ?, food_image = ? where food_id = ?");

            st.setString(1, foodName);
            st.setString(2, foodDescription);
            st.setInt(3, foodPrice);
            st.setInt(4, menuId);
            st.setString(5, foodImage);
            st.setInt(6, foodId);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public static int updateMainImageFood(int foodId, String foodImage) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("update foods set food_image = ? where food_id = ?");

            st.setString(1, foodImage);
            st.setInt(2, foodId);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public static int addFood(String foodName, String foodDescription, int foodPrice, String foodImage, int menuId) {
        int generatedId = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("insert into foods (food_name, food_description, food_price, food_image, menu_id) values (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, foodName);
            st.setString(2, foodDescription);
            st.setInt(3, foodPrice);
            st.setString(4, foodImage);
            st.setInt(5, menuId);

            int affectedRows = st.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    } else {
                        throw new Exception("Creating food failed, no ID obtained.");
                    }
                }
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return generatedId;
    }

    public static int deleteFood(int foodId) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("delete from foods where food_id = ?");
            st.setInt(1, foodId);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public static int uploadImage(String imageName, int foodId) {
        int status = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("insert into food_images(image_name, food_id) values(?,?)");
            st.setString(1, imageName);
            st.setInt(2, foodId);

            status = st.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return status;
    }

    public static ArrayList<FoodImage> getFoodImagesByFoodId(int foodId) {
        ArrayList<FoodImage> result = new ArrayList<>();

        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from food_images where food_id = ?");
            st.setInt(1, foodId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String imageName = rs.getString(2);
                int foodIdData = rs.getInt(3);
                FoodImage foodImage = new FoodImage(id, imageName, foodIdData);
                result.add(foodImage);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("length: " + getAmoutOfFoodInDatabase());
    }
}
