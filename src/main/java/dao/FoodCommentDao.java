/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Food;
import model.FoodComment;

/**
 *
 * @author nguyendatdev
 */
public class FoodCommentDao {

    public static ArrayList<FoodComment> getFoodCommentByFoodId(int foodId) {
        ArrayList<FoodComment> result = new ArrayList<>();

        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from food_comments where food_id = ?");
            st.setInt(1, foodId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int foodIdData = rs.getInt(2);
                String comment = rs.getString(3);
                String username = rs.getString(4);

                FoodComment foodComment = new FoodComment(id, foodIdData, comment, username);

                result.add(foodComment);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return result;
    }
    
    public static int addComment(int foodId, String username, String comment) {
        int generatedId = 0;
        try {
            Connection connection = DatabaseDao.getConnection();
            PreparedStatement st = connection.prepareStatement("insert into food_comments (food_id, comment, username) values (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setInt(1, foodId);
            st.setString(2, comment);
            st.setString(3, username);

            int affectedRows = st.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    } else {
                        throw new Exception("Creating food comment failed, no ID obtained.");
                    }
                }
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return generatedId;
    }
}
