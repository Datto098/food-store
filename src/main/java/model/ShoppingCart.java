/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguyendatdev
 */
public class ShoppingCart {

    private int foodId;
    private String foodName;
    private double total;
    private int amount;
    private String foodImage;

    public ShoppingCart(int foodId, String foodName, double total, int amount, String foodImage) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.total = total;
        this.amount = amount;
        this.foodImage = foodImage;
    }

    public double getTotalPrice() {
        return this.total * this.amount;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
