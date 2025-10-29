package com.sg.floormaster.dto;

import java.math.BigDecimal;

/**
 *
 * @author Seun :D
 */
public class Product {
    private String productType;
    private BigDecimal matCostPerSqFoot;
    private BigDecimal labourCostPerSqFoot;

    public Product(String productType, BigDecimal matCostPerSqFoot, BigDecimal labourCostPerSqFoot) {
        this.productType = productType;
        this.matCostPerSqFoot = matCostPerSqFoot;
        this.labourCostPerSqFoot = labourCostPerSqFoot;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    @Override
    public String toString() {
        return "Product{" +
                "productType='" + productType + '\'' +
                ", matCostPerSqFoot=" + matCostPerSqFoot +
                ", labourCostPerSqFoot=" + labourCostPerSqFoot +
                '}';
    }
}
