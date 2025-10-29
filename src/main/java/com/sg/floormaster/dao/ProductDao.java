package com.sg.floormaster.dao;

import com.sg.floormaster.PersistenceException;
import com.sg.floormaster.dto.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao {
     void loadProductsFromFile() throws PersistenceException;
     List<Product> getAllProducts();
     Product unmarshallProduct(String productsAsText);
}
