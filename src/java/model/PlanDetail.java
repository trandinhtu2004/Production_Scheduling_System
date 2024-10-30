/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class PlanDetail {
    private int pdid; // PlanDetail ID
    private ProductionPlan productionPlan; // Plan ID
    private Product product;  // Product ID
    private Date date;
    private int quantityDay; // Planned quantity
    private ArrayList<Shift> shifts;
    private ProductionPlanHeader header;
    private int sid;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
    

    public ProductionPlanHeader getHeader() {
        return header;
    }

    public void setHeader(ProductionPlanHeader header) {
        this.header = header;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public ArrayList<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(ArrayList<Shift> shifts) {
        this.shifts = shifts;
    }

    
    
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

    public int getQuantityDay() {
        return quantityDay;
    }

    public void setQuantityDay(int quantityDay) {
        this.quantityDay = quantityDay;
    }

    
    
    
}
