/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class ProductionPlanHeader {
    private int id;
    private ProductionPlan plan;
    private Product product;
    private int quantity;
    private float estimatedeffort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductionPlan getPlan() {
        return plan;
    }

    public void setPlan(ProductionPlan plan) {
        this.plan = plan;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getEstimatedeffort() {
        return estimatedeffort;
    }

    public void setEstimatedeffort(float estimatedeffort) {
        this.estimatedeffort = estimatedeffort;
    }
    
            
}
