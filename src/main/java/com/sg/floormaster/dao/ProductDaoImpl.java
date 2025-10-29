package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Product;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductDaoImpl implements  ProductDao{
    private Map<String, Product> products = new HashMap<>();
    private final String PRODUCT_FILE = "data/products.txt";
    private String DELIMITER = ",";

    @Override
    public void loadProductsFromFile() {

    }

    @Override
    //TODO: Consider caching
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
}
