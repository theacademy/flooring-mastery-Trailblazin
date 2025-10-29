package com.sg.floormaster.dao;

import com.sg.floormaster.PersistenceException;
import com.sg.floormaster.dto.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Seun :D
 */
public interface OrderDao {

    void loadOrders() throws PersistenceException;

    Order getOrder(LocalDate date, int orderNo);

    List<Order> getOrdersForDate(LocalDate userDate);

    List<Order> getOrders();

    Map<Integer, Order> getAllOrders();

    void addOrder(Order order);

    void removeOrder(Order order);
}
