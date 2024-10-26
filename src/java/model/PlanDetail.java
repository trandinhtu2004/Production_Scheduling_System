/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class PlanDetail {
    private int pdid; // PlanDetail ID
    private ProductionPlan productionPlan; // Plan ID
    private Product product;  // Product ID
    private String pname; // Product name
    private int quantity; // Planned quantity
    private int totalActualQuantity; // Sum of actual quantities from Attendances

    public int getPdid() {
        return pdid;
    }

    public void setPdid(int pdid) {
        this.pdid = pdid;
    }

    public ProductionPlan getProductionPlan() {
        return productionPlan;
    }

    public void setProductionPlan(ProductionPlan productionPlan) {
        this.productionPlan = productionPlan;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalActualQuantity() {
        return totalActualQuantity;
    }

    public void setTotalActualQuantity(int totalActualQuantity) {
        this.totalActualQuantity = totalActualQuantity;
    }
    
    
}
