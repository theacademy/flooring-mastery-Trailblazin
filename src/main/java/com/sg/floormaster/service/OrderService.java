package com.sg.floormaster.service;

import com.sg.floormaster.PersistenceException;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Tax;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    int getNextOrderNumber();

    void addOrder(Order order);



    void loadOrders() throws PersistenceException;

    List<Order> getOrders();

    void removeOrder(Order order);

    List<Tax> getTaxes();

    List<Product> getProducts();
}
