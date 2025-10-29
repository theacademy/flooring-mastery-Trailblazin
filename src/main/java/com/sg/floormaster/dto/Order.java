package com.sg.floormaster.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * @author Seun :D
 */
public class Order {
    private int orderNumber;
    private LocalDate orderDate;
    private String customerName;
    private String stateAbbr;
    private String productType;
    private BigDecimal area;
    private BigDecimal matCostPerSqFoot;
    private BigDecimal labourCostPerSqFoot;
    private BigDecimal materialCost;
    private BigDecimal labourCost;
    private BigDecimal tax;
    private BigDecimal taxRate;
    private static final BigDecimal taxDivisor = new BigDecimal("100");
    private BigDecimal total;

    public Order(LocalDate orderDate, String customerName, String stateAbbr, String productType, BigDecimal area) {
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.stateAbbr = stateAbbr;
        this.productType = productType;
        this.area = area;
    }

    public Order(int orderNumber, LocalDate orderDate, String customerName, String stateAbbr, String productType, BigDecimal area, BigDecimal matCostPerSqFoot, BigDecimal labourCostPerSqFoot, BigDecimal materialCost, BigDecimal labourCost, BigDecimal tax, BigDecimal taxRate, BigDecimal total) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.stateAbbr = stateAbbr;
        this.productType = productType;
        this.area = area;
        this.matCostPerSqFoot = matCostPerSqFoot;
        this.labourCostPerSqFoot = labourCostPerSqFoot;
        this.materialCost = materialCost;
        this.labourCost = labourCost;
        this.tax = tax;
        this.taxRate = taxRate;
        this.total = total;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return stateAbbr;
    }

    public void setState(String state) {
        stateAbbr = state;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMatCostPerSqFoot() {
        return matCostPerSqFoot;
    }

    public void setMatCostPerSqFoot(BigDecimal matCostPerSqFoot) {
        this.matCostPerSqFoot = matCostPerSqFoot;
    }

    public BigDecimal getLabourCostPerSqFoot() {
        return labourCostPerSqFoot;
    }

    public void setLabourCostPerSqFoot(BigDecimal labourCostPerSqFoot) {
        this.labourCostPerSqFoot = labourCostPerSqFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost() {
        //Get material cost via dependency injection
        this.materialCost = matCostPerSqFoot.multiply(getArea());
    }

    public BigDecimal getLabourCost() {
        return this.labourCost;
    }

    public void setLabourCost() {
        //Get labour cost via dependency injection
        this.labourCost = labourCostPerSqFoot.multiply(getArea());
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax() {
        this.tax = materialCost
                .add(labourCost)
                .multiply(taxRate.divide(taxDivisor, 2, RoundingMode.HALF_UP));
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal() {
        this.total = materialCost.add(labourCost.add(tax));
    }

    public void calculateOrderDetails() {
        this.setTax();
        this.setMaterialCost();
        this.setLabourCost();
        this.setTotal();

    }


    @Override
    public String toString() {
        return "Order: {" +
                "OrderNumber=" + orderNumber +
                ", orderDate=" + orderDate +
                ", customerName='" + customerName + '\'' +
                ", stateAbbr='" + stateAbbr + '\'' +
                ", productType='" + productType + '\'' +
                ", area=" + area +
                ", matCostPerSqFoot=" + matCostPerSqFoot +
                ", labourCostPerSqFoot=" + labourCostPerSqFoot +
                ", materialCost=" + materialCost +
                ", labourCost=" + labourCost +
                ", tax=" + tax +
                ", taxRate=" + taxRate +
                ", total=" + total +
                '}';
    }
}
