package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author Kyle David Rudy
 */
@Component
public class OrderDaoImpl implements OrderDao {

    private Map<Integer, Order> orders = new HashMap<>();

    @Override
    public Order getOrders(LocalDate userDate) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public Order addOrder(Order order) {
        return null;
    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void modifyOrder(Order order) {

    }

    @Override
    public void deleteOrder(int orderId, LocalDate orderDate) {

    }
}
