/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguyendatdev
 */
public class Category {
    private int categoryId;
    private String categoryName;
    private int parentId;
    private String hrefValue;

    public Category(int categoryId, String categoryName, int parentId, String hrefValue) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentId = parentId;
        this.hrefValue = hrefValue;
    }
    
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getHrefValue() {
        return hrefValue;
    }

    public void setHrefValue(String hrefValue) {
        this.hrefValue = hrefValue;
    }
}
