package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Order;

import java.time.LocalDate;
import java.util.LocalDate;
import java.util.List;

/**
 *
 * @author Seun :D
 */
public interface OrderDao {
    Order getOrders(LocalDate userDate);
    List<Order> getAllOrders();
    Order addOrder(Order order);
    void updateOrder(Order order);
    void modifyOrder(Order order);
    void deleteOrder(int orderId, LocalDate orderDate);
}
